import React, { useState } from 'react';
import './food-calculator.css';

const FoodCalculator = () => {
    const [fishWeight, setFishWeight] = useState('');
    const [waterTemp, setWaterTemp] = useState('');
    const [season, setSeason] = useState('summer');
    const [foodAmount, setFoodAmount] = useState(null);

    const calculateFood = () => {
        if (!fishWeight || !waterTemp) {
            alert('Please enter both fish weight and water temperature.');
            return;
        }

        let basePercentage;
        if (waterTemp < 50) {
            basePercentage = 0; // Don't feed when water is too cold
        } else if (waterTemp < 60) {
            basePercentage = 0.5;
        } else if (waterTemp < 70) {
            basePercentage = 1;
        } else if (waterTemp < 80) {
            basePercentage = 2;
        } else {
            basePercentage = 3;
        }

        // Adjust for season
        if (season === 'winter') {
            basePercentage *= 0.5;
        } else if (season === 'spring' || season === 'fall') {
            basePercentage *= 0.8;
        }

        const calculatedAmount = (fishWeight * basePercentage) / 100;
        setFoodAmount(calculatedAmount.toFixed(2));
    };

    return (
        <div className="food-calculator-container">
            <h1>Koi Fish Food Calculator</h1>
            <div className="input-group">
                <label htmlFor="fishWeight">Total Fish Weight (lbs): </label>
                <input
                    type="number"
                    id="fishWeight"
                    value={fishWeight}
                    onChange={(e) => setFishWeight(e.target.value)}
                    min="0"
                    step="0.1"
                />
            </div>
            <div className="input-group">
                <label htmlFor="waterTemp">Water Temperature (Celsius): </label>
                <input
                    type="number"
                    id="waterTemp"
                    value={waterTemp}
                    onChange={(e) => setWaterTemp(e.target.value)}
                    min="0"
                    max="100"
                />
            </div>
            <div className="input-group">
                <label htmlFor="season">Season: </label>
                <select
                    id="season"
                    value={season}
                    onChange={(e) => setSeason(e.target.value)}
                >
                    <option value="spring">Spring</option>
                    <option value="summer">Summer</option>
                    <option value="fall">Fall</option>
                    <option value="winter">Winter</option>
                </select>
            </div>
            <button onClick={calculateFood} className="calculate-button">Calculate Food Amount</button>
            {foodAmount !== null && (
                <div className="result">
                    <h2>Recommended Daily Food Amount: </h2>
                    <p>{foodAmount} lbs</p>
                </div>
            )}
        </div>
    );
};

export default FoodCalculator;