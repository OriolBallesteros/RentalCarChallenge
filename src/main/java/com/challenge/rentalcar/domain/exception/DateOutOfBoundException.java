package com.challenge.rentalcar.domain.exception;

/**
 * Generic custom exception that must be thrown when set dates are impossible.
 */
public class DateOutOfBoundException extends RuntimeException {

  private static final long serialVersionUID = 2209032947163142256L;

  /**
   * Instances a new object with the message by default.
   */
  public DateOutOfBoundException() { this("Set dates are not possible."); }

  /**
   * Instances a new object with given message.
   *
   * @param message Exception's message.
   */
  public DateOutOfBoundException(final String message) { super(message); }
}
