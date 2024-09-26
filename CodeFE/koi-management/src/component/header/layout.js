import React from 'react';
import './layout.css'; 
const logoIcon = '/iconlogo.png'; 

const NavBar = () => {
    return (
        <nav className="navbar">
            <div className="logo"><img src={logoIcon} alt="Sunside Koi Care Logo" style={{ width: '25%' }} />Sunside Koi Care</div>
            <ul className="nav-links">
                <li className="nav-link">Home</li>
                <li className="nav-link dropdown">
                    <li className="nav-link">Features</li>
                    <div className="dropdown-content">
                        <a href="/manage-koi">My Koi</a>
                        <a href="/manage-pond">My Pond</a>
                        <a href="#">Calculate Food</a>
                        <a href="#">Calculate Salt</a>
                    </div>
                </li>
                <li className="nav-link">About us</li>
                <li className="nav-link">User</li>
            </ul>
        </nav>
    );
};

export default NavBar;
