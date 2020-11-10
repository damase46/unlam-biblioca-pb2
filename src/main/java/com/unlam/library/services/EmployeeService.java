package com.unlam.library.services;

import com.unlam.library.domain.Employee;
import com.unlam.library.domain.Person;
import com.unlam.library.domain.Status;
import com.unlam.library.interfaces.Storable;
import com.unlam.library.utils.Sequence;

import java.util.*;

public class EmployeeService implements Storable<Employee> {
    private static EmployeeService employeeService;
    private Set<Employee> employees;
    private Sequence sequence;

    private EmployeeService() {
        employees = new HashSet<Employee>();
        sequence = new Sequence();
    }
    @Override
    public Employee upsert(Employee object) {
        if (object.getEmployeeId() == null) {
            object.setEmployeeId(sequence.getSequence());
            object.setStatus(Status.ENABLED);
            Person person = PersonService.getInstance().upsert(object);
            object.updatePerson(person);
            employees.add(object);
            return object;
        } else {
            Optional<Employee> employeeOptional = findById(object.getEmployeeId());

            if (!employeeOptional.isPresent()) {
                System.out.println("[EmployeeService] Client id not found");
                return null;
            }
            Employee employee = employeeOptional.get();
            employee.setStatus(object.getStatus());
            employee.setEmployeeId(object.getEmployeeId());
            return employee;
        }
    }
    @Override
    public Boolean delete(Employee object) {
        if(object.getEmployeeId()==null){
            System.out.println("[EmployeeService] Error in delete, id not found");
            return false;
        }
        return deleteBy(object.getEmployeeId());
    }
    @Override
    public Boolean deleteBy(Long id) {
        Optional<Employee> employee= findById(id);
        if(!employee.isPresent()){
            System.out.println("[EmployeeService] Error in deleteById, Client not found");
            return false;
        }
        employee.get().setStatus(Status.DISABLED);
        return true;
    }
    @Override
    public List<Employee> findAll() {
        return new ArrayList(employees);
    }
    @Override
    public Optional<Employee> findById(Long id) {
        Employee aux = null;
        for (Employee employee : employees) {
            if (employee.getEmployeeId().equals(id)) {
                aux = employee;
            }
        }
        if (aux == null) {
            Optional.empty();
        } else {
            Optional<Person> person = PersonService.getInstance().findById(aux.getId());
            if (!person.isPresent()) {
                return Optional.empty();
            }
            aux.updatePerson(person.get());
            return Optional.ofNullable(aux);
        }
        return Optional.empty();
    }
    public Optional<Employee> findByIdentification(Long identification){
        for (Employee aux : employees) {
            if(identification.equals(aux.getIdentification())){
                return Optional.ofNullable(aux);
            }
        }
        return Optional.empty();
    }
    public static EmployeeService getInstance() {
        return employeeService = employeeService == null ? new EmployeeService(): employeeService;
    }

    public void reset() {
        employees=new HashSet<Employee>();
    }
}