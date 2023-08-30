import React, { useState } from 'react'
import { Col, Button, Row, Container, Card, Form } from "react-bootstrap";
import { useNavigate ,NavLink} from 'react-router-dom';
import axios from 'axios';
import NavBar from './NavBar';
import { setAuthToken } from './axiosHelper';

export default function Login() {
  const navigate=useNavigate()
    const [formData,setFormData]=useState(
            {
                userName: "",
                password: ""
              }
    )
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({ ...prevData, [name]: value }));
      };

      const handleSubmit = async (e) => {
        e.preventDefault();

       
    
        try {
          const response = await axios.post("http://localhost:7070/jobseeker/signin", formData);
          console.log('Registration response:', response.data.jsId);
         // console.log(response);
         const  token  = response.data;
      localStorage.setItem('isLoggedIn',true);
      localStorage.setItem('userName',formData.userName);
     
      localStorage.setItem('token',token);
      setAuthToken(token);
          
          navigate('/jobseeker/home');
        } catch (error) {
          console.error('Registration error:', error);
          // Handle error
        }
      };

  return (
    <div>
      <NavBar></NavBar>
        <Container>
      
      <Row className="vh-100 d-flex justify-content-center align-items-center">
        <Col md={8} lg={6} xs={12}>
        <div className="border border-2 border-primary"></div>
          <Card className="shadow px-4">
            <Card.Body>
              <div className="mb-3 mt-md-4">
                <h2 className="fw-bold mb-2 text-center text-uppercase ">Login</h2>
                <div className="mb-3">
                  <Form action='/jobprovider/signin' method='post'> 
                    <Form.Group className="mb-3" controlId="Name">
                      <Form.Label className="text-center">
                        UserName
                      </Form.Label>
                      <Form.Control  type="text"                          
                          name="userName"
                          value={formData.userName}
                          onChange={handleChange} />
                    </Form.Group>                    
                    <Form.Group className="mb-3" controlId="formBasicEmail">
                      <Form.Label className="text-center">
                      Password
                      </Form.Label>
                      <Form.Control  type="password"                         
                          name="password"
                          value={formData.password}
                          onChange={handleChange} />
                    </Form.Group>                    
                    <div className="d-grid">
                      <Button variant="primary" type="submit" onClick={handleSubmit}>
                        Login
                      </Button>
                    </div>
                  </Form>
                  <div>
                      New User?<NavLink to={`/jobseeker/registration`}>SignUp</NavLink>
                      <br />
                      <NavLink to={`/jobseeker/forgotpassword`}>Forgot Password?</NavLink>
                  </div>
                 
                </div>
              </div>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container>
    </div>
  )
}