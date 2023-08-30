import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Table from 'react-bootstrap/Table';
import AdminNavBar from './AdminNavBar';

export default function AdminHome() {
    const [jobProviders, setJobProviders] = useState([]);
  const [jobSeekers, setJobSeekers] = useState([]);
  const [jobs, setJobs] = useState([]);
  let userName = localStorage.getItem('userName');
  useEffect(() => {
    // Fetch job providers list
    axios.get('http://localhost:7070/jobprovider/providerlist')
      .then(response => {
        setJobProviders(response.data);
      })
      .catch(error => {
        console.error('Error fetching job providers:', error);
      });

    // Fetch job seekers list
    axios.get('http://localhost:7070/jobseeker/seekerlist')
      .then(response => {
        setJobSeekers(response.data);
      })
      .catch(error => {
        console.error('Error fetching job seekers:', error);
      });

      axios.get(`http://localhost:7070/job/jobs`)
      .then(response => {
        setJobs(response.data);
      })
      .catch(error => {
        console.error(error);
        // Handle error
      });
  }, []);

  const handleDeleteJobSeeker = (jsId) => {
    // Send a DELETE request to delete the job seeker with the given jsId
    axios.delete(`http://localhost:7070/jobseeker/delete/${jsId}`)
      .then(response => {
        // Update the job seekers list after successful deletion
        setJobSeekers(prevJobSeekers => prevJobSeekers.filter(seeker => seeker.jsId !== jsId));
      })
      .catch(error => {
        console.error('Error deleting job seeker:', error);
      });
  };

  const handleDeleteJobProvider = async (jpId) => {
    try {
      await axios.delete(`http://localhost:7070/jobprovider/delete/${jpId}`);
      // Perform any necessary updates to your state or UI after successful deletion
      console.log(`Job Provider with ID ${jpId} deleted`);
    } catch (error) {
      console.error('Error deleting job provider:', error);
    }
  };

  return (
    <div>
      <AdminNavBar></AdminNavBar>
      <h1>Admin Home</h1>

    <h2>Job Providers</h2>
    <Table striped bordered hover variant="dark">
      <thead>
        <tr>
          <th>JP ID</th>
          <th>Name</th>
          <th>User Name</th>
          <th>Website</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
      {jobProviders.map(provider => (
            <tr key={provider.jpId}>
              <td>{provider.jpId}</td>
              <td>{provider.jpName}</td>
              <td>{provider.userName}</td>
              <td>{provider.webSite}</td>
              <td>
                <button onClick={() => handleDeleteJobProvider(provider.jpId)}>Delete</button>
              </td>
              
            </tr>
          ))}
      </tbody>
    </Table>

    <h2>Job Seekers</h2>
    <Table striped bordered hover variant="dark">
      <thead>
        <tr>
          <th>JS ID</th>
          <th>Name</th>
          <th>User Name</th>
          <th>Email</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
      {jobSeekers.map(seeker => (
            <tr key={seeker.jsId}>
                <td>{seeker.jsId}</td>
              <td>{seeker.jsFullName}</td>
              <td>{seeker.userName}</td>
              <td>{seeker.email}</td>
              <td>
              <button onClick={() => handleDeleteJobSeeker(seeker.jsId)}>Delete</button>
              </td>
              
            </tr>
          ))}
      </tbody>
    </Table>

    <h2>Job List</h2>
    <Table striped bordered hover variant="dark">
      <thead>
        <tr>
          <th>Job ID</th>
          <th>Title</th>
          <th>Company</th>
          <th>Salary</th>
          <th>Vacancies</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
      {jobs.map(job => (
            <tr key={job.jobId}>
                <td>{job.jobId}</td>
              <td>{job.assignedJpId.jpName}</td>
              <td>{job.jobTitle}</td>
              <td>{job.salary}</td>
              <td>{job.vacancies}</td>
              
            </tr>
          ))}
      </tbody>
    </Table>
    
    </div>
  )
}
