import React from 'react';
import { Row, Col } from 'antd';

function Footer() {
  return (
    <footer className="footer" style={{ backgroundColor: '#333' }}>
      <Row className="footer-content">
        <Col className="footer-col" xs={24} sm={8}>
          <h4 className="footer-title" style={{ color: '#fff' }}>Company</h4>
          <ul className="footer-list">
            <li><a href="#" style={{ color: '#fff' }}>About Us</a></li>
            <li><a href="#" style={{ color: '#fff' }}>Careers</a></li>
            <li><a href="#" style={{ color: '#fff' }}>Blog</a></li>
          </ul>
        </Col>
        <Col className="footer-col" xs={24} sm={8}>
          <h4 className="footer-title" style={{ color: '#fff' }}>Support</h4>
          <ul className="footer-list">
            <li><a href="#" style={{ color: '#fff' }}>Contact Us</a></li>
            <li><a href="#" style={{ color: '#fff' }}>Help Center</a></li>
            <li><a href="#" style={{ color: '#fff' }}>Terms of Service</a></li>
            <li><a href="#" style={{ color: '#fff' }}>Privacy Policy</a></li>
          </ul>
        </Col>
        <Col className="footer-col" xs={24} sm={8}>
          <h4 className="footer-title" style={{ color: '#fff' }}>Follow Us</h4>
          <ul className="footer-list">
            <li><a href="#" style={{ color: '#fff' }}>Facebook</a></li>
            <li><a href="#" style={{ color: '#fff' }}>Twitter</a></li>
            <li><a href="#" style={{ color: '#fff' }}>YouTube</a></li>
          </ul>
        </Col>
      </Row>
    </footer>

  )
}

export default Footer;

