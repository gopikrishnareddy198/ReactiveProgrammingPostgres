package com.reactive.controller;
import com.reactive.entity.Employee;
import com.reactive.service.IEmployeeService;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeRestController {

	private IEmployeeService employeeService;
	
	
	public EmployeeRestController(IEmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	@PostMapping(value = "/save/{name}")
	public Mono<Employee> createEmplooyee(@PathVariable(value = "name") String name){
		Employee employee;
		
		employee = new Employee();
		employee.setName(name);
		return employeeService.createEmployee(employee);
	}
	
	@GetMapping(value = "/get")
	public Mono<Employee> get() {
		Employee employee = Employee.builder().name("Jhon").id(3).build();

		return Mono.just(employee);
	}
}

