package com.challenge.rentalcar.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.challenge.rentalcar.domain.service.PriceCalculator;
import com.challenge.rentalcar.domain.vo.CarType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PriceCalculatorImplTest {

  private final PriceCalculator priceCalculator = new PriceCalculatorImpl();

  @Test
  @DisplayName("getPrice method, when type is Premium and for 2 days, returns expected result")
  void getPrice_whenPremiumForTwoDays_returnExpectedValue() {
    final CarType type = CarType.PREMIUM;
    final Integer totalDays = 2;
    final Double expected = 600.0;

    final Double result = priceCalculator.getPrice(type, totalDays);

    assertNotNull(result);
    assertEquals(expected, result);
  }

  @Test
  @DisplayName("getPrice method, when type is Premium and for 10 days, returns expected result")
  void getPrice_whenPremiumForTenDays_returnExpectedValue() {
    final CarType type = CarType.PREMIUM;
    final Integer totalDays = 10;
    final Double expected = 3000.0;

    final Double result = priceCalculator.getPrice(type, totalDays);

    assertNotNull(result);
    assertEquals(expected, result);
  }

  @Test
  @DisplayName("getPrice method, when type is suv and for 2 days, returns expected result")
  void getPrice_whenSUVForSixDays_returnExpectedValue() {
    final CarType type = CarType.SUV;
    final Integer totalDays = 2;
    final Double expected = 300.0;

    final Double result = priceCalculator.getPrice(type, totalDays);

    assertNotNull(result);
    assertEquals(expected, result);
  }

  @Test
  @DisplayName("getPrice method, when type is suv and for 9 days, returns expected result")
  void getPrice_whenSUVForNineDays_returnExpectedValue() {
    final CarType type = CarType.SUV;
    final Integer totalDays = 9;
    final Double expected = 1290.0;

    final Double result = priceCalculator.getPrice(type, totalDays);

    assertNotNull(result);
    assertEquals(expected, result);
  }

  @Test
  @DisplayName("getPrice method, when type is suv and for 35 days, returns expected result")
  void getPrice_whenSUVForThirtyFiveDays_returnExpectedValue() {
    final CarType type = CarType.SUV;
    final Integer totalDays = 35;
    final Double expected = 4260.0;

    final Double result = priceCalculator.getPrice(type, totalDays);

    assertNotNull(result);
    assertEquals(expected, result);
  }

  @Test
  @DisplayName("getPrice method, when type is small and for 6 days, returns expected result")
  void getPrice_whenSmallForThirtySixDays_returnExpectedValue() {
    final CarType type = CarType.SMALL;
    final Integer totalDays = 6;
    final Double expected = 300.0;

    final Double result = priceCalculator.getPrice(type, totalDays);

    assertNotNull(result);
    assertEquals(expected, result);
  }

  @Test
  @DisplayName("getPrice method, when type is small and for 10 days, returns expected result")
  void getPrice_whenSmallForTenDays_returnExpectedValue() {
    final CarType type = CarType.SMALL;
    final Integer totalDays = 10;
    final Double expected = 440.0;

    final Double result = priceCalculator.getPrice(type, totalDays);

    assertNotNull(result);
    assertEquals(expected, result);
  }
}