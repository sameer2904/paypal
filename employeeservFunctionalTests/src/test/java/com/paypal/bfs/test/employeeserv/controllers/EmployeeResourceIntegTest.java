package com.paypal.bfs.test.employeeserv.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.employeeserv.EmployeeservApplication;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeeservApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeResourceIntegTest {

  @LocalServerPort
  private int port;



  @Test
  public void testCreateEmployeeResource() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(getEmployee()), headers);

    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/v1/bfs/employees"),
        HttpMethod.POST, entity, String.class);

    final Employee employee = objectMapper.readValue(response.getBody(), Employee.class);

    assertEquals(getEmployee().getFirstName(), employee.getFirstName());
  }

  @Test
  public void testCreateEmployeeResourceNull() {
    ObjectMapper objectMapper = new ObjectMapper();
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<>(null, headers);

    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/v1/bfs/employees"),
        HttpMethod.POST, entity, String.class);

    assertEquals(400, response.getStatusCodeValue());
  }

  @Test
  public void testEmployeeGetById() throws IOException {
    //insert data first
    ObjectMapper objectMapper = new ObjectMapper();
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entityPost = new HttpEntity<>(objectMapper.writeValueAsString(getEmployee()), headers);

    final ResponseEntity<String> exchange = restTemplate.exchange(
        createURLWithPort("/v1/bfs/employees"),
        HttpMethod.POST, entityPost, String.class);


    final Employee employee = objectMapper.readValue(exchange.getBody(), Employee.class);
    //now check if data exists
    HttpEntity<String> entityGet = new HttpEntity<>(null, headers);
    final ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/v1/bfs/employees/" + employee.getId()),
        HttpMethod.GET, entityGet, String.class);
    assertEquals(response.getBody(), exchange.getBody());
  }

  @Test
  public void testEmployeeGetByIdException() throws IOException {
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entityGet = new HttpEntity<>(null, headers);


    final ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/v1/bfs/employees/" + 1001),
        HttpMethod.GET, entityGet, String.class);
    assertEquals(500, response.getStatusCodeValue());
  }

  @Test
  public void testEmployeeGetByIdNullIdException() throws IOException {
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entityGet = new HttpEntity<>(null, headers);


    final ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/v1/bfs/employees/"),
        HttpMethod.GET, entityGet, String.class);
    assertEquals(400, response.getStatusCodeValue());
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }

  private Employee getEmployee() {
    Employee employee = new Employee();
    employee.setDob("04/04/1989");
    employee.setFirstName("sameer");
    employee.setLastName("saurav");
    employee.setId(1);

    Address address = new Address();
    address.setAddressId(1);
    address.setCity("RANCHI");
    address.setCountry("INDIA");
    address.setLine1("11, st 04");
    address.setState("jharkhand");
    address.setZipCode("831005");

    employee.setAddress(address);
    return employee;
  }
}
