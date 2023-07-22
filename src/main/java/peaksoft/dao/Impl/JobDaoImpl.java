package peaksoft.dao.Impl;

import peaksoft.config.Config;
import peaksoft.dao.JobDao;
import peaksoft.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl implements JobDao {
    private final Connection connection = Config.getConnection();
    @Override
    public void createJobTable() {
        String sql = "create  table Jobs(" +
                "id serial primary key ," +
                "position varchar," +
                "profession varchar," +
                "description varchar," +
                "expereince int)";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Table successfuly created ! ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
@Override
public void addJob(Job job) {
    String sql = "insert into  jobs (position, profession, description, expereince) " +
            "values (?, ?, ?, ?)";
    try {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, job.getPosition());
        preparedStatement.setString(2, job.getProfession());
        preparedStatement.setString(3, job.getDescription());
        preparedStatement.setInt(4, job.getExperience());
        preparedStatement.executeUpdate();
        System.out.println("succesfuly aded to table ! ");
        preparedStatement.close();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
@Override
public Job getJobById(Long jobId) {
    Job job = null;

    try {
        String sql = "select * from jobs where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, jobId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            job = new Job();
            job.setId(resultSet.getLong("id"));
            job.setPosition(resultSet.getString("position"));
            job.setProfession(resultSet.getString("profession"));
            job.setDescription(resultSet.getString("description"));
            job.setExperience(resultSet.getInt("experience"));
        }

        resultSet.close();
        preparedStatement.close();

    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    return job;
}


@Override
public List<Job> sortByExperience(String ascOrDesc) {
    List<Job> jobs = new ArrayList<>();

    try {
        String sql = "select * from jobs order by expereince ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Job job = new Job();
            job.setId(resultSet.getLong("id"));
            job.setPosition(resultSet.getString("position"));
            job.setProfession(resultSet.getString("profession"));
            job.setDescription(resultSet.getString("description"));
            job.setExperience(resultSet.getInt("expereince"));
            jobs.add(job);
        }

        resultSet.close();
        preparedStatement.close();

    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    return jobs;
}

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        Job job = null;

        try {
            String sql = "select * from jobs j join employee e on j.id = e.job_id where ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                job = new Job();
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("position"));
                job.setProfession(resultSet.getString("profession"));
                job.setDescription(resultSet.getString("description"));
                job.setExperience(resultSet.getInt("experience"));
            }

            resultSet.close();
            preparedStatement.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return job;
    }
    @Override
    public void deleteDescriptionColumn() {
        try {
            String sql = "alter table jobs drop column description;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.close();
            preparedStatement.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    }

