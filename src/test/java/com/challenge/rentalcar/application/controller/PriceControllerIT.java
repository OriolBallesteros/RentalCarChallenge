package com.challenge.rentalcar.application.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.challenge.rentalcar.domain.dto.RentalRequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerIT {

  private static final String RENTAL_URL = "/pricing/{carModel}";
  private static final String SURCHARGE_URL = "/pricing/surcharge/{carModel}";

  private static final String PREMIUM_MODEL = "bmw"; // In-line variable is a syntax improvement but slows reading and understanding
  private static final String SUV_MODEL = "kia";
  private static final String SMALL_MODEL = "seat";
  private static final String NON_VALID_MODEL = "non-valid";
  private static final String NOT_FOUND_MODEL = "nissan";

  @Autowired
  private MockMvc mockMvc;

  //  GetRentalPrice testing
  @Test
  @DisplayName("getRentalPrice, when it works, should return expected status")
  void getRentalPrice_whenOk_shouldReturnExpectedStatus() throws Exception {
    final String model = PREMIUM_MODEL;
    final String startDate = "2022-12-25";
    final String plannedReturnDate = "2022-12-31";

    mockMvc.perform(get(RENTAL_URL, model)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(mapDTOtoJSON(getRentalRequestDTO(startDate, plannedReturnDate))))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("getRentalPrice, when model not found, should return expected response")
  void getRentalPrice_whenModelNotFound_shouldReturnExpectedResponse() throws Exception {
    final String model = NON_VALID_MODEL;
    final String startDate = "2022-12-25";
    final String plannedReturnDate = "2022-12-31";

    mockMvc.perform(get(RENTAL_URL, model)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(mapDTOtoJSON(getRentalRequestDTO(startDate, plannedReturnDate))))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isNotFound())
        .andExpect(result -> assertEquals("CarModel not found for given params.",
            Objects.requireNonNull(result.getResolvedException()).getMessage()));
  }

  @Test
  @DisplayName("getRentalPrice, when car not found, should return expected response")
  void getRentalPrice_whenCarNotFound_shouldReturnExpectedResponse() throws Exception {
    final String model = NOT_FOUND_MODEL;
    final String startDate = "2022-12-25";
    final String plannedReturnDate = "2022-12-31";

    mockMvc.perform(get(RENTAL_URL, model)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(mapDTOtoJSON(getRentalRequestDTO(startDate, plannedReturnDate))))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isNotFound())
        .andExpect(result -> assertEquals("Car entity not found for given params.",
            Objects.requireNonNull(result.getResolvedException()).getMessage()));
  }

  @Test
  @DisplayName("getRentalPrice, when start date is after planned return date, should return expected response")
  void getRentalPrice_whenImpossibleDatesSelection_shouldReturnExpectedResponse() throws Exception {
    final String model = PREMIUM_MODEL;
    final String startDate = "2022-12-31";
    final String plannedReturnDate = "2022-12-25";

    mockMvc.perform(get(RENTAL_URL, model)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(mapDTOtoJSON(getRentalRequestDTO(startDate, plannedReturnDate))))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isNotAcceptable())
        .andExpect(result -> assertEquals("Set dates are not possible.",
            Objects.requireNonNull(result.getResolvedException()).getMessage()));
  }

  @Test
  @DisplayName("getRentalPrice, when premium model rented for 10 days, should return expected result price")
  void getRentalPrice_whenPremiumModelRentedForTenDays_shouldReturnExpectedResult() throws Exception {
    final String model = PREMIUM_MODEL;
    final String startDate = "2022-12-10";
    final String plannedReturnDate = "2022-12-20";
    final Double expected = 3000.0;

    final MvcResult result =
        mockMvc.perform(get(RENTAL_URL, model)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(mapDTOtoJSON(getRentalRequestDTO(startDate, plannedReturnDate))))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andReturn();

    assertNotNull(result);
    final Double response = mapResultToInteger(result);
    assertEquals(expected, response);
  }

  @Test
  @DisplayName("getRentalPrice, when SUV model rented for 9 days, should return expected result price")
  void getRentalPrice_whenSUVModelRentedFor9Days_shouldReturnExpectedResult() throws Exception {
    final String model = SUV_MODEL;
    final String startDate = "2022-12-10";
    final String plannedReturnDate = "2022-12-19";
    final Double expected = 1290.0;

    final MvcResult result =
        mockMvc.perform(get(RENTAL_URL, model)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(mapDTOtoJSON(getRentalRequestDTO(startDate, plannedReturnDate))))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andReturn();

    assertNotNull(result);
    final Double response = mapResultToInteger(result);
    assertEquals(expected, response);
  }

  @Test
  @DisplayName("getRentalPrice, when SUV model rented for 2 days, should return expected result price")
  void getRentalPrice_whenSUVModelRentedFor2Days_shouldReturnExpectedResult()
      throws Exception {
    final String model = SUV_MODEL;
    final String startDate = "2022-12-10";
    final String plannedReturnDate = "2022-12-12";
    final Double expected = 300.0;

    final MvcResult result =
        mockMvc.perform(get(RENTAL_URL, model)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(mapDTOtoJSON(getRentalRequestDTO(startDate, plannedReturnDate))))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andReturn();

    assertNotNull(result);
    final Double response = mapResultToInteger(result);
    assertEquals(expected, response);
  }

  @Test
  @DisplayName("getRentalPrice, when small model rented for 10 days, should return expected result price")
  void getRentalPrice_whenSmallModelRentedFor10Days_shouldReturnExpectedResult()
      throws Exception {
    final String model = SMALL_MODEL;
    final String startDate = "2022-12-10";
    final String plannedReturnDate = "2022-12-20";
    final Double expected = 440.0;

    final MvcResult result =
        mockMvc.perform(get(RENTAL_URL, model)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(mapDTOtoJSON(getRentalRequestDTO(startDate, plannedReturnDate))))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andReturn();

    assertNotNull(result);
    final Double response = mapResultToInteger(result);
    assertEquals(expected, response);
  }

  //  GetSurcharge testing
  @Test
  @DisplayName("getSurcharge, when it works, should return expected status")
  void getSurcharge_whenOk_shouldReturnExpectedStatus() throws Exception {
    final String model = PREMIUM_MODEL;
    final String effectiveReturnDate = "2022-12-31";

    mockMvc.perform(get(SURCHARGE_URL, model)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(effectiveReturnDate))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("getSurcharge, when model not found, should return expected response")
  void getSurcharge_whenModelNotFound_shouldReturnExpectedResponse()
      throws Exception {
    final String model = NON_VALID_MODEL;
    final String effectiveReturnDate = "2022-11-10";

    mockMvc.perform(get(SURCHARGE_URL, model)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(effectiveReturnDate))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isNotFound())
        .andExpect(result -> assertEquals("CarModel not found for given params.",
            Objects.requireNonNull(result.getResolvedException()).getMessage()));
  }

  @Test
  @DisplayName("getSurcharge, when car not found, should return expected response")
  void getSurcharge_whenCarNotFound_shouldReturnExpectedResponse()
      throws Exception {
    final String model = NOT_FOUND_MODEL;
    final String effectiveReturnDate = "2022-11-10";

    mockMvc.perform(get(SURCHARGE_URL, model)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(effectiveReturnDate))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isNotFound())
        .andExpect(result -> assertEquals("Car entity not found for given params.",
            Objects.requireNonNull(result.getResolvedException()).getMessage()));
  }

  @Test
  @DisplayName("getSurcharge, when surcharge is not yet to be applied, should return expected response")
  void getSurcharge_whenNoSurchargeIsToBeApplied_shouldReturnExpectedResponse()
      throws Exception {
    final String model = SMALL_MODEL;
    final String effectiveReturnDate = "2022-11-10";

    mockMvc.perform(get(SURCHARGE_URL, model)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(effectiveReturnDate))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isNotAcceptable())
        .andExpect(result -> assertEquals("No surcharge is due to be applied.",
            Objects.requireNonNull(result.getResolvedException()).getMessage()));
  }

  @Test
  @DisplayName("getSurcharge, when SUV model passed for 1 day, should return expected result price")
  void getSurcharge_whenSmallModelRentedFor10Days_shouldReturnExpectedResult() throws Exception {
    final String model = SUV_MODEL;
    final String effectiveReturnDate = "2022-11-21";
    final Double expected = 180.0;

    final MvcResult result =
        mockMvc.perform(get(SURCHARGE_URL, model)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(effectiveReturnDate))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andReturn();

    assertNotNull(result);
    final Double response = mapResultToInteger(result);
    assertEquals(expected, response);
  }

  @Test
  @DisplayName("getSurcharge, when premium model passed for 2 day, should return expected result price")
  void getSurcharge_whenPremiumModelDays_shouldReturnExpectedResult() throws Exception {
    final String model = PREMIUM_MODEL;
    final String effectiveReturnDate = "2022-11-17";
    final Double expected = 720.0;

    final MvcResult result =
        mockMvc.perform(get(SURCHARGE_URL, model)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(effectiveReturnDate))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andReturn();

    assertNotNull(result);
    final Double response = mapResultToInteger(result);
    assertEquals(expected, response);
  }

  private RentalRequestDTO getRentalRequestDTO(final String startDate,
      final String plannedReturnDate) {
    final RentalRequestDTO dto = new RentalRequestDTO();
    dto.setStartDate(startDate);
    dto.setPlannedReturnDate(plannedReturnDate);
    return dto;
  }

  private String mapDTOtoJSON(final RentalRequestDTO dto) throws JsonProcessingException {
    final ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(dto);
  }

  private Double mapResultToInteger(final MvcResult result) throws Exception {
    final ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());

    return mapper.readValue(result.getResponse().getContentAsString(), Double.class);
  }


}
