package com.example.springboottesting.service.impl;


import com.example.springboottesting.entity.Employee;
import com.example.springboottesting.exceptionHandler.ResourceNotFoundException;
import com.example.springboottesting.repository.EmployeeRepository;
import com.example.springboottesting.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {

		Optional<Employee> savedEmployee = employeeRepository.findByEmail(employee.getEmail());
		if(savedEmployee.isPresent()){
			throw new ResourceNotFoundException("Employee already exist with given email:" + employee.getEmail());
		}
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(long id) {
		return employeeRepository.findById(id);
	}

	@Override
	public Employee updateEmployee(Employee updatedEmployee) {
		return employeeRepository.save(updatedEmployee);
	}

	@Override
	public void deleteEmployee(long id) {
		employeeRepository.deleteById(id);
	}
}