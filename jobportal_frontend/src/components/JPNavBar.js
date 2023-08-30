import React from 'react';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';

export default function JPNavBar() {
  const userName = localStorage.getItem('userName');

  return (
    <div>
      <Navbar bg="dark" data-bs-theme="dark">
        <Container>
          <Navbar.Brand href="/">Job Portal</Navbar.Brand>
          <Nav className="me-auto">
            <Nav.Link href="/jobprovider/home" className="me-3">Home</Nav.Link>
            <Nav.Link href="/jobprovider/insertjob" className="me-3">Post Job</Nav.Link>
            <Nav.Link href="/jobprovider/jobs" className="me-3">My Jobs</Nav.Link>
            <Nav.Link href='/jobprovider/update' className="me-3">Update Profile</Nav.Link>
            <Nav.Link href="/logout">Sign Out</Nav.Link>
          </Nav>
          <Nav>
          <Navbar.Collapse className="justify-content-end">
            <Nav.Link disabled className="me-3"> Signed in as: {userName}</Nav.Link>
          </Navbar.Collapse>
          </Nav>
        </Container>
      </Navbar>
    </div>
  );
}
