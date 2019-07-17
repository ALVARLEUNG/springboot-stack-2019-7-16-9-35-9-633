package com.tw.apistackbase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jayway.jsonpath.JsonPath;
import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.repository.EmployeeRepository;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository mockEmployeeRepository;

    @Test
    public void should_return_employees_when_get_all_employee() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("1","a",20,"female",10000));
        Mockito.when(mockEmployeeRepository.findAll()).thenReturn(employees);

        mockMvc.perform(get("/employees"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\n" +
                        "        \"id\": \"1\",\n" +
                        "        \"name\": \"a\",\n" +
                        "        \"age\": 20,\n" +
                        "        \"gender\": \"female\",\n" +
                        "        \"salary\": 10000\n" +
                        "    }]"));

    }

    @Test
    public void should_return_employee_when_call_find_employee_by_id_given_id() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("1","a",20,"female",10000));
        Mockito.when(mockEmployeeRepository.findAll()).thenReturn(employees);

        mockMvc.perform(get("/employees").content("1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\n" +
                        "        \"id\": \"1\",\n" +
                        "        \"name\": \"a\",\n" +
                        "        \"age\": 20,\n" +
                        "        \"gender\": \"female\",\n" +
                        "        \"salary\": 10000\n" +
                        "    }]"));

    }

    @Test
    public void should_return_employee_when_call_find_employee_by_gender_given_gender() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("1","a",20,"female",10000));
        Mockito.when(mockEmployeeRepository.findByGender("female")).thenReturn(employees);

        mockMvc.perform(get("/employees/gender/{gender}","female").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\n" +
                        "        \"id\": \"1\",\n" +
                        "        \"name\": \"a\",\n" +
                        "        \"age\": 20,\n" +
                        "        \"gender\": \"female\",\n" +
                        "        \"salary\": 10000\n" +
                        "    }]"));

    }

    @Test
    public void should_return_employees_when_call_create_employee() throws Exception {
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee("1","a",20,"female",10000);
        employees.add(employee);
        Mockito.when(mockEmployeeRepository.save(employee)).thenReturn(employees);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(employee);

        mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void should_update_employee_when_call_update_employee() throws Exception {
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee("1","a",20,"female",10000);
        employees.add(employee);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(employee);

        mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void should_delete_employee_when_call_delete_employee_by_id() throws Exception {
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee("1","a",20,"female",10000);
        employees.add(employee);

        Mockito.when(mockEmployeeRepository.delete(employee.getId())).thenReturn(new ArrayList<>());

        mockMvc.perform(delete("/employees/{employeeId}", "1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(content().json("[]"));

    }

}