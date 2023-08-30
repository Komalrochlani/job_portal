import React from 'react'
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const Logout = () => {
    const navigate=useNavigate();
    useEffect(() => {
        localStorage.removeItem('isLoggedIn');
        localStorage.removeItem('userName');
      //  localStorage.removeItem('passwords');
        window.location.reload();
        navigate("/", { replace: true });
      }, []);
    
  return (
    <div>Logout</div>
  )
}

export default Logout