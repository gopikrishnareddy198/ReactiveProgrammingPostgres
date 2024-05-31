package com.reactive.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.reactive.entity.Employee;
import com.reactive.service.IEmployeeService;

import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(EmployeeRestController.class)
public class EmployeeRestControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private IEmployeeService employeeService;
	
	@Test
	public void shouldCreateUser_return200Success() {
		Employee employee = Employee.builder().name("Jhon").id(3).build();
		
		when(employeeService.createEmployee(employee)).thenReturn(Mono.just(employee));
		
		webTestClient.post()
		.uri(uriBuilder -> uriBuilder
			    .path("/employee/save/{name}")
			    .build("Gopi"))
				.contentType(MediaType.APPLICATION_JSON) 
				.exchange().expectStatus().isOk().expectBody(Employee.class) 
				.isEqualTo(employee); 

	}
	
	
	@Test
	public void aasd() {
		Employee employee = Employee.builder().name("Jhon").id(3).build();

		FluxExchangeResult<Employee> fluxExchangeResult=
		webTestClient.get()
		.uri("/employee/get")
			  .exchange().expectStatus().isOk().returnResult(Employee.class);
		
		Employee actual = fluxExchangeResult.getResponseBody().next().block();
		assertEquals(employee, actual);
	}
	
	
	
}
