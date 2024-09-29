import React, { useState, useEffect } from 'react';
import './waterParameter.css';

const WaterParameter = () => {
    const [parameters, setParameters] = useState([]);
    const [showPopup, setShowPopup] = useState(false);

    useEffect(() => {
        // Simulated data fetch
        const sampleData = [
            {
                id: 1,
                date: '05.29.2024',
                time: '13:29',
                pondName: 'Main pond',
                oxygen: '8.4 mg/l',
                temperature: '18.5 °C',
                ph: '7',
                nitrite: '0.3 mg/l',
                nitrate: '20 mg/l',
                phosphate: '0.025 mg/l',
                ammonia: '0.25 mg/l',
                hardness: '10 °dH',
                alkalinity: '5.2 °dH',
                co2: '17 mg/l',
                salt: '0.05 %',
                totalChlorine: '0.001 mg/l',
                freeChlorine: '0 mg/l'
            },
            {
                id: 2,
                date: '06.05.2024',
                time: '07:42',
                pondName: 'Main pond',
                oxygen: '8.1 mg/l',
                temperature: '19 °C',
                ph: '8',
                nitrite: '0.2 mg/l',
                nitrate: '25 mg/l',
                phosphate: '0.025 mg/l',
                ammonia: '0.1 mg/l',
                hardness: '20 °dH',
                alkalinity: '4 °dH',
                co2: '25 mg/l',
                salt: '0.1 %',
                totalChlorine: '0.001 mg/l',
                freeChlorine: '0 mg/l'
            }
        ];
        setParameters(sampleData);
    }, []);

    const handleAddParameter = () => {
        setShowPopup(true);
    };

    const handleClosePopup = () => {
        setShowPopup(false);
    };

    const popupContent = (
        <div className="popup-content">
            <h2>Add New Water Parameter</h2>
            <p>Feature not supported</p>
            <button onClick={handleClosePopup}>Close</button>
        </div>
    );

    return (
        <div className="water-parameter-container">
            <h1 className="parameter-title">Water Parameters</h1>
            <div className="parameter-list">
                {parameters.map((param) => (
                    <div key={param.id} className="parameter-card">
                        <div className="parameter-header">
                            <span>{param.date} - {param.time}</span>
                            <span>{param.pondName}</span>
                        </div>
                        <div className="parameter-details">
                            <div className="parameter-item">
                                <span className="parameter-label">Oxygen:</span>
                                <span className="parameter-value">{param.oxygen}</span>
                            </div>
                            <div className="parameter-item">
                                <span className="parameter-label">Temperature:</span>
                                <span className="parameter-value">{param.temperature}</span>
                            </div>
                            <div className="parameter-item">
                                <span className="parameter-label">pH:</span>
                                <span className="parameter-value">{param.ph}</span>
                            </div>
                            <div className="parameter-item">
                                <span className="parameter-label">Nitrite:</span>
                                <span className="parameter-value">{param.nitrite}</span>
                            </div>
                            <div className="parameter-item">
                                <span className="parameter-label">Nitrate:</span>
                                <span className="parameter-value">{param.nitrate}</span>
                            </div>
                            <div className="parameter-item">
                                <span className="parameter-label">Phosphate:</span>
                                <span className="parameter-value">{param.phosphate}</span>
                            </div>
                            <div className="parameter-item">
                                <span className="parameter-label">Ammonia:</span>
                                <span className="parameter-value">{param.ammonia}</span>
                            </div>
                            <div className="parameter-item">
                                <span className="parameter-label">Hardness:</span>
                                <span className="parameter-value">{param.hardness}</span>
                            </div>
                            <div className="parameter-item">
                                <span className="parameter-label">Alkalinity:</span>
                                <span className="parameter-value">{param.alkalinity}</span>
                            </div>
                            <div className="parameter-item">
                                <span className="parameter-label">CO2:</span>
                                <span className="parameter-value">{param.co2}</span>
                            </div>
                            <div className="parameter-item">
                                <span className="parameter-label">Salt:</span>
                                <span className="parameter-value">{param.salt}</span>
                            </div>
                            <div className="parameter-item">
                                <span className="parameter-label">Total chlorine:</span>
                                <span className="parameter-value">{param.totalChlorine}</span>
                            </div>
                            <div className="parameter-item">
                                <span className="parameter-label">Free chlorine:</span>
                                <span className="parameter-value">{param.freeChlorine}</span>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
            <button className="add-parameter-btn" onClick={handleAddParameter}>+</button>
            {showPopup && popupContent}
        </div>
    );
};

export default WaterParameter;
