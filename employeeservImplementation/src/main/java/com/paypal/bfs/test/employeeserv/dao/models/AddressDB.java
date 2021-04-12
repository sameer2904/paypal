package com.paypal.bfs.test.employeeserv.dao.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * Address Data class
 */
@Table(name = "address")
@Entity
@Data
public class AddressDB {

  @Id
  @GeneratedValue
  private Integer addressId;

  @Column(name = "line1")
  @JsonProperty("line1")
  private String line1;

  @Column(name = "line2")
  @JsonProperty("line2")
  private String line2;

  @Column(name = "city")
  @JsonProperty("city")
  private String city;

  @Column(name = "state")
  @JsonProperty("state")
  private String state;

  @Column(name = "country")
  @JsonProperty("country")
  private String country;

  @Column(name = "zip_code")
  @JsonProperty("zip_code")
  private String zipCode;



}
