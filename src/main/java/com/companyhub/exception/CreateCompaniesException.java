package com.companyhub.exception;

public class CreateCompaniesException extends CompanyHubException {

  public CreateCompaniesException(String message, Throwable cause) {
    super("Error during the creation of companies: " + message, cause);
  }

  public CreateCompaniesException(Throwable cause) {
    super("Error during the creation of companies: " + cause.getMessage(), cause);
  }
}
