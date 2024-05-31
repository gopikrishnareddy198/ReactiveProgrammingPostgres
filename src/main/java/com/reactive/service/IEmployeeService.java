package com.reactive.service;

import com.reactive.entity.Employee;

import reactor.core.publisher.Mono;

public interface IEmployeeService {

	Mono<Employee> createEmployee(Employee employee);
}
