package com.paypal.bfs.test.employeeserv.utils.exceptions;

/**
 * Exception class for handling no data found cases
 */
public class DataNotFoundException extends Exception {

  private static final long serialVersionUID = 2524686849100170713L;

  public DataNotFoundException(String id) {
    super("No data found for id: " + id);
  }

}
