package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository repository;

	public EmployeeServiceImpl(EmployeeRepository repository) {
		this.repository = repository;
	}

	@Override
	public EmployeeDto createEmployee(EmployeeDto dto) {
		Employee employee = EmployeeMapper.mapToEmployee(dto);
		Employee savedEmployee = repository.save(employee);
		return EmployeeMapper.mapToDto(savedEmployee);
	}

	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
		Employee employee = repository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException
						("Employee is not found with the given id"));
		return EmployeeMapper.mapToDto(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmployee() {
		List<Employee> employee = repository.findAll();
		return employee.stream().map(emp -> EmployeeMapper.mapToDto(emp))
					   .collect(Collectors.toList());
	}

	@Override
	public EmployeeDto updateEmployeeById(Long employeeId, EmployeeDto dto) {
		Employee employee = repository.findById(employeeId)
									  .orElseThrow(() -> new ResourceNotFoundException
											  ("Employee is not found with given Id"));

		employee.setFirstName(dto.getFirstName());
		employee.setLastName(dto.getLastName());
		employee.setEmail(dto.getEmail());

		Employee savedEmployee = repository.save(employee);
		return EmployeeMapper.mapToDto(savedEmployee);
	}

	@Override
	public void deleteEmployeeById(Long id) {
		Employee employee = repository.findById(id)
							          .orElseThrow(()-> new ResourceNotFoundException
							        		  ("Employee is not found with given id"));
		repository.delete(employee);

	}

}
