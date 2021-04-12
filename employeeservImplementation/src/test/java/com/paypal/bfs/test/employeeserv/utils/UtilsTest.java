package com.paypal.bfs.test.employeeserv.utils;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.dao.models.AddressDB;
import com.paypal.bfs.test.employeeserv.dao.models.EmployeeDB;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UtilsTest {

  @Test
  public void convertDbToRestEmployeeTest() throws IOException {
    final Employee employee = Utils.deepCopy(getEmployeeDB(), Employee.class);
    assertEquals(1, (int) employee.getId());
  }

  @Test
  public void deepCopyNullTest() throws IOException {
    assertNull(Utils.deepCopy(null, Employee.class));
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
