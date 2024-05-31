package com.reactive.service;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.reactive.entity.Employee;
import com.reactive.exceptions.RecordNotSavedException;
import com.reactive.repo.EmployeeRepository;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

	@InjectMocks
	private EmployeeServiceImpl employeeService;
	
	@MockBean
	private EmployeeRepository employeeRepository;
	
	@Test
	public void testCreateEmployee_Success() {
		Employee employee=Employee.builder().name("Jhon").id(1).build();
		
		when(employeeRepository.save(employee)).thenReturn(Mono.just(employee));
		
		Mono<Employee> savedEmployee = employeeService.createEmployee(employee);
		
		StepVerifier.create(savedEmployee)
					.expectNext(employee)
					.expectComplete()
					.verify();
		
		verify(employeeRepository, times(1)).save(employee);
	}
		
	@Test
	public void testCreateEmployee_Failure() {
		Employee employee=Employee.builder().name("Jhon").id(1).build();
		
		when(employeeRepository.save(employee)).thenThrow(RuntimeException.class);
		
		Mono<Employee> savedEmployee = employeeService.createEmployee(employee);
		
		StepVerifier
		  .create(savedEmployee)
		  .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
		    throwable.getMessage().equals("Failed to save Employee")
		  ).verify();
		
	}
	
}
