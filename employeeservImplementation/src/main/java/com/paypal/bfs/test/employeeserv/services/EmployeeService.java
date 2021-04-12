package com.paypal.bfs.test.employeeserv.services;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.utils.exceptions.DataNotFoundException;

import java.io.IOException;

public interface EmployeeService {
  /**
   * Returns Employee for a given id. Throws  {@link DataNotFoundException} DataNotFoundException if no data found.
   * @param id
   * @return {@link Employee} resource.
   * @throws Exception
   */
  Employee getEmployeeById(String id) throws Exception;

  /**
   * creates a employee entry in the db.
   * @param employee
   * @return
   * @throws IOException
   */
  Employee createEmployee(Employee employee) throws IOException;
}
