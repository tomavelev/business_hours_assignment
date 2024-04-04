package com.programtom.business_hours.model;

import lombok.Data;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@Data
public class PlaceLocalEntry {
    private String displayed_where;
    private String displayed_what;
    //    @JsonProperty("opening_hours") // properties may be renamed to the java Standard with @JsonProperty
    //I've left it as they are for speed of implementation
    private OpeningHours opening_hours;
    private boolean isOpenNow;

    public List<WorkingHour> getWorkingHours(int dayOfWeek) {
        return switch (dayOfWeek) {
            case Calendar.MONDAY: {
                yield opening_hours.getDays().getMonday();
            }
            case Calendar.TUESDAY: {
                yield opening_hours.getDays().getTuesday();
            }
            case Calendar.WEDNESDAY: {
                yield opening_hours.getDays().getWednesday();
            }
            case Calendar.THURSDAY: {
                yield opening_hours.getDays().getThursday();
            }
            case Calendar.FRIDAY: {
                yield opening_hours.getDays().getFriday();
            }
            case Calendar.SATURDAY: {
                yield opening_hours.getDays().getSaturday();
            }
            case Calendar.SUNDAY: {
                yield opening_hours.getDays().getSunday();
            }
            default:
                yield Collections.emptyList();
        };
    }
}





