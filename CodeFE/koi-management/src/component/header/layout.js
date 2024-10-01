import React from 'react';
import './layout.css';
import { Link } from 'react-router-dom';
const logoIcon = 'Icon.png';

const NavBar = () => {
    return (
        <nav className="navbar">
            <div className="logo">
                <img src={logoIcon} alt="Sunside Koi Care Logo" className="logo-img" /> 
                Sunside Koi Care
            </div>
            <ul className="nav-links">
                <li className="nav-link"><a href="/home">Home</a></li>
                <li className="nav-link dropdown">
                    <span className="nav-link">Features</span>
                    <div className="dropdown-content">
                        <Link to="/manage-koi">My Koi</Link>
                        <Link to="/manage-pond">My Pond</Link>
                        <Link to="/calculate-food">Calculate Food</Link>
                        <Link to="/calculate-salt">Calculate Salt</Link>
                        <Link to="/view-statistics">View Statistics</Link>
                        <Link to="/shopping-recommendations">Shopping Recommendations</Link>
                    </div>
                </li>
                <li className="nav-link"><a href="/about-us">About us</a></li>
                <li className="nav-link dropdown">
                    <span className="nav-link">User</span>
                    <div className="dropdown-content">
                        <Link to="/profile">View Profile</Link>
                        <a href="#">Buy Plan</a>
                        <a href="#">Log Out</a>
                    </div>
                </li>
                <li className="nav-link"><a href="/Shop-manager">Shop</a></li>
            </ul>
        </nav>
    );
};


const Background = () => {
    return (
        <div className="background" image = 'koi-bg.jpg'>
            {/* You can set a background image or gradient here */}
        </div>
    );
};

export {Background, NavBar};