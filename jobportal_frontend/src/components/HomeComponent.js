import React from 'react';
import NavBar from './NavBar';
import '../css/HomeComponent.css'
import Carousel from 'react-bootstrap/Carousel'; // Import the Carousel component

export default function HomeComponent() {
  return (
    <div>
      <NavBar />

      <div className="home-content">
        <h1>Welcome to The Job Portal</h1>
        <p>Find your dream job here. Explore opportunities from top companies.</p>
      </div>
      <h2>Our Recruiters</h2>
      <Carousel className="carousel-container">
        {/* Slide 1 */}
        <Carousel.Item>
          <img
            className="d-block w-100"
            src="/imgs/microsoft.jpg"
            alt="Slide 1"
          />
          
        </Carousel.Item>

        {/* Slide 2 */}
        <Carousel.Item>
          <img
            className="d-block w-100"
            src="/imgs/deutsch.png"
            alt="Slide 2"
          />
          
        </Carousel.Item>

        <Carousel.Item>
          <img
            className="d-block w-100"
            src="/imgs/google.webp"
            alt="Slide 2"
          />
          
        </Carousel.Item>

        {/* Add more slides as needed */}
      </Carousel>
      
    </div>
  );
}
