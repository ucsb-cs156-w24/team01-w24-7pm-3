package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.HolidayQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Public Holiday info from nager")
@Slf4j
@RestController
@RequestMapping("/api/publicholidays")
public class HolidayController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    HolidayQueryService holidayQueryService;

    @Operation(summary = "Get public holidays for a certain year and country", description = "JSON return format documented here: \"https://date.nager.at/api/v2/publicholidays/{year}/{countryCode}")
    @GetMapping("/get")
    public ResponseEntity<String> getPublicHolidays(
        @Parameter(name="year", description="year of the holidays requested", example="2024") @RequestParam String year,
        @Parameter(name="countryCode", description="countryCode of country's holidays", example="US") @RequestParam String countryCode
    ) throws JsonProcessingException {
        log.info("getPublicHolidays: year={} countryCode={}", year, countryCode);
        String result = holidayQueryService.getJSON(year, countryCode);
        return ResponseEntity.ok().body(result);
    }
}
