package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("api/employee")
public class EmployeController {

	private EmployeeService employeeService;

	public EmployeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// Create Employee
	@PostMapping("save")
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto dto) {
		EmployeeDto serviceDto = employeeService.createEmployee(dto);
		return new ResponseEntity<>(serviceDto, HttpStatus.CREATED);
	}

	//Get Employee By Id
	@GetMapping("{employeeId}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long employeeId) {
		EmployeeDto serviceDto = employeeService.getEmployeeById(employeeId);
		return ResponseEntity.ok(serviceDto);
	}

	//Get All Employee
	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
		List<EmployeeDto> serviceDto = employeeService.getAllEmployee();
		return ResponseEntity.ok(serviceDto);
	}

	//Update Employee by id
	@PutMapping("{id}")
	public ResponseEntity<EmployeeDto> updateEmployeeById(@PathVariable Long id,
														 @RequestBody EmployeeDto dto) {
		EmployeeDto serviceDto = employeeService.updateEmployeeById(id, dto);
		return ResponseEntity.ok(serviceDto);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployeebyId(@PathVariable Long id){
		employeeService.deleteEmployeeById(id);
		return ResponseEntity.ok("Employee deleted successfully...");
	}
}
