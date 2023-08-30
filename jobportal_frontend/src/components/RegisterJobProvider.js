import React,{useState} from 'react'
import { Col, Button, Row, Container, Card, Form } from "react-bootstrap";
import { NavLink,Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import NavBar from './NavBar';


export default function RegisterJobProvider() {
  const navigate=useNavigate();
  const [formData, setFormData] = useState({
    jpName: "",
    userName: "",
    password: "",
    email: "",
    address: "",
    webSite: "",
    phoneNo: ""
  });
  const url = "http://localhost:7070/jobprovider/registration"; 

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(url, formData);
      console.log('Registration response:', response.data);     
      navigate('/jobprovider/signin');
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
      <NavBar></NavBar>
    <Container>      
        <Row className="vh-100 d-flex justify-content-center align-items-center">
          <Col md={8} lg={6} xs={12}>
          <div className="border border-2 border-primary"></div>
            <Card className="shadow px-4">
              <Card.Body>
                <div className="mb-3 mt-md-4">
                  <h2 className="fw-bold mb-2 text-center text-uppercase ">Registration</h2>
                  <div className="mb-3">
                    <Form action='/jobprovider/signin' method='post'> 
                      <Form.Group className="mb-3" controlId="Name">
                        <Form.Label className="text-center">
                          Company Name
                        </Form.Label>
                        <Form.Control  type="text"
                            placeholder="Enter Name"
                            name="jpName"
                            value={formData.jpName}
                            onChange={handleChange} />
                      </Form.Group>

                      <Form.Group className="mb-3" controlId="formBasicEmail">
                        <Form.Label className="text-center">
                          UserName
                        </Form.Label>
                        <Form.Control  type="text"
                            placeholder="Enter UserName"
                            name="userName"
                            value={formData.userName}
                            onChange={handleChange} />
                      </Form.Group>

                      <Form.Group
                        className="mb-3"
                        controlId="formBasicPassword"
                      >
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" placeholder="Password"
                            name="password"
                            value={formData.password}
                            onChange={handleChange} />
                      </Form.Group>
                      <Form.Group
                        className="mb-3"
                        controlId="formBasicPassword"
                      >
                        <Form.Label>Email</Form.Label>
                        <Form.Control type="email" placeholder="Email"
                            name="email"
                            value={formData.email}
                            onChange={handleChange}   />
                      </Form.Group>
                      <Form.Group
                        className="mb-3"
                        controlId="formBasicCheckbox"
                      >
                      </Form.Group>
                      <Form.Group
                        className="mb-3"
                        controlId="formBasicPassword"
                      >
                        <Form.Label>Address</Form.Label>
                        <Form.Control type="textarea" placeholder="Address" 
                         name="address"
                         value={formData.address}
                         onChange={handleChange}
                        />
                      </Form.Group>
                      <Form.Group
                        className="mb-3"
                        controlId="formBasicPassword"
                      >
                        <Form.Label>WebSite</Form.Label>
                        <Form.Control type="text" placeholder="www.abc.com"
                            name="webSite"
                            value={formData.webSite}
                            onChange={handleChange}   />
                      </Form.Group>
                      <Form.Group
                        className="mb-3"
                        controlId="formBasicPassword"
                      >
                        <Form.Label>Phone No</Form.Label>
                        <Form.Control type="text" placeholder="PhoneNo"
                           name="phoneNo"
                           value={formData.phoneNo}
                           onChange={handleChange}
                        />
                      </Form.Group>                    
                      <div className="d-grid">
                        <Button variant="primary" type="submit" onClick={handleSubmit}>
                          Create Account
                        </Button>
                      </div>
                    </Form>
                    <div className="mt-3">
                      <p className="mb-0  text-center">
                      Already have an account?{" "}
                      <NavLink to={`/jobprovider/signin`} >SignIn</NavLink>
                        {/* <a href="{''}" className="text-primary fw-bold">
                          Sign In
                        </a> */}
                      </p>
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
