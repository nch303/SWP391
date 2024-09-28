import React from 'react';
import './layout.css';
import { Link } from 'react-router-dom';
const logoIcon = '/iconlogo.png';

const NavBar = () => {
    return (
        <nav className="navbar">
            <div className="logo"><img src={logoIcon} alt="Sunside Koi Care Logo" style={{ width: '25%' }} />Sunside Koi Care</div>
            <ul className="nav-links">
                <li className="nav-link"><a href="/">Home</a></li>
                <li className="nav-link dropdown">
                    <li className="nav-link">Features</li>
                    <div className="dropdown-content">
                        <Link to="/manage-koi">My Koi</Link>
                        <Link to="/manage-pond">My Pond</Link>
                        <a href="#">Calculate Food</a>
                        <a href="#">Calculate Salt</a>
                    </div>
                </li>
                <li className="nav-link">About us</li>
                <li className="nav-link dropdown">
                    <a href="#">User</a>
                    <div className="dropdown-content">
                        <Link to="/user/profile">View Profile</Link>
                        <a href="#">Buy Plan</a>
                        <a href="#">Log Out</a>
                    </div>
                </li>
            </ul>
        </nav>
    );
};

export default NavBar;
