import React, { useState, useEffect } from 'react';
import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';
import axios from 'axios';
import JPNavBar from './JPNavBar';

export default function JobListJp() {
  const [jobs, setJobs] = useState([]);
  const [selectedJobApplications, setSelectedJobApplications] = useState([]);
  const userName = localStorage.getItem('userName');

  useEffect(() => {
    // Fetch the list of jobs from the API by username
    axios.get(`http://localhost:7070/job/jobs/jobprovider/${userName}`)
      .then(response => {
        setJobs(response.data);
      })
      .catch(error => {
        console.error(error);
        // Handle error
      });
  }, [userName]);

  const fetchApplicationsForJob = async (jobId) => {
    try {
      const response = await axios.get(
        `http://localhost:7070/application/job/${jobId}`
      );
      setSelectedJobApplications(response.data);
    } catch (error) {
      console.error('Error fetching applications:', error);
    }
  };

  return (
    <div>
      <JPNavBar></JPNavBar>
      <Table striped bordered hover variant="dark">
        <thead>
          <tr>
            <th>Job ID</th>
            <th>Job Title</th>
            <th>Company</th>
            <th>Role</th>
            <th>Key Skills</th>
            <th>Salary</th>
            <th>Description</th>
            <th>Application Deadline</th>
            <th>Vacancies</th>
            <th>Company Website</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {jobs.map(job => (
            <tr key={job.jobId}>
              <td>{job.jobId}</td>
              <td>{job.jobTitle}</td>
              <td>{job.assignedJpId.jpName}</td>
              <td>{job.role}</td>
              <td>{job.keySkills}</td>
              <td>{job.salary}</td>
              <td>{job.description}</td>
              <td>{job.applicationDeadline}</td>
              <td>{job.vacancies}</td>
              <td>{job.assignedJpId.webSite}</td>
              <td>
                <Button variant="info" onClick={() => fetchApplicationsForJob(job.jobId)}>
                  View Applications
                </Button>
            </td>
            </tr>
          ))}
        </tbody>
      </Table>

       {/* Display selected job's applications */}
       {selectedJobApplications.length > 0 && (
        <Table striped bordered hover variant="dark">
          <thead>
            <tr>
              <th>Application ID</th>
              <th>JobId</th>
              <th>Job Title</th>
            </tr>
          </thead>
          <tbody>
            {selectedJobApplications.map(application => (
              <tr key={application.applicationId}>
                <td>{application.applicationId}</td>
                <td>{application.assignedJobId.jobId}</td>
                <td>{application.assignedJobId.jobTitle}</td>
              </tr>
            ))}
          </tbody>
        </Table>
      )}
    </div>
  );
}
