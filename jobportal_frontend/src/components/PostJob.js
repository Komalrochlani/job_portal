import React,{useState,useEffect} from 'react'
import { Col, Button, Row, Container, Card, Form } from "react-bootstrap";
import { useNavigate } from 'react-router-dom';

import axios from 'axios';
import JPNavBar from './JPNavBar';

export default function PostJob() {
    let userName = localStorage.getItem('userName');
    const navigate=useNavigate();
    const [jobProvider,setJobProvider]=useState([])
    const [category, setcategory] = useState([]);
    const [location, setlocation] = useState([]);
    const [showCategoryForm, setShowCategoryForm] = useState(false);
    const [formData, setFormData] = useState(
        {
            assignedJpId: `${jobProvider.jpId}`,
            assignedJcId: 0,
            assignedLocationId:0 ,
            jobTitle: "",
            role: "",
            keySkills: "",
            salary: 0,
            description: "",
            vacancies: 0,
            applicationDeadline: ""
          }
    )

    useEffect(()=>{
        axios.get("http://localhost:7070/jobcategory/list")
        .then(response =>{
            setcategory(response.data)
        })
        .catch(error => {
            console.error(error);})

            axios.get("http://localhost:7070/location/list")
            .then(response =>{
                setlocation(response.data)
            })
            .catch(error => {
                console.error(error);})

            axios.get(`http://localhost:7070/getJPDetails/${userName}`)
            .then(response =>{
              console.log(response.data)
              setJobProvider(response.data)
            })
            .catch(error => {
              console.error(error);})
    },[])

    const handleSubmit = async (e) => {
        e.preventDefault();
    
        try {
          const response = await axios.post("http://localhost:7070/job/insertjob", formData);
          console.log('Registration response:', response.data);
          
          navigate('/jobprovider/jobs');
        } catch (error) {
          console.error('Registration error:', error);
          // Handle error
        }
      };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({ ...prevData, [name]: value }));
      };

      const toggleCategoryForm = () => {
        setShowCategoryForm(!showCategoryForm);
      };

  return (
    <div> 
      <JPNavBar></JPNavBar>
        <Container>
      
      <Row className="vh-100 d-flex justify-content-center align-items-center">
        <Col md={8} lg={6} xs={12}>
        <div className="border border-2 border-primary"></div>
          <Card className="shadow px-4">
            <Card.Body>
              <div className="mb-3 mt-md-4">
                <h2 className="fw-bold mb-2 text-center text-uppercase ">Post Job</h2>
                <div className="mb-3">
                  <Form action='/job/insertjob' method='post'> 
                    <Form.Group className="mb-3" controlId="Name">
                      <Form.Label className="text-center">
                        Job Provider Id
                      </Form.Label>
                      <Form.Control  type="text"                          
                          name="assignedJpId"
                          value={formData.assignedJpId}
                          onChange={handleChange} />
                    </Form.Group>

                    <Form.Group
                      className="mb-3"
                      controlId="formBasicPassword"
                    >
                      <Form.Label>Job Category</Form.Label> </Form.Group>
                    <Form.Select aria-label="Default select example"
                          name="assignedJcId"
                          value={formData.assignedJcId}
                          onChange={handleChange}
                          >     
                          <option value='' selected></option>
                          {category.map(cat=>(
                            <option key={cat.jcId} value={cat.jcId}>{cat.jcName}</option>
                          ))}                       
                         
                      </Form.Select>
                      {/* <div className="mb-3">
                        <Button variant="primary" onClick={toggleCategoryForm}>
                          Add Job Category
                        </Button></div>
                        {showCategoryForm && (
                              <Form>
                                <Form.Group className="mb-3" controlId="Name">
                                      <Form.Label className="text-center">
                                        Category Name
                                      </Form.Label>
                                      <Form.Control  type="text"                          
                                          name="jcName"
                                          value={formData.assignedJpId}
                                          onChange={handleChange} />
                                    </Form.Group>
                                <div className="d-grid">
                                  <Button variant="primary" type="submit">
                                    Add Category
                                  </Button>
                                </div>
                              </Form>
                            )} */}

                      <Form.Group
                      className="mb-3"
                      controlId="formBasicPassword"
                    >
                    <Form.Label>Location</Form.Label> </Form.Group>
                    <Form.Select aria-label="Default select example"
                          name="assignedLocationId"
                          value={formData.assignedLocationId}
                          onChange={handleChange}
                          >   
                          <option value='' selected></option>
                          {location.map(locate => (
                            <option key={locate.locationId} value={locate.locationId}>{locate.locationName}</option>
                          ))}                         

                      </Form.Select>

                    <Form.Group className="mb-3" controlId="formBasicEmail">
                      <Form.Label className="text-center">
                      Job Title
                      </Form.Label>
                      <Form.Control  type="text"
                         
                          name="jobTitle"
                          value={formData.jobTitle}
                          onChange={handleChange} />
                    </Form.Group>

                    <Form.Group
                      className="mb-3"
                      controlId="formBasicPassword"
                    >
                      <Form.Label>Role</Form.Label>
                      <Form.Control type="text" 
                          name="role"
                          value={formData.role}
                          onChange={handleChange} />
                    </Form.Group>
                    <Form.Group
                      className="mb-3"
                      controlId="formBasicPassword"
                    >
                      <Form.Label>Skills </Form.Label>
                      <Form.Control type="text" placeholder="Java,Python"
                          name="keySkills"
                          value={formData.keySkills}
                          onChange={handleChange}   />
                    </Form.Group>
                    <Form.Group
                      className="mb-3"
                      controlId="formBasicPassword"
                    >
                      <Form.Label>Salary </Form.Label>
                      <Form.Control type="text" 
                          name="salary"
                          value={formData.salary}
                          onChange={handleChange}   />
                    </Form.Group>
                    <Form.Group
                      className="mb-3"
                      controlId="formBasicPassword"
                    >
                      <Form.Label>Description </Form.Label>
                      <Form.Control type="text" 
                          name="description"
                          value={formData.description}
                          onChange={handleChange}   />
                    </Form.Group>
                    <Form.Group
                      className="mb-3"
                      controlId="formBasicPassword"
                    >
                      <Form.Label>vacancies </Form.Label>
                      <Form.Control type="number" 
                          name="vacancies"
                          value={formData.vacancies}
                          onChange={handleChange}   />
                    </Form.Group>
                    <Form.Group
                      className="mb-3"
                      controlId="formBasicPassword"
                    >
                      <Form.Label>Application Deadline </Form.Label>
                      <Form.Control type="date" 
                          name="applicationDeadline"
                          value={formData.applicationDeadline}
                          onChange={handleChange}   />
                    </Form.Group>
                    <div className="d-grid">
                      <Button variant="primary" type="submit" onClick={handleSubmit}>
                        Post Job
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
