import React from 'react';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';

export default function AdminNavBar() {
  const userName = localStorage.getItem('userName');
  return (
    <div><div>
    <Navbar bg="dark" data-bs-theme="dark">
      <Container>
        <Navbar.Brand href="/">Job Portal</Navbar.Brand>
        <Nav className="me-auto">
          <Nav.Link href="/admin/home">Home</Nav.Link>
          <Nav.Link href="/admin/registration">Register Another Admin</Nav.Link>
          <Nav.Link href="/logout">SignOut</Nav.Link>
        </Nav>
        <Navbar.Collapse className="justify-content-end">
        <Nav.Link disabled className="me-3"> Signed in as: {userName}</Nav.Link>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  </div></div>
  )
}
