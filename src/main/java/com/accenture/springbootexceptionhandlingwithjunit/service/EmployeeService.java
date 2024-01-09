package com.accenture.springbootexceptionhandlingwithjunit.service;

import com.accenture.springbootexceptionhandlingwithjunit.entity.Employee;
import com.accenture.springbootexceptionhandlingwithjunit.exception.EmployeeNotFoundException;
import com.accenture.springbootexceptionhandlingwithjunit.exception.EmptyEmployeeListException;
import com.accenture.springbootexceptionhandlingwithjunit.exception.MissingInputValueException;
import com.accenture.springbootexceptionhandlingwithjunit.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//@EnableWebSecurity
//@EnableMethodSecurity
public class EmployeeService {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    EmployeeRepository employeeRepository;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<Employee>();
        employees = employeeRepository.findAll();
        //employeeRepository.findAll().forEach(employee -> employees.add(employee));
        if(employees.isEmpty()) {
            LOGGER.info("Could not find any employee in db now.");
            throw new EmptyEmployeeListException("No employee found in DB now, please add first.");
        }
        LOGGER.info("Below employee(s) found now." + employees );
        return employees;

    }
    @PreAuthorize("hasRole('ADMIN')")
    public Employee addEmployee(Employee employee) {
        if(employee.getName().isEmpty() || employee.getDepartment().isEmpty() || employee.getAddress().isEmpty()) {
            LOGGER.info("Missing some input so could not add employee with: name={}", employee.getName());
            throw new MissingInputValueException("Missing input value, please provide it.");
        }
        LOGGER.info("Employee added with: name={}", employee.getName());
       return employeeRepository.save(employee);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public int deleteEmployee(int id) {
        if(employeeRepository.findById(id).isEmpty()) {
            LOGGER.info("Could not find employee to delete with: id={}", id);
            throw new EmployeeNotFoundException("Requested Employee does not exist");
        }
        employeeRepository.deleteById(id);
        LOGGER.info("Employee deleted with: id={}", id);
        return id;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
        public Employee getEmployeeById(int id) {
        if(employeeRepository.findById(id).isEmpty()) {
            LOGGER.info("Could not find employee with: id={}", id);
            throw new EmployeeNotFoundException("Requested Employee does not exist");
        }
        LOGGER.info("Employee found with: id={}", id);
        return employeeRepository.findById(id).get();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Employee updateEmployee(int employeeId,Employee employee) {
        Employee existingEmployee = employeeRepository.findById(employeeId).orElse(null);
        if(existingEmployee != null && existingEmployee.getId()!=0) {
            existingEmployee.setName(employee.getName());
            existingEmployee.setAddress(employee.getAddress());
            existingEmployee.setDepartment(employee.getDepartment());
            existingEmployee.setExpectedSalary(employee.getExpectedSalary());
            LOGGER.info("Employee updated with: id={}", employeeId);
            return employeeRepository.save(existingEmployee);
        }
        else{
            LOGGER.info("Could not find employee to update with: id={}", employeeId);
            throw new EmployeeNotFoundException("Requested Employee does not exist");
        }
    }

}

