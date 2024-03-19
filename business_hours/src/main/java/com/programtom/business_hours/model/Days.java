package com.programtom.business_hours.model;

@lombok.Data
public class Days {
    private java.util.List<WorkingHour> tuesday;
    private java.util.List<WorkingHour> wednesday;
    private java.util.List<WorkingHour> thursday;
    private java.util.List<WorkingHour> friday;
    private java.util.List<WorkingHour> monday;

}