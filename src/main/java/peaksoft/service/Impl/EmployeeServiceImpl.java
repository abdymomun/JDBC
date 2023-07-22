package peaksoft.service.Impl;

import peaksoft.dao.Impl.EmployeeDaoImpl;
import peaksoft.model.Employee;
import peaksoft.model.Job;
import peaksoft.service.EmployeeService;

import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDaoImpl employeeService = new EmployeeDaoImpl();
    @Override
    public void createEmployee() {
employeeService.createEmployee();
    }

    @Override
    public void addEmployee(Employee employee) {
employeeService.addEmployee(employee);
    }

    @Override
    public void dropTable() {
employeeService.dropTable();
    }

    @Override
    public void cleanTable() {
employeeService.cleanTable();
    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
employeeService.updateEmployee(id,employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeService.findByEmail(email);
    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        return employeeService.getEmployeeByPosition(position);
    }
}
