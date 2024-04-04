package com.programtom.business_hours.web;

import com.programtom.business_hours.model.PlaceLocalEntry;
import com.programtom.business_hours.service.BusinessLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class BusinessLocationController {

    @Autowired
    BusinessLocationService businessLocationService;

    @GetMapping("/businessPlace")
    public ModelAndView businessPlace(@RequestParam("placeId") String id) {
        ModelAndView businessPlaceView = new ModelAndView("businessPlace");

        PlaceLocalEntry business = businessLocationService.getBusinessEntry(id).orElse(null);
        if (business != null) {
            businessLocationService.groupOpenDays(business);
        }
        businessPlaceView.addObject("business", business);


        Map<Integer, String> daysOfTheWeek = new HashMap<>();
        daysOfTheWeek.put(1, "Monday");
        daysOfTheWeek.put(2, "Tuesday");
        daysOfTheWeek.put(3, "Wednesday");
        daysOfTheWeek.put(4, "Thursday");
        daysOfTheWeek.put(5, "Friday");
        daysOfTheWeek.put(6, "Saturday");
        daysOfTheWeek.put(7, "Sunday");
        businessPlaceView.addObject("daysOfTheWeek", daysOfTheWeek);


        return businessPlaceView;
    }
}
