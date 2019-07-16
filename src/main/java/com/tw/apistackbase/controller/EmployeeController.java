package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

//    GET       /employees    #obtain employee list
//    GET       /employees/1  # obtain a certain specific employee
//    GET       /employees?page=1&pageSize=5  #Page query, page equals 1, pageSize equals 5
//    GET       /employees?gender=male   #screen all male employees
//    POST      /employees    # add an employee
//    PUT       /employees/1  #update an employee
//    DELETE    /employees/1  #delete an employee
    @GetMapping
    public List<Employee> getAllEmployee () {
        return employeeRepository.findAll();
    }

}
