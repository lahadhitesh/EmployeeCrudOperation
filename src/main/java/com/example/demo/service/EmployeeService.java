package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.EmployeeDto;

public interface EmployeeService {

	EmployeeDto createEmployee(EmployeeDto dto);

	EmployeeDto getEmployeeById(Long employeeId);

	List<EmployeeDto> getAllEmployee();

	EmployeeDto updateEmployeeById(Long employeeId, EmployeeDto dto);

	void deleteEmployeeById(Long id);
}
