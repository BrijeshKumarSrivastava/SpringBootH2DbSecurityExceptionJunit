package com.accenture.springbootexceptionhandlingwithjunit.service;

import com.accenture.springbootexceptionhandlingwithjunit.entity.Employee;
import com.accenture.springbootexceptionhandlingwithjunit.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeService employeeService;
    AutoCloseable autoCloseable;
    Employee employee1,employee2;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        employee1 = new Employee(101,"Brijesh1", "IT1", "GZB1", 11000.0);
        employee2 = new Employee(102, "Brijesh2", "IT2", "GZB2", 12000.0);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testAddEmployee() {
        mock(Employee.class);
        mock(EmployeeRepository.class);

        when(employeeRepository.save(employee1)).thenReturn(employee1);
        assertThat(employeeService.addEmployee(employee1)).isEqualTo(employee1);

    }
    @Test
    void testGetAll() {
        mock(Employee.class);
        mock(EmployeeRepository.class);

        when(employeeRepository.findAll()).thenReturn(new ArrayList<Employee>(
                Collections.singleton(employee1)
        ));

        assertThat(employeeService.getEmployees().get(0).getName()).isEqualTo(employee1.getName());

    }

    @Test
    void testUpdateEmployee() {
        mock(Employee.class);
        int id = 101;
        mock(EmployeeRepository.class);
        when(employeeRepository.findById(id)).thenReturn(Optional.ofNullable(employee1));
        when(employeeRepository.save(any())).thenReturn(employee1);
        assertEquals(employeeService.updateEmployee(employee1.getId(), employee1).getName(), employee1.getName());
    }



    @Test
    void testDeleteEmployee() {
        mock(Employee.class);
        int id = 102;
        when(employeeRepository.findById(id)).thenReturn(Optional.ofNullable(employee2));
        doNothing().when(employeeRepository).delete(employee2);
        employeeService.deleteEmployee(id);
        assertAll(() -> employeeService.deleteEmployee(id));
    }


    @Test
    void testGetEmployeeById() {
        mock(Employee.class);
        mock(EmployeeRepository.class);

        when(employeeRepository.findById(employee1.getId())).
                thenReturn(Optional.of(employee1));

        assertThat(employeeService.getEmployeeById(employee1.getId()).getId()).
                isEqualTo(employee1.getId());
    }
}
