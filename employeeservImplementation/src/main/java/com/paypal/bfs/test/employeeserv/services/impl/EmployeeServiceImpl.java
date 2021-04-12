package com.paypal.bfs.test.employeeserv.services.impl;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.dao.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.dao.models.EmployeeDB;
import com.paypal.bfs.test.employeeserv.services.EmployeeService;
import com.paypal.bfs.test.employeeserv.utils.Utils;
import com.paypal.bfs.test.employeeserv.utils.exceptions.DataNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Override
  public Employee getEmployeeById(String id) throws Exception {
    Optional<EmployeeDB> employeeDBOptional = null;
    try {
      employeeDBOptional = employeeRepository.findById(Integer.valueOf(id));
    } catch (Exception ex) {
      log.error("Error while fetching employee from db", ex);
      throw ex;
    }

    if (!employeeDBOptional.isPresent()) {
      throw new DataNotFoundException(id);
    }
    return Utils.deepCopy(employeeDBOptional.get(), Employee.class);

  }

  @Override
  public Employee createEmployee(Employee employee) throws IOException {
    try {
      return Utils.deepCopy(
          employeeRepository.save(Utils.deepCopy(employee, EmployeeDB.class)), Employee.class);
    } catch (Exception e) {
      log.error("Error while saving employee to db", e);
      throw e;
    }
  }
}
