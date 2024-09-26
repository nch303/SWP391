import React, { useEffect, useState } from 'react';
import './manager-koi.css';

const ManagerKoi = () => {
    const [koiFish, setKoiFish] = useState([]);
    const [filteredKoiFish, setFilteredKoiFish] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [selectedVariety, setSelectedVariety] = useState('');

    useEffect(() => {
        // Sample data for Koi fish
        const sampleData = [
            { id: 1, name: 'Goldie', age: 3, variety: 'Kohaku', length: 10, weight: 2, image: '/images/goldie.png' },
            { id: 2, name: 'Scales', age: 2, variety: 'Sanke', length: 8, weight: 1.5, image: '/images/scales.png' },
            { id: 3, name: 'Finley', age: 4, variety: 'Showa', length: 12, weight: 3, image: '/images/finley.png' },
        ];
        setKoiFish(sampleData);
    }, []);

    useEffect(() => {
        const filtered = koiFish.filter(koi => 
            koi.name.toLowerCase().includes(searchTerm.toLowerCase()) && 
            (selectedVariety === '' || koi.variety === selectedVariety)
        );
        setFilteredKoiFish(filtered);
    }, [koiFish, searchTerm, selectedVariety]);

    return (
        <div className="manager-koi-container">
            <h1 className="koi-fish-title">My Koi Fish Dashboard</h1>
            <div className="filter-search">
                <input 
                    type="text" 
                    value={searchTerm} 
                    onChange={(e) => setSearchTerm(e.target.value)} 
                    placeholder="Search by name..."
                    className="search-input"
                />
                <select 
                    value={selectedVariety} 
                    onChange={(e) => setSelectedVariety(e.target.value)}
                    className="filter-select"
                >
                    <option value="">All Varieties</option>
                    <option value="Kohaku">Kohaku</option>
                    <option value="Sanke">Sanke</option>
                    <option value="Showa">Showa</option>
                </select>
            </div>
            <div className="koi-fish-dashboard">
                {filteredKoiFish.map((koi) => (
                    <div key={koi.id} className="koi-fish">
                        <div className="koi-fish-info">
                            <h2 className="koi-fish-name">{koi.name}</h2>
                            <img src={koi.image} alt={koi.name} className="koi-fish-image" />
                            <p className="koi-fish-detail">Age: {koi.age}</p>
                            <p className="koi-fish-detail">Variety: {koi.variety}</p>
                            <p className="koi-fish-detail">Length: {koi.length} inches</p>
                            <p className="koi-fish-detail">Weight: {koi.weight} pounds</p>
                            <button className="see-more-button">See More Details</button>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ManagerKoi;
