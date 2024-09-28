import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import './koi-info.css'; // Import the CSS file

const KoiInfo = () => {
  const [koiData, setKoiData] = useState([]);
  const [showGrowHistory, setShowGrowHistory] = useState(false); // Added state for showGrowHistory
  const [notes, setNotes] = useState(''); // Added state for notes
  const { id } = useParams();

  useEffect(() => {
    // Sample data for Koi fish
    const sampleData = [
      {
        id: 1, name: 'Goldie', age: 3, variety: 'Kohaku', length: 10, weight: 2, image: '/images/goldie.png', color: 'Red and White',
        growthHistory: {
          year1: { date: '2020-01-01', length: 5, weight: 1 },
          year2: { date: '2021-01-01', length: 7, weight: 1.5 },
          year3: { date: '2022-01-01', length: 10, weight: 2 },
          year4: { date: '2023-01-01', length: 12, weight: 2.5 },
          year5: { date: '2024-01-01', length: 15, weight: 3 },
        }
      },
      {
        id: 2, name: 'Scales', age: 2, variety: 'Sanke', length: 8, weight: 1.5, image: '/images/scales.png', color: 'White and Black',
        growthHistory: {
          year1: { date: '2021-01-01', length: 5, weight: 1 },
          year2: { date: '2022-01-01', length: 7, weight: 1.5 },
        }
      },
      {
        id: 3, name: 'Finley', age: 4, variety: 'Showa', length: 12, weight: 3, image: '/images/finley.png', color: 'Black and White',
        growthHistory: {
          year1: { date: '2020-01-01', length: 5, weight: 1 },
          year2: { date: '2021-01-01', length: 7, weight: 1.5 },
          year3: { date: '2022-01-01', length: 10, weight: 2 },
          year4: { date: '2023-01-01', length: 12, weight: 3 },
        }
      },
    ];
    setKoiData(sampleData);
  }, []);

  const koi = koiData.find(koi => koi.id === parseInt(id));

  const handleSubmitGrowHistory = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const date = formData.get('date');
    const length = formData.get('length');
    const weight = formData.get('weight');

    // Update the growth history state
    const newGrowthHistory = { ...koi.growthHistory };
    const newYear = `year${Object.keys(koi.growthHistory).length + 1}`;
    newGrowthHistory[newYear] = { date, length, weight };

    // Update the koi data state
    const newKoiData = [...koiData];
    const index = newKoiData.findIndex((koi) => koi.id === parseInt(id));
    newKoiData[index].growthHistory = newGrowthHistory;

    setKoiData(newKoiData);
    setShowGrowHistory(false);
  };
  return (
    <div className="koi-info-container">
      <h1 className="koi-info-title">Koi Information</h1>
      {koi && (
        <div className="koi-info-detail">
          <h2 className="koi-name">{koi.name}</h2>
          <div className="koi-detail">Color: {koi.color}</div>
          <div className="koi-detail">Age: {koi.age} years old</div>
          <div className="koi-detail">Variety: {koi.variety}</div>
          <div className="koi-detail">Length: {koi.length} inches</div>
          <div className="koi-detail">Weight: {koi.weight} pounds</div>
          <img src={koi.image} alt={koi.name} className="koi-image" />
          <h3 className="grow-history-title">Grow History</h3>
          <div className="grow-history">
            <button onClick={() => setShowGrowHistory(!showGrowHistory)}>Add More History</button>
            {showGrowHistory && (
              <div className="popup-content">
                <form onSubmit={handleSubmitGrowHistory}>
                  <div>
                    <label htmlFor="date">Date:</label>
                    <input type="date" id="date" name="date" required />
                  </div>
                  <div>
                    <label htmlFor="length">Length (inches):</label>
                    <input type="number" id="length" name="length" required />
                  </div>
                  <div>
                    <label htmlFor="weight">Weight (pounds):</label>
                    <input type="number" id="weight" name="weight" required />
                  </div>
                  <div className="button-group">
                    <button type="submit" className="confirm-button">Submit</button>
                    <button onClick={() => setShowGrowHistory(false)} className="cancel-button">Cancel</button>
                  </div>
                </form>
              </div>
            )}
            {koi.growthHistory && (
              <>
                {Object.keys(koi.growthHistory).map((year, index) => (
                  <p key={index} className="grow-history-detail">
                    {`Year ${index + 1}: ${koi.growthHistory[year].date}, Length: ${koi.growthHistory[year].length} inches, Weight: ${koi.growthHistory[year].weight} pounds`}
                  </p>
                ))}
              </>
            )}

          </div>
          <h3 className="notes-title">Notes</h3>
          <textarea value={notes} onChange={(e) => setNotes(e.target.value)} placeholder="Add notes about the fish..." className="notes-textarea"></textarea>
        </div>
      )}
    </div>
  );
};

export default KoiInfo;