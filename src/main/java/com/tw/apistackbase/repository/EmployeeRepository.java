package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.Employee;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class EmployeeRepository {
    private Map<String, Employee> employees;

    public EmployeeRepository() {
        employees = new HashMap<>();
        employees.put("1",new Employee("1","a",20,"female",10000));
        employees.put("2",new Employee("2","b",21,"male",20000));
        employees.put("3",new Employee("3","c",22,"male",30000));
    }

    public List<Employee> findAll(){
        return employees.values().stream().collect(Collectors.toList());
    }

    public Employee findById(String employeeId){
        return employees.get(employeeId);
    }

    public List<Employee> findByGender(String gender) {
        return employees.values().stream().filter(employee -> employee.getGender().equals(gender)).collect(Collectors.toList());
    }

    public List<Employee> save(Employee employee) {
        String employeeId = UUID.randomUUID().toString();
        employees.put(employeeId,employee);
        return employees.values().stream().collect(Collectors.toList());
    }

    public List<Employee> delete(String employeeId){
        employees.remove(employeeId);
        return employees.values().stream().collect(Collectors.toList());
    }

    public void update(Employee employee) {
        employees.put(employee.getId(),employee);
    }

}
