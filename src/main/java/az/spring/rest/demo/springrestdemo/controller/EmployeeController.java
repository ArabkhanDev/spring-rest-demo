package az.spring.rest.demo.springrestdemo.controller;

import az.spring.rest.demo.springrestdemo.rest.model.dto.EmployeeDto;
import az.spring.rest.demo.springrestdemo.rest.model.response.EmployeeResponse;
import az.spring.rest.demo.springrestdemo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public EmployeeResponse getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("{employee-id}")
    public EmployeeDto getEmployee(@PathVariable("employee-id") long id){
        return employeeService.getEmployee(id);
    }

}
