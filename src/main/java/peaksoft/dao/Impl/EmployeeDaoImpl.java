package peaksoft.dao.Impl;

import peaksoft.config.Config;
import peaksoft.dao.EmployeeDao;
import peaksoft.model.Employee;
import peaksoft.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDaoImpl implements EmployeeDao {
    private final Connection connection = Config.getConnection();
    @Override
    public void createEmployee() {
        String sql = "create table Employees(" +
                "id serial primary key ,"+
                "first_name varchar," +
                "last_name varchar," +
                "age int ,"+
                "email varchar," +
                "job_id int references jobs(id))";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Table successfuly created !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void addEmployee(Employee employee) {
        String sql = "insert into employees (first_name,last_name,age,email,job_id)" +
                "values (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,employee.getFirst_name());
            preparedStatement.setString(2,employee.getLast_name());
            preparedStatement.setInt(3,employee.getAge());
            preparedStatement.setString(4,employee.getEmail());
            preparedStatement.setInt(5,employee.getJobId());
            preparedStatement.executeUpdate();
            System.out.println("succesfuly aded to table ! ");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void dropTable() {
        String sql = "drop table employees";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Succesfuly deleted table !");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cleanTable() {
        String sql = "delete from employees";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Succesfuly cleaned table !");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateEmployee(Long id, Employee employee) {

        try {
            String sql =
                    "update employees set first_name = ? ,last_name = ?,age =? ,email = ?,job_id =? where id  =?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,employee.getFirst_name());
            preparedStatement.setString(2,employee.getLast_name());
            preparedStatement.setInt(3,employee.getAge());
            preparedStatement.setString(4,employee.getEmail());
            preparedStatement.setInt(5,employee.getJobId());
            preparedStatement.setLong(6,id);
            preparedStatement.executeUpdate();
            System.out.println("Succesfuly updateed !");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Employee> getAllEmployees() {
        List<Employee>employees = new ArrayList<>();
        String sql = "select * from employees";


        try {


            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet =  preparedStatement.executeQuery();
            while (resultSet.next()){
            Employee employee = new Employee();
            employee.setId(resultSet.getLong("id"));
            employee.setFirst_name(resultSet.getString("first_name"));
            employee.setLast_name(resultSet.getString("last_name"));
            employee.setAge(resultSet.getInt("age"));
            employee.setEmail(resultSet.getString("email"));
            employee.setJobId(resultSet.getInt("job_id"));
            employees.add(employee);
        }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

@Override
public Employee findByEmail(String email) {
    Employee employee = null;
    String sql = "select * from employees where email = ?";
    try {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            employee = new Employee();
            employee.setId(resultSet.getLong("id"));
            employee.setFirst_name(resultSet.getString("first_name"));
            employee.setLast_name(resultSet.getString("last_name"));
            employee.setAge(resultSet.getInt("age"));
            employee.setEmail(resultSet.getString("email"));
            employee.setJobId(resultSet.getInt("job_id"));
        }

        resultSet.close();
        preparedStatement.close();

    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    return employee;
}



    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        Map<Employee, Job> employeeJobMap = new HashMap<>();
        String sql = "select e.id as emp_id, e.first_name, e.last_name, e.age, e.email, " +
                "j.id AS job_id, j.position, j.profession, j.description, j.expereince " +
                "from employees e " +
                "join jobs j on e.job_id = j.id " +
                "where e.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("emp_id"));
                employee.setFirst_name(resultSet.getString("first_name"));
                employee.setLast_name(resultSet.getString("last_name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setEmail(resultSet.getString("email"));

                Job job = new Job();
                job.setId(resultSet.getLong("job_id"));
                job.setPosition(resultSet.getString("position"));
                job.setProfession(resultSet.getString("profession"));
                job.setDescription(resultSet.getString("description"));
                job.setExperience(resultSet.getInt("expereince"));

                employeeJobMap.put(employee, job);
            }

            resultSet.close();
            preparedStatement.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return employeeJobMap;
    }



    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        List<Employee> employees = new ArrayList<>();

        String sql = "select * from employees e join jobs j on j.id = e.job_id where j.position = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, position);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setFirst_name(resultSet.getString("first_name"));
                employee.setLast_name(resultSet.getString("last_name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setEmail(resultSet.getString("email"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

}
