package com.paypal.bfs.test.employeeserv.api;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.core.MediaType;

/**
 * Interface for employee resource operations.
 */
public interface EmployeeResource {



    /**
     * Retrieves the {@link Employee} resource by id.
     *
     * @param id employee id.
     * @return {@link Employee} resource.
     */
    @RequestMapping(value = "/v1/bfs/employees/{id}", consumes = MediaType.APPLICATION_JSON,produces = MediaType.APPLICATION_JSON)
    ResponseEntity<Employee> employeeGetById(@PathVariable("id") String id);

    /**
     * Create a new employee
     * @param employee
     * @return {@link Employee} resource.
     */
    @RequestMapping("/v1/bfs/employees")
    ResponseEntity<Employee> createEmployee(@RequestBody  Employee employee);
}
