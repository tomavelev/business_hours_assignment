package com.programtom.business_hours.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DaysGroup {

    private List<Integer> daysOfTheWeek = new ArrayList<>();
    private java.util.List<WorkingHour> hours = new ArrayList<>();
}
