package peaksoft;

import peaksoft.dao.Impl.JobDaoImpl;
import peaksoft.model.Employee;
import peaksoft.model.Job;
import peaksoft.service.Impl.EmployeeServiceImpl;
import peaksoft.service.Impl.JobServiceImpl;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        JobServiceImpl jobService = new JobServiceImpl();
        while (true) {
            switch (new Scanner(System.in).nextLine()) {
                case "1", "Create job " -> jobService.createJobTable();
                case "2", "Add job" -> jobService.addJob(new Job("Mentor", "java", "Backend", 1));
                case "3", "Get job by id " -> System.out.println(jobService.getJobById(1L));
                case "4", "Sort by experience" -> System.out.println(jobService.sortByExperience("acs"));
                case "5", "Get job by employee id" -> jobService.getJobByEmployeeId(1L);
                case "6", "Delete description column" -> jobService.deleteDescriptionColumn();
                case "7", "Create employee " -> employeeService.createEmployee();
                case "8", "Add employee" -> employeeService.addEmployee(new Employee("Abdymomun", "Akunov", 18, "abdymomun@gmail.com", 1));
                case "9", "Drop table" -> employeeService.dropTable();
                case "10", "Clean table" -> employeeService.cleanTable();
                case "11", "update employee" -> employeeService.updateEmployee(1L, new Employee("Bekmamat", "Azamatbekov", 20, "beka@gail.com", 1));
                case "12", "Get all employees" -> System.out.println(employeeService.getAllEmployees());
                case "13", "Find by email" -> System.out.println(employeeService.findByEmail("beka@gail.com"));
                case "14","Get employee by id " -> System.out.println(employeeService.getEmployeeById(1L));
                case "15","Get employee by position " -> System.out.println(employeeService.getEmployeeByPosition("Mentor"));
            }
        }
    }
}
