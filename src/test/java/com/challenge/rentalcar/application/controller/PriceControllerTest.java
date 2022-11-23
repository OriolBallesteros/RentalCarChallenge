package com.challenge.rentalcar.application.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.challenge.rentalcar.domain.dto.RentalRequestDTO;
import com.challenge.rentalcar.domain.service.CarPricingService;
import com.challenge.rentalcar.domain.service.CarSurchargeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

  @Mock
  private CarPricingService carPricingService;

  @Mock
  private CarSurchargeService carSurchargeService;

  @InjectMocks
  private PriceController controller;

  @Test
  @DisplayName("getRentalPrice method should make expected internal calls")
  void getRentalPrice_shouldMakeExpectedCalls() {
    final String model = "BMW";
    final String startDate = "2022-12-25";
    final String plannedReturnDate = "2022-12-31";
    final Double expected = 9.0;
    mockServiceGetRentalPrice(expected);

    controller.getRentalPrice(model, getRentalRequestDTO(startDate, plannedReturnDate));

    verify(carPricingService, only()).getRentalPrice(eq(model), eq(startDate),
        eq(plannedReturnDate));
  }

  @Test
  @DisplayName("getRentalPrice, when works, should return expected value")
  void getRentalPrice_whenOk_shouldReturnExpectedValue() {
    final String model = "BMW";
    final String startDate = "2022-12-25";
    final String plannedReturnDate = "2022-12-31";
    final Double expected = 9.0;
    mockServiceGetRentalPrice(expected);

    final Double result = controller.getRentalPrice(model,
        getRentalRequestDTO(startDate, plannedReturnDate));

    assertNotNull(result);
    assertEquals(expected, result);
  }

  @Test
  @DisplayName("getSurcharge method should make expected internal calls")
  void getSurcharge_shouldMakeExpectedCalls() {
    final String model = "BMW";
    final String effectiveReturnDate = "2022-11-31";
    final Double expected = 9.0;
    mockServiceGetSurcharge(expected);

    controller.getSurcharge(model, effectiveReturnDate);

    verify(carSurchargeService, only()).getSurcharge(eq(model), eq(effectiveReturnDate));
  }

  @Test
  @DisplayName("getSurcharge, when works, should return expected value")
  void getSurcharge_whenOk_shouldReturnExpectedValue() {
    final String model = "BMW";
    final String effectiveReturnDate = "2022-11-31";
    final Double expected = 9.0;
    mockServiceGetSurcharge(expected);

    final Double result = controller.getSurcharge(model, effectiveReturnDate);

    assertNotNull(result);
    assertEquals(expected, result);
  }

  private void mockServiceGetRentalPrice(final Double expected) {
    when(carPricingService.getRentalPrice(anyString(), anyString(), anyString()))
        .thenReturn(expected);
  }

  private void mockServiceGetSurcharge(final Double expected) {
    when(carSurchargeService.getSurcharge(anyString(), anyString()))
        .thenReturn(expected);
  }

  private RentalRequestDTO getRentalRequestDTO(final String startDate,
      final String plannedReturnDate) {
    final RentalRequestDTO dto = new RentalRequestDTO();
    dto.setStartDate(startDate);
    dto.setPlannedReturnDate(plannedReturnDate);
    return dto;
  }

}