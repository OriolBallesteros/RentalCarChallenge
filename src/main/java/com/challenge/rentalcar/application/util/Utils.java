package com.challenge.rentalcar.application.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {

  private static final String DATE_FORMAT = "yyyy-MM-dd";

  public static LocalDate mapStringToLocalDate(String dateString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    return LocalDate.parse(dateString, formatter);
  }

}
