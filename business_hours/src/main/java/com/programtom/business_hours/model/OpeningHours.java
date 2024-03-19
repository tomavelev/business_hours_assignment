package com.programtom.business_hours.model;

import java.util.ArrayList;
import java.util.List;

@lombok.Data
public class OpeningHours {
    private Boolean closed_on_holidays;
    private Days days;
    private Boolean open_by_arrangement;

    private List<DaysGroup> daysGroup = new ArrayList<>();
}