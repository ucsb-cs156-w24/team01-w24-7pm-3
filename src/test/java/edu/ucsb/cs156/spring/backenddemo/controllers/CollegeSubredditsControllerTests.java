package edu.ucsb.cs156.spring.backenddemo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import edu.ucsb.cs156.spring.backenddemo.services.CollegeSubredditQueryService;
import edu.ucsb.cs156.spring.backenddemo.services.HolidayQueryService;


import static org.mockito.ArgumentMatchers.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




@WebMvcTest(value = CollegeSubredditsController.class)
public class CollegeSubredditsControllerTests {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  CollegeSubredditQueryService mockCollegeSubredditQueryService;

  @Test
  public void test_getCollegeSubreddits() throws Exception {
  
    String fakeJsonResult="{ \"fake\" : \"result\" }";
    when(mockCollegeSubredditQueryService.getJSON()).thenReturn(fakeJsonResult);

    String url = String.format("/api/collegesubreddits/get");

    MvcResult response = mockMvc
        .perform( get(url).contentType("application/json"))
        .andExpect(status().isOk()).andReturn();

    String responseString = response.getResponse().getContentAsString();

    assertEquals(fakeJsonResult, responseString);
  }

}