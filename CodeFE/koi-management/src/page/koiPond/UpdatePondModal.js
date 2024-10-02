import React, { useState, useEffect } from 'react';
import './UpdatePondModal.css';

const UpdatePondModal = ({ pond, onClose, onUpdatePond }) => {
    const [name, setName] = useState(pond.name);
    const [volume, setVolume] = useState(pond.volume);
    const [depth, setDepth] = useState(pond.depth);
    const [pCapacity, setPCapacity] = useState(pond.pCapacity);
    const [drains, setDrains] = useState(pond.drains);
    const [skimmers, setSkimmers] = useState(pond.skimmers);
    const [image, setImage] = useState(null);

    useEffect(() => {
        const handleEscapeKey = (event) => {
            if (event.key === "Escape") {
                onClose();
            }
        };

        document.addEventListener("keydown", handleEscapeKey);

        return () => {
            document.removeEventListener("keydown", handleEscapeKey);
        };
    }, [onClose]);

    const handleSubmit = (e) => {
        e.preventDefault();
        const updatedPond = {
            ...pond,
            name,
            volume: parseFloat(volume),
            depth: parseFloat(depth),
            pCapacity: parseFloat(pCapacity),
            drains: parseInt(drains),
            skimmers: parseInt(skimmers),
            image: image ? URL.createObjectURL(image) : pond.image,
        };
        onUpdatePond(updatedPond);
    };

    const handleImageChange = (e) => {
        if (e.target.files && e.target.files[0]) {
            setImage(e.target.files[0]);
        }
    };

    return (
        <div className="update-pond-modal">
            <div className="modal-content">
                <h2>Update Pond</h2>
                <form onSubmit={handleSubmit}>
                    <input type="text" value={name} onChange={(e) => setName(e.target.value)} placeholder="Pond Name" required />
                    <input type="number" value={volume} onChange={(e) => setVolume(e.target.value)} placeholder="Volume (l)" required />
                    <input type="number" value={depth} onChange={(e) => setDepth(e.target.value)} placeholder="Depth (m)" required />
                    <input type="number" value={pCapacity} onChange={(e) => setPCapacity(e.target.value)} placeholder="Pump Capacity (l/h)" required />
                    <input type="number" value={drains} onChange={(e) => setDrains(e.target.value)} placeholder="Number of Drains" required />
                    <input type="number" value={skimmers} onChange={(e) => setSkimmers(e.target.value)} placeholder="Number of Skimmers" required />
                    <input type="file" onChange={handleImageChange} accept="image/*" />
                    <div className="button-group">
                        <button type="submit">Update Pond</button>
                        <button type="button" onClick={onClose}>Cancel</button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default UpdatePondModal;