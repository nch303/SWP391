import React, { useState } from 'react';
import './AddPondModal.css';

const AddPondModal = ({ onClose, onAddPond }) => {
    const [name, setName] = useState('');
    const [volume, setVolume] = useState('');
    const [depth, setDepth] = useState('');
    const [pCapacity, setPCapacity] = useState('');
    const [drains, setDrains] = useState('');
    const [skimmers, setSkimmers] = useState('');
    const [image, setImage] = useState(null);

    const handleSubmit = (e) => {
        e.preventDefault();
        const newPond = {
            name,
            volume: parseFloat(volume),
            depth: parseFloat(depth),
            pCapacity: parseFloat(pCapacity),
            drains: parseInt(drains),
            skimmers: parseInt(skimmers),
            image: image ? URL.createObjectURL(image) : null,
            noFish: 0,
        };
        onAddPond(newPond);
    };

    const handleImageChange = (e) => {
        if (e.target.files && e.target.files[0]) {
            setImage(e.target.files[0]);
        }
    };

    return (
        <div className="add-pond-modal">
            <div className="modal-content">
                <h2>Add New Pond</h2>
                <form onSubmit={handleSubmit}>
                    <div>
                        <label htmlFor="name">Name: </label>
                        <input className="input-box" type="text" value={name} onChange={(e) => setName(e.target.value)} placeholder="Pond Name" required />
                    </div>
                    <div>
                        <label htmlFor="volume">Volume (l): </label>
                        <input className="input-box" type="number" value={volume} onChange={(e) => setVolume(e.target.value)} placeholder="Volume (l)" required />
                    </div>
                    <div>
                        <label htmlFor="depth">Depth (m): </label>
                        <input className="input-box" type="number" value={depth} onChange={(e) => setDepth(e.target.value)} placeholder="Depth (m)" required />
                    </div>
                    <div>
                        <label htmlFor="pCapacity">Pump Capacity (l/h): </label>
                        <input className="input-box" type="number" value={pCapacity} onChange={(e) => setPCapacity(e.target.value)} placeholder="Pump Capacity (l/h)" required />
                    </div>
                    <div>
                        <label htmlFor="drains">Number of Drains: </label>
                        <input className="input-box" type="number" value={drains} onChange={(e) => setDrains(e.target.value)} placeholder="Number of Drains" required />
                    </div>
                    <div>
                        <label htmlFor="skimmers">Number of Skimmers: </label>
                        <input className="input-box" type="number" value={skimmers} onChange={(e) => setSkimmers(e.target.value)} placeholder="Number of Skimmers" required />
                    </div>
                    <div>
                        <label htmlFor="image">Choose your pond image</label>
                        <input className="choose-image" type="file" onChange={handleImageChange} accept="image/*" />
                    </div>
                    <div className="button-group">
                        <button className="cancel-button" type="button" onClick={onClose}>Cancel</button>
                        <button className="confirm-button" type="submit">Add Pond</button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default AddPondModal;