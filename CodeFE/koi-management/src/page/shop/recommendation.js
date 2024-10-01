import React, { useState } from 'react';
import { useParams } from 'react-router-dom';
import './recommendation.css';

const Recommendation = () => {

    const [selectedCategory, setSelectedCategory] = useState('All');
    const [searchTerm, setSearchTerm] = useState('');

    const categories = [
        'All',
        'Food',
        'Health',
        'Water Care',
        'Decorations',
    ];

    const recommendedItems = [
        {
            name: 'Koi Food',
            description: 'High quality koi food for optimal growth and health',
            price: 10.99,
            image: 'koi-food.png',
            category: 'Food',
        },
        {
            name: 'Pond Salt',
            description: 'Natural salt for maintaining healthy water parameters',
            price: 24.99,
            image: 'pond-salt.jpg',
            category: 'Water Care',
        },
        {
            name: 'Water Test Kit',
            description: 'Monitor your pond water parameters with this complete kit',
            price: 34.98,
            image: 'water-test-kit.jpg',
            category: 'Water Care',
        },
        {
            name: 'Koi Net',
            description: 'Good quality net for catching koi fish',
            price: 7.99,
            image: 'koi-net.jpg',
            category: 'Health',
        },
        {
            name: 'Pond Pump',
            description: 'Powerful pump for circulating water in your pond',
            price: 99.99,
            image: 'pond-pump.jpg',
            category: 'Decorations',
        },
        {
            name: 'Floating Plant',
            description: 'Beautiful floating plant for your pond',
            price: 19.99,
            image: 'floating-plant.jpg',
            category: 'Decorations',
        },
    ];

    const [sortBy, setSortBy] = useState('asc');

    const filteredItems = recommendedItems.filter(item => {
        return (
            (selectedCategory === 'All' || item.category === selectedCategory) &&
            item.name.toLowerCase().includes(searchTerm.toLowerCase())
        );
    });

    const sortedItems = [...filteredItems];
    if (sortBy === 'asc') {
        sortedItems.sort((a, b) => a.price - b.price);
    } else if (sortBy === 'desc') {
        sortedItems.sort((a, b) => b.price - a.price);
    }
    return (
        <div >
            <h1> Recommendation </h1>
            <div className="recommendation">
                <div className="sidebar">

                    <p>Search:</p>
                    <input
                        type="text"
                        value={searchTerm}
                        onChange={e => setSearchTerm(e.target.value)}
                        placeholder="Search by name"
                    />

                    <p>Filter by category:</p>
                    <select value={selectedCategory} onChange={e => setSelectedCategory(e.target.value)}>
                        {categories.map(category => (
                            <option key={category}>{category}</option>
                        ))}
                    </select>

                    <p>Sort by price:</p>
                    <select value={sortBy} onChange={e => setSortBy(e.target.value)}>
                        <option value="asc">Lowest price first</option>
                        <option value="desc">Highest price first</option>
                    </select>
                </div>

                <div className="main-content">
                    <div className="dashboard">
                        {filteredItems.map((item, index) => (
                            <div className="dashboard-item" key={index}>
                                <img src={item.image} alt={item.name} className="dashboard-item-image" />
                                <h3>{item.name}</h3>
                                <p>{item.description}</p>
                                <p>Price: ${item.price}</p>
                            </div>
                        ))}
                    </div>
                </div>
            </div>
        </div>
    );
};


export default Recommendation;


