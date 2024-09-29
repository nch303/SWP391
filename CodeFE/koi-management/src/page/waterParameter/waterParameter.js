import React, { useState, useEffect } from 'react';
import './waterParameter.css';

const WaterParameter = () => {
    const [parameters, setParameters] = useState([]);

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
                            <p>Oxygen: {param.oxygen}</p>
                            <p>Temperature: {param.temperature}</p>
                            <p>pH: {param.ph}</p>
                            <p>Nitrite: {param.nitrite}</p>
                            <p>Nitrate: {param.nitrate}</p>
                            <p>Phosphate: {param.phosphate}</p>
                            <p>Ammonia: {param.ammonia}</p>
                            <p>Hardness: {param.hardness}</p>
                            <p>Alkalinity: {param.alkalinity}</p>
                            <p>CO2: {param.co2}</p>
                            <p>Salt: {param.salt}</p>
                            <p>Total chlorine: {param.totalChlorine}</p>
                            <p>Free chlorine: {param.freeChlorine}</p>
                        </div>
                    </div>
                ))}
            </div>
            <button className="add-parameter-btn">+</button>
        </div>
    );
};

export default WaterParameter;
