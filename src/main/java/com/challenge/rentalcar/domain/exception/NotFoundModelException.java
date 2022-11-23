package com.challenge.rentalcar.domain.exception;

/**
 * Generic custom exception that must be thrown when not found car model enum.
 */
public class NotFoundModelException extends RuntimeException {

  private static final long serialVersionUID = 2099558255962178397L;

  /**
   * Instances a new object with the message by default.
   */
  public NotFoundModelException() { this("CarModel not found for given params."); }

  /**
   * Instances a new object with given message.
   *
   * @param message Exception's message.
   */
  public NotFoundModelException(final String message) { super(message); }

}
