import React from 'react';

const Profile = () => {
    const profile = {
        name: 'John Doe',
        email: 'john@example.com',
        password: 'password123',
        currentPlan: 'Premium',
    };

    return (
        <div className="profile-container">
            <h2>Profile</h2>
            <p>Name: {profile.name}</p>
            <p>Email: {profile.email}</p>
            <p>Password: {profile.password}</p>
            <p>Current Plan: {profile.currentPlan}</p>
        </div>
    );
};

export default Profile;

