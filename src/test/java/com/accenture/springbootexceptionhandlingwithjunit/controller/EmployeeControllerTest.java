package com.accenture.springbootexceptionhandlingwithjunit.controller;

import com.accenture.springbootexceptionhandlingwithjunit.entity.Employee;
import com.accenture.springbootexceptionhandlingwithjunit.exception.MissingInputValueException;
import com.accenture.springbootexceptionhandlingwithjunit.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = EmployeeController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;
    Employee employee1,employee2,employee3;
    List<Employee> employees= new ArrayList<>();

    @BeforeEach
    void setUp() {
        employee1 = new Employee(101, "Brijesh1", "IT1", "Ghaziabad1", 11000.0);
        employee2 = new Employee(102, "Brijesh2", "IT2", "Ghaziabad2", 12000.0);
        employee3 = new Employee(102, "Brijesh2", "", "", 12000.0);
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addEmployee() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(employee1);

        when(employeeService.addEmployee(employee1))
                .thenReturn(employee1);
        this.mockMvc.perform(post("/employee/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getAll() throws Exception {
        when(employeeService.getEmployees()).thenReturn(employees);
        this.mockMvc.perform(get("/employee/getAll"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void deleteEmployee() throws Exception {
        when(employeeService.deleteEmployee(102))
                .thenReturn(102);
        this.mockMvc.perform(delete("/employee/delete/" + 102))
                .andDo(print()).andExpect(status().isOk());

    }

    @Test
    void getEmployeeById() throws Exception {
        when(employeeService.getEmployeeById(employee1.getId()))
                .thenReturn(employee1);
        this.mockMvc.perform(get("/employee/get/" + 101))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void updateEmployee() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(employee1);

        when(employeeService.updateEmployee(employee1.getId(), employee1 ))
                .thenReturn(employee1);
        this.mockMvc.perform(put("/employee/update/" + 101)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }


}
