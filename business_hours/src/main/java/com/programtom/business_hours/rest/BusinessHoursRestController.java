package com.programtom.business_hours.rest;

import com.programtom.business_hours.model.PlaceLocalEntry;
import com.programtom.business_hours.service.BusinessLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BusinessHoursRestController {

    @Autowired
    BusinessLocationService businessLocationService;

    @GetMapping("v1/businessHours/{id}")
    ResponseEntity<PlaceLocalEntry> get(@PathVariable("id") String id) {
        //1
        Optional<PlaceLocalEntry> localEntry = businessLocationService.getBusinessEntry(id);
        //2
        return localEntry.map((PlaceLocalEntry placeLocalEntry) -> {
            businessLocationService.groupOpenDays(placeLocalEntry);
            return ResponseEntity.ok(placeLocalEntry);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("v1/isOpenNow/{id}")
    ResponseEntity<PlaceLocalEntry> isOpenNow(@PathVariable("id") String id) {
        //1
        Optional<PlaceLocalEntry> localEntry = businessLocationService.getBusinessEntry(id);
        //2
        return localEntry.map((PlaceLocalEntry placeLocalEntry) -> {
            businessLocationService.groupOpenDays(placeLocalEntry);
            businessLocationService.isOpenNow(placeLocalEntry);
            return ResponseEntity.ok(placeLocalEntry);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
