package com.paypal.bfs.test.employeeserv.controllers;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.services.EmployeeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

  @Autowired
  private EmployeeService employeeService;

  @Override
  public ResponseEntity createEmployee(Employee employee) {
    try {
      employee = employeeService.createEmployee(employee);
      return ResponseEntity.status(HttpStatus.OK)
          .body(employee);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("ERROR while inserting employee data. Reason: " + e.getMessage());
    }
  }

  @Override
  public ResponseEntity employeeGetById(String id) {

    if (StringUtils.isEmpty(id)) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    try {
      final Employee employee = employeeService.getEmployeeById(id);
      return ResponseEntity.status(HttpStatus.OK)
          .body(employee);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("ERROR while getting employee data. Reason: " + e.getMessage());
    }
  }
}
