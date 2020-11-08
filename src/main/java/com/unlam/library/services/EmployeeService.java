package com.unlam.library.services;
import java.util.HashSet;
import java.util.Set;


import com.unlam.library.domain.Employee;
import com.unlam.library.utils.Sequence;

public class EmployeeService {
	
	private static EmployeeService employeeService;
	private HashSet<Employee> employees;
	private Sequence sequence;
	
	private EmployeeService() {
		employees = new HashSet<Employee>();
		
		  sequence = new Sequence();
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
