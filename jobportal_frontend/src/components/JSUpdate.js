import React,{useEffect, useState} from 'react'
import { Col, Button, Row, Container, Card, Form } from "react-bootstrap";
import { NavLink,Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import JSNavBar from './JSNavBar';

const JSUpdate = () => {
    let userName = localStorage.getItem('userName');
    const userLoggedIn = Boolean(userName);
    const navigate=useNavigate();
    const [formData, setFormData] = useState({
      jsFullName: '',
      email: '',
      address: '',
      phoneNo: '',
      gender: 'MALE',
      education: '',
      skill1: '',
      skill2: '',
      skill3: '',
      experience: 0
    });
  
    useEffect(() => {
        axios.get(`http://localhost:7070/jobseeker/getJSDetails/${userName}`)
        .then(response => {
            setFormData(response.data);
          })
          .catch(error => {
            console.error(error);
          });
    },[])

    const [successMessage, setSuccessMessage] = useState('');
    const url = `http://localhost:7070/jobseeker/updateJS/${userName}`; 

    const handleSubmit = async (e) => {
      e.preventDefault();
  
      const formDataToSend = new FormData();
      for (const key in formData) {
        formDataToSend.append(key, formData[key]);
      }
  
      try {
        const response = await axios.post(url, formDataToSend,{
                headers: { 'Content-Type': 'application/json' }
        });
        console.log('Registration response:', response.data);
        alert("User Data Updated")
      } catch (error) {
        console.error('Registration error:', error);
      }
    };
  
    const handleChange = (e) => {
      const { name, value } = e.target;
      setFormData((prevData) => ({ ...prevData, [name]: value }));
    };
  
    
  
    return (
      <>
       
      <div>
        <JSNavBar></JSNavBar>
        <Container>
        
          <Row className="vh-100 d-flex justify-content-center align-items-center">
            <Col md={8} lg={6} xs={12}>
            <div className="border border-2 border-primary"></div>
              <Card className="shadow px-4">
                <Card.Body>
                  <div className="mb-3 mt-md-4">
                    <h2 className="fw-bold mb-2 text-center text-uppercase ">User Details</h2>
                    <div className="mb-3">
                      <Form method='post'> 
                        <Form.Group className="mb-3" controlId="Name">
                          <Form.Label className="text-center">
                            Full Name
                          </Form.Label>
                          <Form.Control  type="text"
                              placeholder="Enter Name"
                              name="jsFullName"
                              value={formData.jsFullName}
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
                          controlId="formBasicCheckbox"
                        >
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
                        <Form.Group
                          className="mb-3"
                          controlId="formBasicCheckbox"
                        >
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
                          <Form.Label>Gender</Form.Label> </Form.Group>
                        <Form.Select aria-label="Default select example"
                              name="gender"
                              value={formData.gender}
                              onChange={handleChange}
                              >                            
                              <option value="MALE">Male</option>
                              <option value="FEMALE">Female</option>
                          </Form.Select>
                          <Form.Group
                          className="mb-3"
                          controlId="formBasicPassword"
                        >
                          <Form.Label>Education</Form.Label>
                          <Form.Control type="text" placeholder="Education"
                             name="education"
                             value={formData.education}
                             onChange={handleChange} 
                          />
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
                          <Form.Label>Skill1</Form.Label>
                          <Form.Control type="text" placeholder="Skill1" 
                             name="skill1"
                             value={formData.skill1}
                             onChange={handleChange}
                          />
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
                          <Form.Label>Skill 2</Form.Label>
                          <Form.Control type="text" placeholder="Skill2"
                             name="skill2"
                             value={formData.skill2}
                             onChange={handleChange}
                          />
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
                          <Form.Label>Skill 3</Form.Label>
                          <Form.Control type="text" placeholder="Skill3" 
                             name="skill3"
                             value={formData.skill3}
                             onChange={handleChange}
                          />
                        </Form.Group>
                        <Form.Group
                          className="mb-3"
                          controlId="formBasicCheckbox"
                        >
                        </Form.Group>
                        <div className="d-grid">
                          <Button variant="primary" type="submit" onClick={handleSubmit}>
                            Update
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
    
      </>
  
    );
}

export default JSUpdate