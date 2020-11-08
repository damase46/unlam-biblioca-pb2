package com.unlam.library.domain;



public class Employee extends Person {

    private Long employeeId;
    private Status status;
    
    
    public 	Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
    
    
  
    
    
}
