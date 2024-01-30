package edu.ucsb.cs156.spring.backenddemo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import edu.ucsb.cs156.spring.backenddemo.services.HolidayQueryService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;

@WebMvcTest(value = HolidayController.class)
public class HolidayControllerTests {
    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    HolidayQueryService mockHolidayQueryService;

    @Test
    public void test_getEarthquakes() throws Exception {

        String fakeJsonResult = "[{\"date\":\"2024-01-01\",\"localName\":\"New Year's Day\",\"name\":\"New Year's Day\",\"countryCode\":\"US\",\"fixed\":false,\"global\":true,\"counties\":null,\"launchYear\":null,\"type\":\"Public\"},{\"date\":\"2024-01-15\",\"localName\":\"Martin Luther King, Jr. Day\",\"name\":\"Martin Luther King, Jr. Day\",\"countryCode\":\"US\",\"fixed\":false,\"global\":true,\"counties\":null,\"launchYear\":null,\"type\":\"Public\"},{\"date\":\"2024-02-19\",\"localName\":\"Presidents Day\",\"name\":\"Washington's Birthday\",\"countryCode\":\"US\",\"fixed\":false,\"global\":true,\"counties\":null,\"launchYear\":null,\"type\":\"Public\"},{\"date\":\"2024-03-29\",\"localName\":\"Good Friday\",\"name\":\"Good Friday\",\"countryCode\":\"US\",\"fixed\":false,\"global\":false,\"counties\":[\"US-CT\",\"US-DE\",\"US-HI\",\"US-IN\",\"US-KY\",\"US-LA\",\"US-NC\",\"US-ND\",\"US-NJ\",\"US-TN\"],\"launchYear\":null,\"type\":\"Public\"},{\"date\":\"2024-03-29\",\"localName\":\"Good Friday\",\"name\":\"Good Friday\",\"countryCode\":\"US\",\"fixed\":false,\"global\":false,\"counties\":[\"US-TX\"],\"launchYear\":null,\"type\":\"Optional\"},{\"date\":\"2024-05-27\",\"localName\":\"Memorial Day\",\"name\":\"Memorial Day\",\"countryCode\":\"US\",\"fixed\":false,\"global\":true,\"counties\":null,\"launchYear\":null,\"type\":\"Public\"},{\"date\":\"2024-06-19\",\"localName\":\"Juneteenth\",\"name\":\"Juneteenth\",\"countryCode\":\"US\",\"fixed\":false,\"global\":true,\"counties\":null,\"launchYear\":2021,\"type\":\"Public\"},{\"date\":\"2024-07-04\",\"localName\":\"Independence Day\",\"name\":\"Independence Day\",\"countryCode\":\"US\",\"fixed\":false,\"global\":true,\"counties\":null,\"launchYear\":null,\"type\":\"Public\"},{\"date\":\"2024-09-02\",\"localName\":\"Labor Day\",\"name\":\"Labour Day\",\"countryCode\":\"US\",\"fixed\":false,\"global\":true,\"counties\":null,\"launchYear\":null,\"type\":\"Public\"},{\"date\":\"2024-10-14\",\"localName\":\"Columbus Day\",\"name\":\"Columbus Day\",\"countryCode\":\"US\",\"fixed\":false,\"global\":false,\"counties\":[\"US-AL\",\"US-AZ\",\"US-CO\",\"US-CT\",\"US-GA\",\"US-ID\",\"US-IL\",\"US-IN\",\"US-IA\",\"US-KS\",\"US-KY\",\"US-LA\",\"US-ME\",\"US-MD\",\"US-MA\",\"US-MS\",\"US-MO\",\"US-MT\",\"US-NE\",\"US-NH\",\"US-NJ\",\"US-NM\",\"US-NY\",\"US-NC\",\"US-OH\",\"US-OK\",\"US-PA\",\"US-RI\",\"US-SC\",\"US-TN\",\"US-UT\",\"US-VA\",\"US-WV\"],\"launchYear\":null,\"type\":\"Public\"},{\"date\":\"2024-11-11\",\"localName\":\"Veterans Day\",\"name\":\"Veterans Day\",\"countryCode\":\"US\",\"fixed\":false,\"global\":true,\"counties\":null,\"launchYear\":null,\"type\":\"Public\"},{\"date\":\"2024-11-28\",\"localName\":\"Thanksgiving Day\",\"name\":\"Thanksgiving Day\",\"countryCode\":\"US\",\"fixed\":false,\"global\":true,\"counties\":null,\"launchYear\":1863,\"type\":\"Public\"},{\"date\":\"2024-12-25\",\"localName\":\"Christmas Day\",\"name\":\"Christmas Day\",\"countryCode\":\"US\",\"fixed\":false,\"global\":true,\"counties\":null,\"launchYear\":null,\"type\":\"Public\"}]";
        String year = "2024";
        String countryCode = "US";
        when(mockHolidayQueryService.getJSON(eq(year), eq(countryCode))).thenReturn(fakeJsonResult);

        String url = String.format("/api/publicholidays/get?year=%s&countryCode=%s", year, countryCode);

        MvcResult response = mockMvc
                .perform(get(url).contentType("application/json"))
                .andExpect(status().isOk()).andReturn();

        String responseString = response.getResponse().getContentAsString();

        assertEquals(fakeJsonResult, responseString);
    }
}
