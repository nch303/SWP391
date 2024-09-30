import React from 'react';
import { useParams } from 'react-router-dom';
import './recommendation.css';

const Recommendation = () => {

    const recommendedItems = [
        {
            name: 'Koi Food',
            description: 'High quality koi food for optimal growth and health',
            price: 10.99,
            image: 'koi-food.png',
        },
        {
            name: 'Pond Salt',
            description: 'Natural salt for maintaining healthy water parameters',
            price: 24.99,
            image: 'pond-salt.jpg',
        },
        {
            name: 'Water Test Kit',
            description: 'Monitor your pond water parameters with this complete kit',
            price: 34.98,
            image: 'water-test-kit.jpg',
        },
    ];

    return (
        <div className="recommendation">
            <h2>Recommended Items</h2>
            <div className="dashboard">
                {recommendedItems.map((item, index) => (
                    <div className="dashboard-item" key={index}>
                        <img src={item.image} alt={item.name} className="dashboard-item-image" />
                        <h3>{item.name}</h3>
                        <p>{item.description}</p>
                        <p>Price: ${item.price}</p>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Recommendation;

