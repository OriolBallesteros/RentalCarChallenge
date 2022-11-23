package com.challenge.rentalcar.domain.exception;

/**
 * Generic custom exception that must be thrown when not found client entity.
 */
public class NotFoundClientException extends RuntimeException {

  private static final long serialVersionUID = 7055940268271169879L;

  /**
   * Instances a new object with the message by default.
   */
  public NotFoundClientException() { this("Client entity not found for given params."); }

  /**
   * Instances a new object with given message.
   *
   * @param message Exception's message.
   */
  public NotFoundClientException(final String message) { super(message); }
}
