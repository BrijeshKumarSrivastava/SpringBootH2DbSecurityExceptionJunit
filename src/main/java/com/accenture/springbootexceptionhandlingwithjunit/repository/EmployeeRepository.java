package com.accenture.springbootexceptionhandlingwithjunit.repository;

import com.accenture.springbootexceptionhandlingwithjunit.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
//  List<Employee> findByName(String name);
}


