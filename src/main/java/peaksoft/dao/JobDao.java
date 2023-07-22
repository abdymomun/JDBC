package peaksoft.dao;

import peaksoft.model.Employee;
import peaksoft.model.Job;

import java.util.List;
import java.util.Map;

public interface JobDao {
    void createJobTable();
    void addJob(Job job);
    Job getJobById(Long jobId);
    List<Job> sortByExperience(String ascOrDesc);
    Job getJobByEmployeeId(Long employeeId);
    void deleteDescriptionColumn();

}
