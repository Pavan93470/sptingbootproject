package com.evoke.springbootproject.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evoke.springbootproject.ResourcesNotFound.ResourceNotFoundExpection;
import com.evoke.springbootproject.model.Employee;
import com.evoke.springbootproject.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	@Autowired(required=true)
	private EmployeeRepository employeerepository;

	// create get all employees
	@GetMapping("/emp")
	public List<Employee> getallemployees() {
		return ((JpaRepository<Employee, Integer>) employeerepository).findAll();

	}

	// create employee
	@PostMapping("/employees")
	public Employee createemployee(@RequestBody Employee employee) {
		return employeerepository.save(employee);

	}

	// get employees by id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(name = "id") int id)
			throws ResourceNotFoundExpection {
		Employee employee = ((CrudRepository<Employee, Integer>) employeerepository).findById(id)
				.orElseThrow(() -> new ResourceNotFoundExpection("not found " + id));
		return ResponseEntity.ok().body(employee);
	}

	// update employee
	@PutMapping("/employees/{id}")
	public Employee updateemployee(@PathVariable(value = "id") int id, @RequestBody Employee empdto)
			throws ResourceNotFoundExpection {
		Employee employee = employeerepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExpection("not found " + id));
		employee.setId(empdto.getId());
		employee.setName(empdto.getName());
		employee.setSalary(empdto.getSalary());
		employee.setRole(empdto.getRole());
		return employee;

	}

	public ResponseEntity<Employee> deleteemployee(@PathVariable(value = "id") int id)
			throws ResourceNotFoundExpection {
		Employee employee = ((CrudRepository<Employee, Integer>) employeerepository).findById(id)
				.orElseThrow(() -> new ResourceNotFoundExpection("not found " + id));
		((CrudRepository<Employee, Integer>) employeerepository).deleteById(id);
		return ResponseEntity.ok().body(employee);

	}

}
