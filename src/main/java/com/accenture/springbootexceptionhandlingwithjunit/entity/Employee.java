package com.accenture.springbootexceptionhandlingwithjunit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE_TBL")
public class Employee {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String department;
    private String address;
    private double expectedSalary;

    public Employee(String name, String department, String address, double expectedSalary) {
        this.name = name;
        this.department = department;
        this.address = address;
        this.expectedSalary = expectedSalary;
    }


//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {return name;}
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(String department) {
//        this.department = department;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public double getExpectedSalary() {
//        return expectedSalary;
//    }
//
//    public void setExpectedSalary(double expectedSalary) {
//        this.expectedSalary = expectedSalary;
//    }
//

}
