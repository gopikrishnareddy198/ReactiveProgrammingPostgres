package com.reactive.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.reactive.entity.Employee;

public interface EmployeeRepository extends ReactiveCrudRepository<Employee, Integer> {

}
