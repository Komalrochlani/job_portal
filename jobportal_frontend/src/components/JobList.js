import React, { useState, useEffect } from 'react';
import Table from 'react-bootstrap/Table';
import axios from 'axios';
import Button from 'react-bootstrap/Button';
import JSNavBar from './JSNavBar';
import { useNavigate } from 'react-router-dom';


export default function JobList() {
  
  const [jobs, setJobs] = useState([]);
  const [buttonClicked,setButtonClicked] =useState(false);
  const currentDate = new Date();

  let userName = localStorage.getItem('userName');
  const userLoggedIn = Boolean(userName);
  const url = `http://localhost:7070/job/jobs/jobseeker/${userName}`;

  useEffect(() => {
    // Fetch the list of jobs from the API
    axios.get(url)
      .then(response => {
        setJobs(response.data);
      })
      .catch(error => {
        console.error(error);
        // Handle error
      });
  }, []);

  const updateJobStatus = (jobId) => {
    const updatedJobs = jobs.map(job => {
      if (job.jobId === jobId) {
        return { ...job, isApplied: true };
      }
      return job;
    });
    setJobs(updatedJobs);
  };

  const handleJobApplication = async (jobId) => {
    if (jobId) {
  
      try {
        const response = await axios.post(
          `http://localhost:7070/application/apply/${jobId}`, {
              userName: userName
          }
            // Update the URL with the job ID
        
        );
        console.log("Job application response:", response.data);
        if(response.data === 'Success'){
          alert("Applied for Job Successfully");
        } else {
          alert("Application failed");
        }
        updateJobStatus(jobId);
        
        // Handle the response or show a success message
      } catch (error) {
        console.error("Job application error:", error);
        // Handle the error
      }
    }
  };

  return (
    <div>
      <JSNavBar></JSNavBar>
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
            
            {/* <td><form action='/application/apply'><Button variant="success" type="submit" onClick={() => handleJobApplication(job.jobId)}>Apply</Button></form></td>   */}
            
            {/* Changes */}
            <td>
            {currentDate >new Date(job.applicationDeadline)?
            (<Button variant="success" disabled>Application Closed</Button>)  :
            userLoggedIn ? 
            (!job.isApplied ? <Button variant="success" type="submit" id={job.jobId}
            onClick={() => {handleJobApplication(job.jobId); setButtonClicked(true);} }
            >Apply</Button>:<Button variant="success" disabled>Applied</Button>)
            :(<Button variant="success" disabled>Login To Apply</Button>)}</td>  
          </tr>
        ))}
      </tbody>
    </Table>
    </div>
  );
}
