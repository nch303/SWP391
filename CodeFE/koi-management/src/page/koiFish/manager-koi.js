import React, { useEffect, useState } from 'react';
import './manager-koi.css';
import { Link } from 'react-router-dom';

const ManagerKoi = () => {
    const [koiFish, setKoiFish] = useState([]);
    const [filteredKoiFish, setFilteredKoiFish] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [selectedVariety, setSelectedVariety] = useState('');

    const defaultImage = "png-transparent-koi-fish-koi-aquarium-japanese-pond-water-japan-swim-aquatic-goldfish-thumbnail.png";

    useEffect(() => {
        // Sample data for Koi fish
        const sampleData = [
            { id: 1, name: 'Goldie', age: 3, variety: 'Kohaku', length: 10, weight: 2, image: defaultImage },
            { id: 2, name: 'Scales', age: 2, variety: 'Sanke', length: 8, weight: 1.5, image: defaultImage },
            { id: 3, name: 'Finley', age: 4, variety: 'Showa', length: 12, weight: 3, image: defaultImage },
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

    const [showPopup, setShowPopup] = useState(false);
    const [newKoiFish, setNewKoiFish] = useState({
        name: '',
        age: '',
        variety: '',
        length: '',
        weight: '',
        image: '',
    });

    const addKoiFish = () => {
        setShowPopup(true);
    };

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setNewKoiFish({ ...newKoiFish, [name]: value });
    };

    const handleSubmit = () => {
        // Assuming there's a function to add a new koi fish to the state or database
        // This is a placeholder for the actual logic to add a new koi fish
        console.log('Add Koi Fish functionality is not implemented yet.');
        setShowPopup(false);
    };

    const handleCancel = () => {
        setShowPopup(false);
    };

    const popupContent = (
        <div className="popup-content">
            <h2>Add New Koi Fish</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="name">Name:</label>
                    <input type="text" id="name" name="name" value={newKoiFish.name} onChange={handleInputChange} required />
                </div>
                <div>
                    <label htmlFor="age">Age:</label>
                    <input type="number" id="age" name="age" value={newKoiFish.age} onChange={handleInputChange} required />
                </div>
                <div>
                    <label htmlFor="variety">Variety:</label>
                    <select id="variety" name="variety" value={newKoiFish.variety} onChange={handleInputChange} required>
                        <option value="">Select Variety</option>
                        <option value="Kohaku">Kohaku</option>
                        <option value="Sanke">Sanke</option>
                        <option value="Showa">Showa</option>
                    </select>
                </div>
                <div>
                    <label htmlFor="length">Length (inches):</label>
                    <input type="number" id="length" name="length" value={newKoiFish.length} onChange={handleInputChange} required />
                </div>
                <div>
                    <label htmlFor="weight">Weight (pounds):</label>
                    <input type="number" id="weight" name="weight" value={newKoiFish.weight} onChange={handleInputChange} required />
                </div>
                <div>
                    <label htmlFor="image">Image URL:</label>
                    <input type="text" id="image" name="image" value={newKoiFish.image} onChange={handleInputChange} required />
                </div>
                <div className="button-group">
                    <button onClick={handleCancel} className="cancel-button">Cancel</button>
                    <button type="submit" className="confirm-button">Confirm</button>
                </div>
            </form>
        </div>
    );

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
                <button onClick={addKoiFish} className="add-koi-button">+</button>
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
                            <Link to={`/koi-info/${koi.id}`} className="see-more-button">See More Details</Link>
                        </div>
                    </div>
                ))}
            </div>
            {showPopup && popupContent}
        </div>
    );
};

export default ManagerKoi;
        