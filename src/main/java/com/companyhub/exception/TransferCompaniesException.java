package com.companyhub.exception;

public class TransferCompaniesException extends CompanyHubException {

  public TransferCompaniesException(String message, Throwable cause) {
    super("Error during transferring companies: " + message, cause);
  }

  public TransferCompaniesException(Throwable cause) {
    super("Error during transferring companies: " + cause.getMessage(), cause);
  }
}
