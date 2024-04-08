package com.companyhub.service;

import static java.nio.charset.StandardCharsets.*;

import com.companyhub.client.GithubApiClient;
import com.companyhub.client.exception.GithubApiForbiddenException;
import com.companyhub.client.exception.GithubApiResourceNotFoundException;
import com.companyhub.client.exception.GithubApiUnauthorizedException;
import com.companyhub.configuration.CompanyHubConfiguration;
import com.companyhub.exception.CreateCompaniesException;
import com.companyhub.exception.GetCompaniesException;
import com.companyhub.exception.TransferCompaniesException;
import com.companyhub.mapper.CompanyMapper;
import com.companyhub.model.Company;
import io.restassured.response.Response;
import java.util.Base64;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CompanyHubService {

  private static final String GITHUB_AUTHENTICATION_ERROR_MESSAGE =
      "Github authentication error: Verify that token is valid";
  private static final String GITHUB_RESOURCE_NOT_FOUND_ERROR_MESSAGE =
      "Github error: Verify that resource exist in the remote repository.";
  private GithubApiClient githubApiClient;
  private CompanyMapper mapper;
  private CompanyHubConfiguration config;

  public void createSourceCompanies(List<Company> companies) {
    if (companies == null || companies.isEmpty()) {
      throw new IllegalArgumentException("Expected number of companies > 0.");
    }
    log.info("Received {} companies", companies.size());
    String path = config.getFilePath();
    String branch = config.getSourceBranch();
    String content = toBase64(mapper.toCsv(companies));
    String message = "Create companies.";
    try {
      Response response = githubApiClient.getContent(path, branch);
      String hash = response.jsonPath().getString("sha");
      githubApiClient.putContent(content, path, branch, hash, message);
      log.info("Updated file \"{}\" content on branch \"{}\"", path, branch);
    } catch (GithubApiUnauthorizedException | GithubApiForbiddenException e) {
      throw new CreateCompaniesException(GITHUB_AUTHENTICATION_ERROR_MESSAGE, e);
    } catch (GithubApiResourceNotFoundException e) {
      throw new CreateCompaniesException(GITHUB_RESOURCE_NOT_FOUND_ERROR_MESSAGE, e);
    } catch (RuntimeException e) {
      throw new CreateCompaniesException(e);
    }
  }

  public List<Company> getSourceCompanies() {
    String filePath = config.getFilePath();
    String branch = config.getSourceBranch();
    return getCompanies(filePath, branch);
  }

  public List<Company> getTransferredCompanies() {
    String filePath = config.getFilePath();
    String branch = config.getTargetBranch();
    return getCompanies(filePath, branch);
  }

  public void transferCompanies() {
    String head = config.getSourceBranch();
    String base = config.getTargetBranch();
    try {
      githubApiClient.mergeBranch(head, base);
      log.info("Transferred companies from branch \"{}\" to branch \"{}\"", head, base);
    } catch (GithubApiUnauthorizedException | GithubApiForbiddenException e) {
      throw new TransferCompaniesException(GITHUB_AUTHENTICATION_ERROR_MESSAGE, e);
    } catch (RuntimeException e) {
      throw new TransferCompaniesException(e);
    }
  }

  private List<Company> getCompanies(String path, String branch) {
    if (path == null || path.isBlank()) {
      throw new IllegalArgumentException("File path should not be empty");
    }
    if (branch == null || branch.isBlank()) {
      throw new IllegalArgumentException("Branch name should not be empty");
    }
    try {
      Response response = githubApiClient.getContent(path, branch);
      String content = response.jsonPath().getString("content").replace("\n", "");
      List<Company> companies = mapper.toCompanies(fromBase64(content));
      log.info("Collected {} companies from branch \"{}\"", companies.size(), branch);
      return companies;
    } catch (GithubApiUnauthorizedException | GithubApiForbiddenException e) {
      throw new GetCompaniesException(GITHUB_AUTHENTICATION_ERROR_MESSAGE, e);
    } catch (GithubApiResourceNotFoundException e) {
      throw new GetCompaniesException(GITHUB_RESOURCE_NOT_FOUND_ERROR_MESSAGE, e);
    } catch (RuntimeException e) {
      throw new GetCompaniesException(e);
    }
  }

  private String toBase64(String content) {
    if (content == null || content.isBlank()) {
      throw new IllegalArgumentException("Content should not be empty");
    }
    return Base64.getEncoder().encodeToString(content.getBytes());
  }

  private String fromBase64(String content) {
    if (content == null || content.isBlank()) {
      throw new IllegalArgumentException("Content should not be empty");
    }
    byte[] decodedCsvContentBytes = Base64.getDecoder().decode(content);
    return new String(decodedCsvContentBytes, UTF_8);
  }
}
