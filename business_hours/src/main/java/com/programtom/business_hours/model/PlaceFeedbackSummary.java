package com.programtom.business_hours.model;

@lombok.Data
public class PlaceFeedbackSummary {
    private Boolean display_recommendations;
    private Integer positive_recommendations;
    private Integer feedbacks_count;
    private java.util.List<RatingSummary> rating_summaries;
    private Integer positive_recommendation_percentage;
    private Double average_rating;
    private Integer ratings_count;
    private Integer recommendations;
    private Integer reviews_count;
    private Boolean display_average_rating;
}