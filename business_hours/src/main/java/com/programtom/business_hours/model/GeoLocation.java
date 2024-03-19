package com.programtom.business_hours.model;

import com.programtom.business_hours.model.gps.Geography;

@lombok.Data
public class GeoLocation {
    private Integer zipcode;
    private String city;
    private String street;
    private Geography geography;
    private String house_number;
    private String state;
}

