import React from 'react'
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';


export default function NavBar() {
  return (
    <div>
          <Navbar bg="dark" data-bs-theme="dark">
        <Container>
          <Navbar.Brand href="/">Job Portal</Navbar.Brand>
          <Nav>
            <NavDropdown
              id="nav-dropdown-dark-example"
              title="SignIn/SignUp"
              menuVariant="dark"
            >
              <NavDropdown.Item href="/jobprovider/signin">Job Provider</NavDropdown.Item>
              <NavDropdown.Item href="/jobseeker/signin">Job Seeker</NavDropdown.Item>
              <NavDropdown.Item href="/admin/signin">Admin</NavDropdown.Item>
            </NavDropdown>
          </Nav>
        </Container>
      </Navbar>
      
    </div>
  )
}
