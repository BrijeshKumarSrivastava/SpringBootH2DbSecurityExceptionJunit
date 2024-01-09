package com.accenture.springbootexceptionhandlingwithjunit.repository;

import com.accenture.springbootexceptionhandlingwithjunit.entity.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;
    Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee(100, "brijesh", "IT", "Ghaziabad", 10000.0);
        employeeRepository.save(employee);
    }

    @AfterEach
    void tearDown() {
        employee = null;
        employeeRepository.deleteAll();
    }
//    @Test
//    void testFindByName_Found()
//    {
//        List<Employee> employees = employeeRepository.findByName("brijesh");
//        assertThat(employees.get(0).getId()).isEqualTo(employee.getId());
//        assertThat(employees.get(0).getName())
//                .isEqualTo(employee.getName());
//    }

    // Test case FAILURE
//    @Test
//    void testFindByName_NotFound()
//    {
//        List<Employee> employees = employeeRepository.findByName("xyz");
//        assertThat(employees.isEmpty()).isTrue();
//    }

}
