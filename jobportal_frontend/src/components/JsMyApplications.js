import React, { useState, useEffect } from 'react';
import Table from 'react-bootstrap/Table';
import axios from 'axios';
import JSNavBar from './JSNavBar';

export default function JsMyApplications() {
    const userName = localStorage.getItem('userName');
    const [selectedJobApplications, setSelectedJobApplications] = useState([]);
  
    useEffect(() => {
      // Fetch the list of applications by jobseeker from the API
      axios.get(`http://localhost:7070/application/jobs/jobseeker/${userName}`)
        .then(response => {
          setSelectedJobApplications(response.data);
        })
        .catch(error => {
          console.error(error);
          // Handle error
        });
    }, [userName]);
  
  return (
    <div>
        <JSNavBar/>
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
    </div>
  )
}
