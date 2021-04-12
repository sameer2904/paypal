package com.paypal.bfs.test.employeeserv.dao.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * Employee table Data class
 */
@Table(name = "employee")
@Entity
@Data
public class EmployeeDB {
  @Id
  @GeneratedValue
  @JsonProperty("id")
  private Integer id;

  @Column(name = "first_name")
  @JsonProperty("first_name")
  private String firstName;

  @Column(name = "last_name")
  @JsonProperty("last_name")
  private String lastName;

  @Column(name = "dob")
  @JsonProperty("dob")
  private String dob;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id")
  private AddressDB address;
}
