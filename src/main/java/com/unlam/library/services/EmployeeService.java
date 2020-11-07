package com.unlam.library.services;
import java.util.HashSet;
import java.util.Set;


import com.unlam.library.domain.Employee;

public class EmployeeService {
	
	private static EmployeeService employeeService;
	private HashSet<Employee> employees;
	
	private EmployeeService() {
		employees = new HashSet<Employee>();
		
		
	}
	
	 public Boolean save(Employee object) {
		return employees.add(object);
		
		
	    }

	   
	    public Boolean delete(Employee object) {
	    	
	        for (Employee employee : employees) {
				if(employee.equals(object)) {
				return	employees.remove(object);
				}
				
			}
			return null;
	    }

	    
	    
	    
	    
	   
	    public  HashSet<Employee> findAll() {
	    	return employees;
	    }

	    public static EmployeeService getInstance() {
	    	if(employeeService == null) {
	    		employeeService = new EmployeeService();
	    		return employeeService;
	    	}
	    	else {
	    		return employeeService;
	    	}
	    }
}
