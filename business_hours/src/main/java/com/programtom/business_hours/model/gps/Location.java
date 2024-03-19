package com.programtom.business_hours.model.gps;

@lombok.Data
public class Location {
    private String latlon;
    private Integer x;
    private Format2d format2d;
    private Integer y;
    private Double lon;
    private String geohex;
    private Double lat;
}


