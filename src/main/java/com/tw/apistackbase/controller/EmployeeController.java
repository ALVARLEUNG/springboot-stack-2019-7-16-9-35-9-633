package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeByID(@PathVariable String id) {
        return employeeRepository.findById(id);
    }

    @PostMapping
    public List<Employee> addEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @DeleteMapping("/{employeeId}")
    public List<Employee> deleteEmployee(@PathVariable String employeeId){
       return employeeRepository.delete(employeeId);
    }


    @PutMapping
    public void updateEmployee(@RequestBody Employee employee){
        employeeRepository.update(employee);
    }

    @GetMapping("/gender/{gender}")
    public List<Employee> findEmployeeByGender(@PathVariable String gender){
        return  employeeRepository.findByGender(gender);
    }

}
