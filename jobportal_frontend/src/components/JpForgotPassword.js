import React, { useState } from 'react';
import { Container, Card, Form, Button, Alert } from 'react-bootstrap';
import axios from 'axios';
import NavBar from './NavBar';

export default function JpForgotPassword() {
    const [userName, setUserName] = useState('');
    const [step, setStep] = useState(1);
  const [otp, setOtp] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [message, setMessage] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  const handleSendOTP = async () => {
    try {
      const response = await axios.get(`http://localhost:7070/jobprovider/forgotpass/${userName}`);
      setMessage('OTP has been sent to your email. Please check your inbox.');
      setStep(2);
    } catch (error) {
      setErrorMessage('Failed to send OTP. Please check your username.');
    }
  };

  const handleVerifyOTP = async () => {
    try {
      const response = await axios.get(`http://localhost:7070/jobprovider/verify/${userName}/${otp}`);
      setMessage('OTP verified successfully. You can now reset your password.');
      setStep(3);
    } catch (error) {
      setErrorMessage('Invalid OTP. Please try again.');
    }
  };

  const handleResetPassword = async () => {
    try {
      const response = await axios.post(`http://localhost:7070/jobprovider/resetpass/${userName}`, {
        newPassword: newPassword,
      });
      setMessage('Password reset successful. You can now log in with your new password.');
    } catch (error) {
      setErrorMessage('Failed to reset password. Please try again.');
    }
  };
  return (
    <div>
        <NavBar></NavBar>
         <Container>
      <Card className="shadow px-4">
        <Card.Body>
          <div className="mb-3 mt-md-4">
            <h2 className="fw-bold mb-2 text-center text-uppercase">Forgot Password Flow</h2>
            <div className="mb-3">
              {step === 1 && (
                <div>
                  <Form.Group className="mb-3" controlId="userName">
                    <Form.Label className="text-center">User Name</Form.Label>
                    <Form.Control
                      type="text"
                      name="userName"
                      value={userName}
                      onChange={(e) => setUserName(e.target.value)}
                    />
                  </Form.Group>
                  <div className="d-grid">
                    <Button variant="primary" onClick={handleSendOTP}>
                      Send OTP
                    </Button>
                  </div>
                </div>
              )}
              {step === 2 && (
                <div>
                  <Form.Group className="mb-3" controlId="otp">
                    <Form.Label className="text-center">Enter OTP</Form.Label>
                    <Form.Control
                      type="text"
                      name="otp"
                      value={otp}
                      onChange={(e) => setOtp(e.target.value)}
                    />
                  </Form.Group>
                  <div className="d-grid">
                    <Button variant="primary" onClick={handleVerifyOTP}>
                      Verify OTP
                    </Button>
                  </div>
                </div>
              )}
              {step === 3 && (
                <div>
                  <Form.Group className="mb-3" controlId="newPassword">
                    <Form.Label className="text-center">Enter New Password</Form.Label>
                    <Form.Control
                      type="password"
                      name="newPassword"
                      value={newPassword}
                      onChange={(e) => setNewPassword(e.target.value)}
                    />
                  </Form.Group>
                  <div className="d-grid">
                    <Button variant="primary" onClick={handleResetPassword}>
                      Reset Password
                    </Button>
                  </div>
                </div>
              )}
              {message && <Alert variant="success">{message}</Alert>}
              {errorMessage && <Alert variant="danger">{errorMessage}</Alert>}
            </div>
          </div>
        </Card.Body>
      </Card>
    </Container>

    </div>
  )
}
