import React,{useState} from 'react'
import { Col, Button, Row, Container, Card, Form } from "react-bootstrap";
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import AdminNavBar from './AdminNavBar';


export default function RegistrationAdmin() {
    const navigate=useNavigate();
  const [formData, setFormData] = useState({
    
        adminUserName: "",
        adminPassword: ""
      
  });
  const url = "http://localhost:7070/admin/registration"; 

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(url, formData);
      console.log('Registration response:', response.data);     
      navigate('/admin/signin');
    } catch (error) {
      console.error('Registration error:', error);
      // Handle error
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({ ...prevData, [name]: value }));
  };

  return (
    <div>
        <AdminNavBar/>
        <Container>      
        <Row className="vh-100 d-flex justify-content-center align-items-center">
          <Col md={8} lg={6} xs={12}>
          <div className="border border-2 border-primary"></div>
            <Card className="shadow px-4">
              <Card.Body>
                <div className="mb-3 mt-md-4">
                  <h2 className="fw-bold mb-2 text-center text-uppercase ">Registration</h2>
                  <div className="mb-3">
                    <Form action='/admin/registration' method='post'> 
                      <Form.Group className="mb-3" controlId="Name">
                        <Form.Label className="text-center">
                          UserName
                        </Form.Label>
                        <Form.Control  type="text"
                            placeholder="Enter Name"
                            name="adminUserName"
                            value={formData.adminUserName}
                            onChange={handleChange} />
                      </Form.Group>

                      <Form.Group
                        className="mb-3"
                        controlId="formBasicPassword"
                      >
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" placeholder="Password"
                            name="adminPassword"
                            value={formData.adminPassword}
                            onChange={handleChange} />
                      </Form.Group>                                          
                      <div className="d-grid">
                        <Button variant="primary" type="submit" onClick={handleSubmit}>
                          Create Account
                        </Button>
                      </div>
                    </Form>
                    
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
