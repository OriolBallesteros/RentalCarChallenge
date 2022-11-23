package com.challenge.rentalcar.domain.exception;

/**
 * Generic custom exception that must be thrown when not found car entity.
 */
public class NotFoundCarException extends RuntimeException {

  private static final long serialVersionUID = 8064631422562209032L;

  /**
   * Instances a new object with the message by default.
   */
  public NotFoundCarException() { this("Car entity not found for given params."); }

  /**
   * Instances a new object with given message.
   *
   * @param message Exception's message.
   */
  public NotFoundCarException(final String message) { super(message); }
}
