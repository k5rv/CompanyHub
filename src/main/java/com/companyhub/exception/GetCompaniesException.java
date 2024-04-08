package com.companyhub.exception;

public class GetCompaniesException extends CompanyHubException {

  public GetCompaniesException(String message, Throwable cause) {
    super("Error during getting companies: " + message, cause);
  }

  public GetCompaniesException(Throwable cause) {
    super("Error during getting companies: " + cause.getMessage(), cause);
  }
}
