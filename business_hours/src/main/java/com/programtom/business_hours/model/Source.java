package com.programtom.business_hours.model;

@lombok.Data
public class Source {
    private Subscriber subscriber;
    private Provider provider;
    private String source_id;
}