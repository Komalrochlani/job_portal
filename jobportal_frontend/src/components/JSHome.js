import React, { useState, useEffect } from 'react';
import axios from 'axios';
import JSNavBar from './JSNavBar';

export default function JSHome() {
  // const [userName, setUserName] = useState(null);

  // useEffect(() => {
  //   // Make an API call to retrieve user profile data
  //   axios.get('/jobseeker/profile')
  //     .then(response => {
  //       setUserName(response.data.userName);
  //     })
  //     .catch(error => {
  //       console.error('Error fetching user profile:', error);
  //     });
  // }, []); 
  let userName = localStorage.getItem('userName');

  return (
    <div>
      <JSNavBar />
      <div>
        {userName ? (
          <p>Welcome, {userName}!</p>
        ) : (
          <p>Loading user profile...</p>
        )}
      </div>
    </div>
  );
}
