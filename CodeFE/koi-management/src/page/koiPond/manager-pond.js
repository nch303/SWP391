import React, { useEffect, useState } from 'react';
import './manager-pond.css';
import AddPondModal from './AddPondModal';
import UpdatePondModal from './UpdatePondModal';

const ManagerPond = () => {
    const [ponds, setPond] = useState([]);
    const [filteredPond, setFilteredPond] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [selectedVariety, setSelectedVariety] = useState('');
    const [isAddPondModalOpen, setIsAddPondModalOpen] = useState(false);
    const [updatePondId, setUpdatePondId] = useState(null);

    const defaultImage = "tac-dong-cua-anh-nang-mt-troi-doi-voi-ho-ca-koi-1.jpg";

    useEffect(() => {
        const sampleData = [
            { id: 1, name: 'First pond', noFish: 3, volume: 1500, depth: 2, pCapacity: 200, drains: 5, skimmers: 2, image: defaultImage },
            { id: 2, name: 'Second pond', noFish: 2, volume: 1000, depth: 1.5, pCapacity: 100, drains: 2, skimmers: 1, image: defaultImage },
            { id: 3, name: 'Third pond', noFish: 4, volume: 1250, depth: 2.5, pCapacity: 150, drains: 3, skimmers: 2, image: defaultImage },
        ];
        setPond(sampleData);
    }, []);

    useEffect(() => {
        const filtered = ponds.filter(pond =>
            pond.name.toLowerCase().includes(searchTerm.toLowerCase()) &&
            (selectedVariety === '' || pond.variety === selectedVariety)
        );
        setFilteredPond(filtered);
    }, [ponds, searchTerm, selectedVariety]);

    const openAddPondModal = () => {
        setIsAddPondModalOpen(true);
    };

    const closeAddPondModal = () => {
        setIsAddPondModalOpen(false);
    };

    const handleAddPond = (newPond) => {
        setPond([...ponds, { ...newPond, id: ponds.length + 1 }]);
        closeAddPondModal();
    };

    const openUpdateModal = (id) => {
        setUpdatePondId(id);
    };

    const closeUpdateModal = () => {
        setUpdatePondId(null);
    };

    const handleUpdatePond = (updatedPond) => {
        setPond(ponds.map(pond => pond.id === updatedPond.id ? updatedPond : pond));
        closeUpdateModal();
    };

    return (
        <div className="manager-pond-container">
            <h1 className="pond-title">My Pond</h1>
            <div className="filter-search">
                <input
                    type="text"
                    value={searchTerm}
                    onChange={(e) => setSearchTerm(e.target.value)}
                    placeholder="Search by name..."
                    className="search-input"
                />
                {/*<select*/}
                {/*    value={selectedVariety}*/}
                {/*    onChange={(e) => setSelectedVariety(e.target.value)}*/}
                {/*    className="filter-select"*/}
                {/*>*/}
                {/*    <option value="">All Pond</option>*/}
                {/*</select>*/}
                <button className="add-pond-button" onClick={openAddPondModal}>+</button>
            </div>
            <div className="pond-dashboard">
                {filteredPond.map((pond) => (
                    <div key={pond.id} className="pond">
                        <div className="pond-info">
                            <h2 className="pond-name">{pond.name}</h2>
                            <img src={pond.image} alt={pond.name} className="pond-image" />
                            <p className="pond-detail">Number of fish: {pond.noFish}</p>
                            <p className="pond-detail">Volume: {pond.volume} l</p>
                            <p className="pond-detail">Depth: {pond.depth} m</p>
                            <p className="pond-detail">Pump capacity: {pond.pCapacity} l/h</p>
                            <p className="pond-detail">Drains: {pond.drains}</p>
                            <p className="pond-detail">Skimmers: {pond.skimmers}</p>
                            <button className="update-pond" onClick={() => openUpdateModal(pond.id)}>Update Pond</button>
                        </div>
                    </div>
                ))}
            </div>
            {isAddPondModalOpen && (
                <AddPondModal onClose={closeAddPondModal} onAddPond={handleAddPond} />
            )}
            {updatePondId && (
                <UpdatePondModal
                    pond={ponds.find(p => p.id === updatePondId)}
                    onClose={closeUpdateModal}
                    onUpdatePond={handleUpdatePond}
                />
            )}
        </div>
    );
}

export default ManagerPond;