package com.programtom.business_hours.model;

@lombok.Data
public class PlaceLocalEntry {
    private String displayed_where;
    private java.util.List<Address> addresses;
    private String displayed_what;
    private String language;
    private Source source;
    private String creation_date;
    private String modified_date;
    private String local_entry_id;
    private java.util.List<String> tags;
    private PlaceFeedbackSummary place_feedback_summary;
    private OpeningHours opening_hours;
    private String _class;
    private String entry_type;
    private String _update_process_type;
    private Boolean favorited;
    private GeoLocation where;
}





