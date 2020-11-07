package com.unlam.library.domain;



public class Employee extends Person {

    private Integer employeeId;
    private StatusEmployee status;
    
    
    
   
	public Employee(Integer i) {
		super();
		this.employeeId = i;
		
	}
	public 	Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public StatusEmployee getStatus() {
		return status;
	}
	public void setStatus(StatusEmployee status) {
		this.status = status;
	}
	
    
    
  
    
    
}
