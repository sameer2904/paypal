package com.paypal.bfs.test.employeeserv.services;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.dao.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.dao.models.AddressDB;
import com.paypal.bfs.test.employeeserv.dao.models.EmployeeDB;
import com.paypal.bfs.test.employeeserv.services.impl.EmployeeServiceImpl;
import com.paypal.bfs.test.employeeserv.utils.exceptions.DataNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class EmployeeServiceTest {

  @TestConfiguration
  static class EmployeeServiceImplTestContextConfiguration {

    @Bean
    public EmployeeService employeeService() {
      return new EmployeeServiceImpl();
    }
  }

  @MockBean
  private EmployeeRepository employeeRepository;

  @Autowired
  private EmployeeServiceImpl employeeService;

  @Test
  public void getEmployeeByIdTest() throws Exception {
    given(employeeRepository.findById(Integer.valueOf("1"))).willReturn(Optional.of(getEmployeeDB()));

    final Employee employee= employeeService.getEmployeeById("1");
    assertTrue(employee.getId() == 1);
  }

  @Test(expected = DataNotFoundException.class)
  public void getEmployeeByIdNoDataTest() throws Exception {
    given(employeeRepository.findById(Mockito.anyInt())).willReturn(Optional.empty());

    final Employee employee= employeeService.getEmployeeById("1");
  }

  @Test(expected = Exception.class)
  public void getEmployeeByIdExceptionTest() throws Exception {
    given(employeeRepository.findById(Mockito.anyInt())).willThrow(new Exception());

    final Employee employee= employeeService.getEmployeeById("1");
  }

  @Test
  public void createEmployeeTest() throws Exception {
    final Employee employee1 = getEmployee();
    final EmployeeDB employeeDB = getEmployeeDB();
    given(employeeRepository.save(employeeDB)).willReturn(employeeDB);

    final Employee employee= employeeService.createEmployee(employee1);
    assertEquals(1, (int) employee.getId());
  }

  @Test(expected = Exception.class)
  public void createEmployeeTestExceptionTest() throws Exception {
    final Employee employee1 = getEmployee();
    final EmployeeDB employeeDB = getEmployeeDB();
    given(employeeRepository.save(employeeDB)).willThrow(new Exception());

    final Employee employee= employeeService.createEmployee(employee1);
  }

  private EmployeeDB getEmployeeDB() {
    EmployeeDB employeeDB = new EmployeeDB();
    employeeDB.setDob("04/04/1989");
    employeeDB.setFirstName("sameer");
    employeeDB.setLastName("saurav");
    employeeDB.setId(1);

    AddressDB addressDB = new AddressDB();
    addressDB.setAddressId(1);
    addressDB.setCity("RANCHI");
    addressDB.setCountry("INDIA");
    addressDB.setLine1("11, st 04");
    addressDB.setState("jharkhand");
    addressDB.setZipCode("831005");

    employeeDB.setAddress(addressDB);
    return employeeDB;
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
