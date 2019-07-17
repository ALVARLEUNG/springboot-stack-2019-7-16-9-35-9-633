package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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


}