package com.paypal.bfs.test.employeeserv.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.dao.models.EmployeeDB;
import org.springframework.beans.BeanUtils;

import java.io.IOException;

public class Utils {

  /**
   * Deep copy of a complex nested object using serialization.
   * @param input
   * @param outputClass
   * @param <T>
   * @return
   * @throws IOException
   */
  public static <T> T deepCopy(Object input, Class<T> outputClass) throws IOException {
    if (input == null) {
      return null;
    }

    ObjectMapper objectMapper = new ObjectMapper();
    T output = objectMapper
        .readValue(objectMapper.writeValueAsString(input), outputClass);
    return output;
  }
}
