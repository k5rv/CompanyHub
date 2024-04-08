package com.companyhub;

import com.companyhub.model.Company;
import com.companyhub.service.CompanyHubService;
import com.companyhub.utils.CompanyHelper;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest()
class CompanyHubServiceTest {

  @Autowired CompanyHubService companyHubService;

  @Test
  void itShouldCreateSourceCompanies() {
    // Given
    List<Company> companies = CompanyHelper.createRandomCompanies(100);
    // When
    companyHubService.createSourceCompanies(companies);
    // Then
    List<Company> sourceCompanies = companyHubService.getSourceCompanies();
    Assertions.assertThat(sourceCompanies).hasSameSizeAs(companies).hasSameElementsAs(companies);
  }

  @Test
  void itShouldTransferCompanies() {
    // Given
    List<Company> companies = CompanyHelper.createRandomCompanies(100);
    companyHubService.createSourceCompanies(companies);
    // When
    companyHubService.transferCompanies();
    // Then
    List<Company> transferredCompanies = companyHubService.getTransferredCompanies();
    Assertions.assertThat(transferredCompanies)
        .hasSameSizeAs(companies)
        .hasSameElementsAs(companies);
  }
}
