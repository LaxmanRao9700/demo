package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class EmployeeController {
	

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/home")
	public ResponseEntity<String> getHome() {
	try {
		return new  ResponseEntity<>("Home page.", HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}
	
	@PostMapping("/employee")
	public ResponseEntity<Employee> createEmployee( @RequestBody Employee employee) {

		try {
			Employee employeeObj= employeeRepository.save(new Employee(employee.getName(),employee.getAddress(),employee.getSalary()));
	
			return new ResponseEntity<>(employeeObj, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployee( ) {
		try {
			List<Employee> employees= employeeRepository.findAll();
	
			return new ResponseEntity<List<Employee>>(employees, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
