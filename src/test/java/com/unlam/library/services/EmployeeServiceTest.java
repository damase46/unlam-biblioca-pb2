package com.unlam.library.services;

import com.unlam.library.domain.Employee;
import com.unlam.library.domain.Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class EmployeeServiceTest {


    @Before
    public void setup(){
        EmployeeService employeeService = EmployeeService.getInstance();
        employeeService.reset();
    }

    @Test
    public void upsert() {
        EmployeeService employeeService = EmployeeService.getInstance();
        Employee Martin = new Employee();
        employeeService.upsert(Martin);
        Assert.assertEquals(1, employeeService.findAll().size());
    }

    public void upsertUpdate(){
        EmployeeService employeeService = EmployeeService.getInstance();
        Employee Carl = new Employee();
        employeeService.upsert(Carl);
        Carl.setStatus(Status.DISABLED);
        employeeService.upsert(Carl);
        Assert.assertEquals(1, employeeService.findAll().size());
        Assert.assertEquals(Status.DISABLED, Carl.getStatus());
    }

    @Test
    public void delete() {
        EmployeeService employeeService = EmployeeService.getInstance();
        Employee Michael = new Employee();
        employeeService.upsert(Michael);
        employeeService.delete(Michael);
        Assert.assertEquals(Status.DISABLED, Michael.getStatus());

    }

    @Test
    public void deleteBy() {
        EmployeeService employeeService = EmployeeService.getInstance();
        Employee Juan = new Employee();
        employeeService.upsert(Juan);
        Employee auxEnable = employeeService.upsert(Juan);
        Assert.assertEquals(Status.ENABLED,auxEnable.getStatus());
        employeeService.deleteBy(auxEnable.getId());
        Optional<Employee> auxDisable = employeeService.findById(auxEnable.getId());
        Assert.assertEquals(Status.DISABLED,auxDisable.get().getStatus());
    }

    @Test
    public void findById() {
        EmployeeService employeeService = EmployeeService.getInstance();
        Employee Emanuel = new Employee ();
        employeeService.upsert(Emanuel);
        Assert.assertEquals(Emanuel.getEmployeeId(),employeeService.findById(Emanuel.getEmployeeId()).get().getEmployeeId());
    }

    @Test
    public void findByIdentification() {
        EmployeeService employeeService = EmployeeService.getInstance();
        Employee Emanuel = new Employee();
        employeeService.upsert(Emanuel);
        Emanuel.setIdentification(1234l);
        Assert.assertEquals(Emanuel.getIdentification(),employeeService.findByIdentification(Emanuel.getIdentification()).get().getIdentification());
    }

}

