package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/myweb")


public class MyWebService {
	
	ArrayList<Employee> myList = new ArrayList<Employee>();
	private Object EmployeeNotFoundException;
	
	public MyWebService() {
			
		
			System.out.println("My web services constructor.....");
			
			Employee e1 = new Employee(1,"John",20000);
			Employee e2 = new Employee(2,"Jack",28000);
			Employee e3 = new Employee(3,"Jenny",80000);
			Employee e4 = new Employee(4,"Julie",90000);
			Employee e5 = new Employee(5,"Jill",45000);
			
			myList.add(e1);
			myList.add(e2);
			myList.add(e3);
			myList.add(e4);
			myList.add(e5);
			
	}
	

		@GetMapping("/getEmps") // http://localhost:8080/myweb/greet
		
		public List<Employee> getAllEmployees()
		{
			System.out.println("getAllEmployees() returning array list");
			return myList;
		}
		
		
	@GetMapping("/getEmp/{eno}") // http://localhost:8080/myweb/greet
		
		public ResponseEntity<?> getEmployee(@PathVariable("eno") int empno)
		{
			
			System.out.println("getEmployee() returning array list");
			boolean foundEmp = false;
			Employee tempEmp = null;
			for(Employee employee : myList)
			{
				if(employee.getEmpno()==empno)
				{
				tempEmp = employee;
				foundEmp = true;
				break;
				}
			}
			
			if(foundEmp==true)
			{
				return ResponseEntity.ok(tempEmp);
			}
			else
			{
				EmployeeNotFoundException empNotFndEx = new EmployeeNotFoundException("Employee Not found");
					return ResponseEntity.status(404).body(empNotFndEx.getMessage());
			}
		}
		
	@PostMapping("/addEmp") // http://localhost:8080/myweb/greet
	
	public ResponseEntity<?> addEmployee(@RequestBody Employee empObj)
	{
		
		System.out.println("addEmployee() returning array list");
		boolean foundEmp = false;
		Employee tempEmp = null;
		for(Employee employee : myList)
		{
			if(employee.getEmpno()==empObj.getEmpno())
			{
			tempEmp = employee;
			foundEmp = true;
			break;
			}
		}
		
		if(foundEmp==true)
		{
			EmployeeAlreadyExistsException empExistEx = new EmployeeAlreadyExistsException("Employee already exist with ID "+empObj.getEmpno());
			return ResponseEntity.status(404).body(empExistEx.getMessage());
		}
		else
		{
			myList.add(empObj);
				return ResponseEntity.ok(empObj);
		}
	}
	
	@PutMapping("/updateEmp")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee empObj)
	{
		
		System.out.println("updateEmployee() returning array list");
		boolean foundEmp = false;
		Employee tempEmp = null;
		for(Employee employee : myList)
		{
			if(employee.getEmpno()==empObj.getEmpno())
			{
			myList.remove(employee);
			myList.add(empObj);
			foundEmp = true;
			break;
			}
		}
		
		if(foundEmp==true)
		{
			return ResponseEntity.ok(empObj);
		
		}
		else
		{
			EmployeeNotFoundException empNotFndEx = new EmployeeNotFoundException("Employee does not exist with this ID "+empObj.getEmpno());
			return ResponseEntity.status(404).body(empNotFndEx.getMessage());
		}
	}
	
	@DeleteMapping("/deleteEmp/{eno}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("eno") int x)
	{
		
		System.out.println("deleteEmployee() returning array list");
		boolean foundEmp = false;
		Employee tempEmp = null;
		for(Employee employee : myList)
		{
			if(employee.getEmpno()== x)
			{
			myList.remove(employee);
		
			foundEmp = true;
			break;
			}
		}
		
		if(foundEmp==true)
		{
			return ResponseEntity.ok("Employee deleted " + x);
		
		}
		else
		{
			EmployeeNotFoundException empNotFndEx = new EmployeeNotFoundException("Employee does not exist with this ID "+ x);
			return ResponseEntity.status(404).body(empNotFndEx.getMessage());
		}
	}
}
