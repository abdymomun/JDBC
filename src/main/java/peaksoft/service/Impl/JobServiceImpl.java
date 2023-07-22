package peaksoft.service.Impl;

import peaksoft.dao.Impl.JobDaoImpl;
import peaksoft.model.Job;
import peaksoft.service.JobService;

import java.util.List;

public class JobServiceImpl implements JobService {
    JobDaoImpl dao = new JobDaoImpl();
    @Override
    public void createJobTable() {
dao.createJobTable();
    }

    @Override
    public void addJob(Job job) {
dao.addJob(job);
    }

    @Override
    public Job getJobById(Long jobId) {
        return dao.getJobById(jobId);
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        return dao.sortByExperience(ascOrDesc);
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        return dao.getJobByEmployeeId(employeeId);
    }

    @Override
    public void deleteDescriptionColumn() {
        dao.deleteDescriptionColumn();
    }
}
