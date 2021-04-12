package com.paypal.bfs.test.employeeserv.dao;

import com.paypal.bfs.test.employeeserv.dao.models.EmployeeDB;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for Employee data table
 */
public interface EmployeeRepository extends CrudRepository<EmployeeDB, Integer> {
}
