package com.reactive.service;

import org.springframework.stereotype.Service;

import com.reactive.entity.Employee;
import com.reactive.exceptions.RecordNotSavedException;
import com.reactive.repo.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class EmployeeServiceImpl implements IEmployeeService {

	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Mono<Employee> createEmployee(Employee employee) {
		return employeeRepository.save(employee)
				.doOnSuccess(savedEmployee->{
					log.info("{} saved successfully", employee);
					})
				.onErrorMap(error->{
					log.error("Exception occured while saving employee: "+error);
					throw new RecordNotSavedException("Failed to save Employee");
				});
	}


}
