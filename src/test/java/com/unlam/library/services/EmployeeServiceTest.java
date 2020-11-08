package com.unlam.library.services;
import com.unlam.library.domain.Employee;
import com.unlam.library.domain.StatusEmployee;
import com.unlam.library.services.EmployeeService;
import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

public class EmployeeServiceTest {

	@Test
	public  void queSeCreeEmployeeService() {
		
	EmployeeService service =  EmployeeService.getInstance();
	
	assertNotNull(service);
		
		
	}
	
	@Test
	public void queSeGuardenEmployees() {
		
		EmployeeService service =  EmployeeService.getInstance();
		
		
		
		Employee employee1 = new Employee();
		employee1.setName("Juan");
		
		service.save(employee1);
		
		
		
		assertTrue(service.findAll().contains(employee1));
		
		
		
		
	}
	
	@Test
	public void queSeEliminenEmployees() {
		
		EmployeeService service =  EmployeeService.getInstance();
		
		
		
		Employee employee1 = new Employee();
		employee1.setName("Juan");
		
		service.save(employee1);
		
		service.delete(employee1);
		
		assertFalse(service.findAll().contains(employee1));
		
		
		
	}
	
	
	
	@Test
	public void queSeMuestrenTodosLosEmployees() {
EmployeeService service =  EmployeeService.getInstance();
		
		
		
		Employee employee1 = new Employee();
		employee1.setName("Juan");
		
		Employee employee2 = new Employee();
		employee1.setName("Juan");
		
		Employee employee3 = new Employee();
		employee1.setName("Juan");
		
		Employee employee4 = new Employee();
		employee1.setName("Juan");
		
		service.save(employee1);
		service.save(employee2);
		service.save(employee3);
		service.save(employee4);
		
		
		HashSet<Employee> employees00 = new HashSet<Employee>();
		
		employees00.add(employee1);
		employees00.add(employee2);
		employees00.add(employee3);
		employees00.add(employee4);
		
		assertEquals(service.findAll(),employees00);
		
		
		
		
		
	}
	
	
	
	

}
