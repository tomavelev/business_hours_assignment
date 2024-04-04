package com.programtom.business_hours.service;

import com.programtom.business_hours.model.DaysGroup;
import com.programtom.business_hours.model.PlaceLocalEntry;
import com.programtom.business_hours.model.WorkingHour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;

@Service
public class BusinessLocationService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${storage_base}")
    private String storageBase;

    public Optional<PlaceLocalEntry> getBusinessEntry(String id) {

        try {
            ResponseEntity<PlaceLocalEntry> placeResponse = restTemplate.getForEntity(storageBase + id, PlaceLocalEntry.class);

            if (placeResponse.hasBody() && placeResponse.getBody() != null) {
                PlaceLocalEntry body = placeResponse.getBody();
                return Optional.of(body);
            } else {
                return Optional.empty();
            }
        } catch (HttpClientErrorException.NotFound notFound) {
            return Optional.empty();
        }
    }

    public void groupOpenDays(PlaceLocalEntry body) {
        Map<Integer, List<WorkingHour>> pairs = new LinkedHashMap<>();
        pairs.put(1, body.getOpening_hours().getDays().getMonday());
        pairs.put(2, body.getOpening_hours().getDays().getTuesday());
        pairs.put(3, body.getOpening_hours().getDays().getWednesday());
        pairs.put(4, body.getOpening_hours().getDays().getThursday());
        pairs.put(5, body.getOpening_hours().getDays().getFriday());
        pairs.put(6, body.getOpening_hours().getDays().getSaturday());
        pairs.put(7, body.getOpening_hours().getDays().getSunday());

        DaysGroup group = null;

        for (int i = 1; i <= 7; i++) {
            List<WorkingHour> workingHours = pairs.get(i);
            if (workingHours != null) {
                if (group == null) {
                    group = new DaysGroup();
                    group.getDaysOfTheWeek().add(i);
                    group.setHours(workingHours);
                    body.getOpening_hours().getDaysGroup().add(group);
                } else {
                    if (group.getHours().equals(workingHours)) {
                        group.getDaysOfTheWeek().add(i);
                    } else {
                        group = new DaysGroup();
                        group.getDaysOfTheWeek().add(i);
                        group.setHours(workingHours);
                        body.getOpening_hours().getDaysGroup().add(group);
                    }
                }
            } else {
                if (group != null) {
                    group = null;
                }
            }
        }
    }

    public void isOpenNow(PlaceLocalEntry placeLocalEntry) {
        Calendar timeNow = Calendar.getInstance();
        timeNow.setTime(new Date());

        int dayOfWeek = timeNow.get(Calendar.DAY_OF_WEEK);
        List<WorkingHour> workingHoursForCurrentDay = placeLocalEntry.getWorkingHours(dayOfWeek);

        LocalTime now = LocalTime.now();

//        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        if (!workingHoursForCurrentDay.isEmpty()) {
            for (WorkingHour workingHour : workingHoursForCurrentDay) {
                if (workingHour.getType().equals("OPEN")) {
                    String start = workingHour.getStart();
                    String end = workingHour.getEnd();
                    try {
                        LocalTime fromDate = LocalTime.parse(start);
                        LocalTime endDate = LocalTime.parse(end);


//                        timeNow after fromDate
//                        &&
//                        timeNow before endDate

                        if (now.isAfter(fromDate) && now.isBefore(endDate)) {
                            placeLocalEntry.setOpenNow(true);
                        }
                        break;
                    } catch (DateTimeParseException e) {

                        // report to internal team that the origin data is corrupted
//                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
