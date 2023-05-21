package az.spring.rest.demo.springrestdemo.controller;

import az.spring.rest.demo.springrestdemo.rest.model.dto.EmployeeDto;
import az.spring.rest.demo.springrestdemo.rest.model.response.EmployeeResponse;
import az.spring.rest.demo.springrestdemo.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
@Tag(name = "Employee services",description = "employee services")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeResponse getAllEmployees(){
        return employeeService.getAllEmployees();
    }



    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/user/{employee-id}")
    @Operation(summary = "This gets employee by id")
    public EmployeeDto getEmployee(@PathVariable("employee-id") long employeeId){
        return employeeService.getEmployee(employeeId);
    }

    @GetMapping("/search")
    public EmployeeResponse getEmployeeByNameAndSurname(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname){

        return employeeService.getEmployeeByNameAndSurname(name,surname);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void insert(@RequestBody @Valid EmployeeDto employeeDto){
        employeeService.insert(employeeDto);
    }

    @PutMapping ("/{id}")
    public void updateAll(@RequestBody EmployeeDto employeeDto,@PathVariable("id") long id){
        employeeService.update(employeeDto,id);
    }

    @PatchMapping ("/{id}")
    public void updateSome(@RequestBody EmployeeDto employeeDto,@PathVariable("id") long id){
        employeeService.updateSome(employeeDto,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id){
        employeeService.delete(id);
    }




}
