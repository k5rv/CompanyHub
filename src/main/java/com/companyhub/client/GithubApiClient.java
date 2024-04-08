package com.companyhub.client;

import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.MediaType.*;

import com.companyhub.client.dto.*;
import com.companyhub.client.exception.*;
import com.companyhub.configuration.GithubConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GithubApiClient {

  private GithubConfiguration repoConfig;

  public Response getContent(String path, String branch) {
    String requestPath = buildRepositoryContentRequestPath(path);
    RequestSpecification request = buildRepoContentGetRequest(branch);
    Response response;
    try {
      response = request.get(requestPath);
    } catch (RuntimeException e) {
      throw new GithubApiClientException(
          "Exception while making the repo content GET request using RestAssured.", e);
    }
    validateResponseStatus(response);
    return response;
  }

  public void putContent(String content, String path, String branch, String hash, String message) {
    RepoContentPutRequestBody bodyContent =
        RepoContentPutRequestBody.builder().content(content).hash(hash).message(message).build();
    String bodyContentJson = buildJson(bodyContent);
    String requestPath = buildRepositoryContentRequestPath(path);
    RequestSpecification request = buildRepoContentPutRequest(bodyContentJson, branch);
    Response response;
    try {
      response = request.put(requestPath);
    } catch (RuntimeException e) {
      throw new GithubApiClientException(
              "Exception while making the repo content PUT request using RestAssured.", e);
    }
    validateResponseStatus(response);
  }

  public void mergeBranch(String head, String base) {
    MergeBranchPostRequestBody bodyContent = MergeBranchPostRequestBody.builder().head(head).base(base).build();
    String bodyContentJson = buildJson(bodyContent);
    String requestPath = buildMergeBranchRequestPath();
    RequestSpecification request = buildMergeBranchPostRequest(bodyContentJson);
    Response response;
    try {
      response = request.post(requestPath);
      validateResponseStatus(response);
    } catch (RuntimeException e) {
      throw new GithubApiClientException(
              "Exception while making the merge branch POST request using RestAssured.", e);
    }
  }

  private RequestSpecification buildRepoContentGetRequest(String branch) {
    String apiUrl = repoConfig.getApiUrl();
    String token = repoConfig.getToken();
    return RestAssured.given()
        .baseUri(apiUrl)
        .header(AUTHORIZATION, token)
        .accept(ContentType.JSON)
        .queryParam("ref", branch);
  }

  private RequestSpecification buildRepoContentPutRequest(String bodyContent, String branch) {
    String apiUrl = repoConfig.getApiUrl();
    String token = repoConfig.getToken();
    return RestAssured.given()
        .baseUri(apiUrl)
        .header(AUTHORIZATION, token)
        .contentType(APPLICATION_JSON_VALUE)
        .body(bodyContent)
        .queryParam("ref", branch);
  }

  private RequestSpecification buildMergeBranchPostRequest(String bodyContent) {
    String apiUrl = repoConfig.getApiUrl();
    String token = repoConfig.getToken();
    return RestAssured.given()
        .baseUri(apiUrl)
        .header(AUTHORIZATION, token)
        .contentType(ContentType.JSON)
        .body(bodyContent);
  }

  private void validateResponseStatus(Response response) {
    if (response == null) throw new GithubApiClientException("Response is null");
    int status = response.statusCode();
    if (status >= 200 && status <= 204) return;
    String message =
        response.body() == null
            ? "Response body is null"
            : "HTTP status code: " + status + ", response body: " + response.body().toString();
    if (status == 401) {
      throw new GithubApiUnauthorizedException(message);
    }
    if (status == 403) {
      throw new GithubApiForbiddenException(message);
    }
    if (status == 404) {
      throw new GithubApiResourceNotFoundException(message);
    }
    if (status == 409) {
      throw new GithubApiConflictException(message);
    }
    if (status == 422) {
      throw new GithubApiValidationException(message);
    }
  }

  private String buildRepositoryContentRequestPath(String path) {
    String owner = repoConfig.getOwner();
    String repo = repoConfig.getName();
    return "repos/" + owner + "/" + repo + "/contents/" + path;
  }

  private String buildMergeBranchRequestPath() {
    String owner = repoConfig.getOwner();
    String repo = repoConfig.getName();
    return "/repos/" + owner + "/" + repo + "/merges";
  }

  private String buildJson(Object object) {
    try {
      return new ObjectMapper().writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new GithubApiClientException("Exception while parsing object to JSON string.", e);
    }
  }
}
