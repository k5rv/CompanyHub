package com.companyhub.mapper;

import com.companyhub.exception.CompanyMapperException;
import com.companyhub.model.Company;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CompanyMapper {

  String[] HEADERS = {"Name", "NumberOfEmployees", "NumberOfCustomers", "Country"};

  default String toCsv(List<Company> companies) {
    try (StringWriter stringWriter = new StringWriter();
        CSVPrinter printer =
            new CSVPrinter(stringWriter, CSVFormat.DEFAULT.builder().setHeader(HEADERS).build())) {
      for (Company company : companies) {
        printer.printRecord(
            company.getName(),
            company.getEmployeesTotal(),
            company.getCustomersTotal(),
            company.getCountry());
      }
      return stringWriter.toString().trim();
    } catch (IOException e) {
      throw new CompanyMapperException("Error while converting companies to CSV format.", e);
    }
  }

  default List<Company> toCompanies(String companiesCsv) {
    List<Company> companies = new ArrayList<>();
    try (StringReader stringReader = new StringReader(companiesCsv);
        CSVParser csvParser =
            CSVFormat.DEFAULT
                .builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build()
                .parse(stringReader)) {
      for (CSVRecord csvRecord : csvParser) {
        String name = csvRecord.get("Name");
        Integer numberOfEmployees = Integer.parseInt(csvRecord.get("NumberOfEmployees"));
        Integer numberOfCustomers = Integer.parseInt(csvRecord.get("NumberOfCustomers"));
        String country = csvRecord.get("Country");
        Company company =
            Company.builder()
                .name(name)
                .employeesTotal(numberOfEmployees)
                .customersTotal(numberOfCustomers)
                .country(country)
                .build();
        companies.add(company);
      }
      return companies;
    } catch (IOException | NumberFormatException e) {
      throw new CompanyMapperException("Error while converting CSV to list of companies.", e);
    }
  }
}
