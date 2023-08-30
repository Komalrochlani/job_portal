import React from 'react'
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';

import axios from 'axios';

export default function JSNavBar() {
  const userName = localStorage.getItem('userName');
    
  return (
    <div>
    <Navbar bg="dark" data-bs-theme="dark">
      <Container>
        <Navbar.Brand href="/">Job Portal</Navbar.Brand>
        <Nav className="me-auto">
        <Nav.Link href="/jobseeker/home">Home</Nav.Link>
        <Nav.Link href="/job/jobs">All Jobs</Nav.Link>
        <Nav.Link href='/jobseeker/myapplications'>Applications</Nav.Link>
        <Nav.Link href='/jobseeker/update'>Update</Nav.Link>
        <Nav.Link href='/logout'>SignOut</Nav.Link>
        </Nav>
        <Nav>
        <Navbar.Collapse className="justify-content-end">
          
          <Nav.Link disabled className="me-3"> Signed in as: {userName}</Nav.Link>
        </Navbar.Collapse>
        </Nav>
      </Container>
    </Navbar>
  </div>
  )
}
