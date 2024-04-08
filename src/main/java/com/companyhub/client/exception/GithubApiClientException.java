package com.companyhub.client.exception;

public class GithubApiClientException extends RuntimeException {

  public GithubApiClientException(String message, Throwable cause) {
    super(message, cause);
  }

  public GithubApiClientException(String message) {
    super(message);
  }
}
