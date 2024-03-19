package com.programtom.business_hours.model.gps;

@lombok.Data
public class Geography {
    private Integer altitude;
    private BoundingBox bounding_box;
    private Location location;
}