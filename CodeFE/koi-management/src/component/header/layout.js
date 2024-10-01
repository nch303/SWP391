import React from 'react';
import './layout.css';
import { Link } from 'react-router-dom';
const logoIcon = 'Icon.png';

export const NavBar = () => {
    return (
        <nav className="navbar">
            <div className="logo">
                <img src={logoIcon} alt="Sunside Koi Care Logo" style={{ width: '6%' }} /> Sunside Koi Care
            </div>
            <ul className="nav-links">
                <li className="nav-link"><a href="/">Home</a></li>
                <li className="nav-link dropdown">
                    <li className="nav-link">Features</li>
                    <div className="dropdown-content">
                        <Link to="/manage-koi">My Koi</Link>
                        <Link to="/manage-pond">My Pond</Link>
                        <Link to="/calculate-food">Calculate Food</Link>
                        <Link to="/calculate-salt">Calculate Salt</Link>
                        <Link to="/view-statistics">View Statistics</Link>
                        <Link to="/shopping-recommendations">Shopping Recommendations</Link>
                    </div>
                </li>
                <li className="nav-link">About us</li>
                <li className="nav-link dropdown">
                    <a href="#">User</a>
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

