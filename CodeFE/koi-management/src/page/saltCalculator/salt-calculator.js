import React, { useState } from 'react';
import './salt-calculator.css';

const SaltCalculator = () => {
    const [volume, setVolume] = useState('');
    const [currentSalinity, setCurrentSalinity] = useState('');
    const [targetSalinity, setTargetSalinity] = useState('');
    const [result, setResult] = useState(null);

    const calculateSalt = () => {
        const pondVolume = parseFloat(volume);
        const current = parseFloat(currentSalinity);
        const target = parseFloat(targetSalinity);

        if (isNaN(pondVolume) || isNaN(current) || isNaN(target)) {
            setResult('Please enter valid numbers for all fields.');
            return;
        }

        // Calculate the amount of salt needed (in grams)
        const saltNeeded = (target - current) * pondVolume * 1000 / 1000000;

        setResult(`You need to add ${saltNeeded.toFixed(2)} kg of salt to reach the target salinity.`);
    };

    return (
        <div className="salt-calculator">
            <h2>Koi Pond Salt Calculator</h2>
            <div>
                <label htmlFor="volume">Pond Volume (liters): </label>
                <input
                    type="number"
                    id="volume"
                    value={volume}
                    onChange={(e) => setVolume(e.target.value)}
                    placeholder="Enter pond volume"
                />
            </div>
            <div>
                <label htmlFor="currentSalinity">Current Salinity (ppt): </label>
                <input
                    type="number"
                    id="currentSalinity"
                    value={currentSalinity}
                    onChange={(e) => setCurrentSalinity(e.target.value)}
                    placeholder="Enter current salinity"
                />
            </div>
            <div>
                <label htmlFor="targetSalinity">Target Salinity (ppt): </label>
                <input
                    type="number"
                    id="targetSalinity"
                    value={targetSalinity}
                    onChange={(e) => setTargetSalinity(e.target.value)}
                    placeholder="Enter target salinity"
                />
            </div>
            <button onClick={calculateSalt}>Calculate</button>
            {result && <div className="result">{result}</div>}
            <div className="info">
                <h3>Salinity Guidelines for Koi Fish:</h3>
                <ul>
                    <li>Normal freshwater: 0-0.5 ppt</li>
                    <li>Slight salinity (helps with stress): 1-3 ppt</li>
                    <li>Parasitic treatment: 3-5 ppt (short term only)</li>
                    <li>Warning: Do not exceed 5 ppt for koi fish</li>
                </ul>
            </div>
        </div>
    );
}

export default SaltCalculator;