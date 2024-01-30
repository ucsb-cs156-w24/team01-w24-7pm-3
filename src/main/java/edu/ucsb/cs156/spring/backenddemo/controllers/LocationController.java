package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import edu.ucsb.cs156.spring.backenddemo.services.LocationQueryService;


@Tag(name="Location Information")
@RestController
@RequestMapping("/api/locationquery")
public class LocationController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    LocationQueryService locationQueryService;

    @Operation(summary = "Get location data from a location", description = "I'm not too sure what I'm supposed to get ngl")
    @GetMapping("/get")
    public ResponseEntity<String> getLocationDetails(
        @Parameter(name="location", description="location", example="islaVista") @RequestParam String location
    ) throws JsonProcessingException {
        String result = locationQueryService.getJSON(location);
        return ResponseEntity.ok().body(result);
    }
}