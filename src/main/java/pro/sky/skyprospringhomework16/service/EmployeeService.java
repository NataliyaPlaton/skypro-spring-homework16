package pro.sky.skyprospringhomework16.service;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.skyprospringhomework16.exception.EmployeeAlreadyAddedException;
import pro.sky.skyprospringhomework16.exception.EmployeeNotFoundException;
import pro.sky.skyprospringhomework16.exception.EmployeeStorageIsFullException;
import pro.sky.skyprospringhomework16.exception.InvalidInputException;
import pro.sky.skyprospringhomework16.model.Employee;

import java.util.*;

@Service
public class EmployeeService {
    private final Map<String, Employee> employees;

    public EmployeeService() {
        this.employees = new HashMap<>();
    }

    public Employee addEmployee(String firstName,
                                String lastName) {
        if(!validateInput(firstName, lastName)){
            throw new InvalidInputException();
        }
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    public Employee removeEmployee(String firstName,
                                   String lastName) {
        if(!validateInput(firstName, lastName)){
            throw new InvalidInputException();
        }
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey((employee.getFullName()))) {
            return employees.remove(employee.getFullName());

        }
        throw new EmployeeNotFoundException();
    }


    public Employee findEmployee(String firstName,
                                 String lastName) {
        if(!validateInput(firstName, lastName)){
            throw new InvalidInputException();
        }
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey((employee.getFullName()))) {
            return employees.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException();
    }

    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }
    private boolean validateInput(String firstName, String lastName){
        return StringUtils.isAlpha(firstName) && StringUtils.isAlpha(lastName);
    }
    }


