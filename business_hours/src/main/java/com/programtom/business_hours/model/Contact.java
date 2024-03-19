package com.programtom.business_hours.model;

@lombok.Data
public class Contact {
    private String contact_type;
    private Boolean freecall_enabled;
    private String service_code;
    private String call_link;
    private Boolean refuse_advertising;
    private String phone_number;
    private String _class;
    private String formatted_service_code;
    private String id;
    private Boolean preferred;
}