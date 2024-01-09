package com.accenture.springbootexceptionhandlingwithjunit.controller;

import com.accenture.springbootexceptionhandlingwithjunit.entity.Employee;
import com.accenture.springbootexceptionhandlingwithjunit.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    //creating post mapping that post the employee detail in the database
    @PostMapping("/addEmployee")
    public Employee addEmployee(@RequestBody @Valid Employee employee)
        {
        employee = employeeService.addEmployee(employee);
        return employee;
        }


    //creating a get mapping that retrieves all the employees detail from the database
    @GetMapping("/getAll")
    private List<Employee> getAllEmployees()
        {
            return employeeService.getEmployees();
        }


    //creating a get mapping that retrieves the detail of a specific employee
    @GetMapping("/get/{id}")
    private Employee getEmployeeById(@PathVariable int id)
       {
           return employeeService.getEmployeeById(id);
       }

    //creating a put mapping that updates a specific employee details
    @PutMapping("/update/{id}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable int id) {
        return employeeService.updateEmployee(id, employee);
    }

    //creating a delete mapping that deletes a specific employee
    @DeleteMapping("/delete/{id}")
    private int deleteEmployee(@PathVariable int id)
    {
      return employeeService.deleteEmployee(id);
    }

}

