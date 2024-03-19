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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

        DaysGroup group = null;

        for (int i = 1; i <= 5; i++) {
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
                        body.getOpening_hours().getDaysGroup().add(group);
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
}
