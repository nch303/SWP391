import React from 'react';
import { Link } from 'react-router-dom'; // Import Link for navigation
import './welcome.css';
const koiImage = 'Icon.png'; // Adjust the path to your koi image

const Welcome = () => {
  return (
    <div className="welcome-container">
      <Link to="/login" className="login-button-header">Login/Register</Link>
      <header className="header">
        <h1>Welcome to <span className="highlight">Sunside Koi Care</span></h1>
      </header>
      <div className="content">
        <img src={koiImage} alt="Koi" className="koi-image" />
        <p className="info">
          Sunside Koi Care<br />
          Represented by: Group 7 - S1863<br />
          Street D1, Long Thanh My, Thu Duc City, Ho Chi Minh City<br />
          Tel: ...<br />
          Mail: ...<br />
          Privacy Policy
        </p>
      </div>
      <Link to="/login" className="login-button">Login/Register</Link>
    </div>
  );
};

export default Welcome;
