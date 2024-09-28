//import React, { useEffect, useState } from 'react';
//import './manager-pond.css';


//const ManagerPond = () => {
//    const [ponds, setPond] = useState([]);
//    const [filteredPond, setFilteredPond] = useState([]);
//    const [searchTerm, setSearchTerm] = useState('');
//    const [selectedVariety, setSelectedVariety] = useState('');

//    const defaultImage = "https://koilover.vn/uploads/images/tac-dong-cua-anh-nang-mt-troi-doi-voi-ho-ca-koi-1.jpg";

//    useEffect(() => {
//        const sampleData = [
//            { id: 1, name: 'First pond', noFish: 3, volume: 1500, depth: 2, pCapacity: 200, drains: 5, skimmers: 2, image: defaultImage },
//            { id: 2, name: 'Second pond', noFish: 2, volume: 1000, depth: 1.5, pCapacity: 100, drains: 2, skimmers: 1, image: defaultImage },
//            { id: 3, name: 'Third pond', noFish: 4, volume: 1250, depth: 2.5, pCapacity: 150, drains: 3, skimmers: 2, image: defaultImage },
//        ];
//        setPond(sampleData);
//    }, []);

//    useEffect(() => {
//        const filtered = ponds.filter(pond =>
//            pond.name.toLowerCase().includes(searchTerm.toLowerCase()) &&
//            (selectedVariety === '' || pond.variety === selectedVariety)
//        );
//        setFilteredPond(filtered);
//    }, [ponds, searchTerm, selectedVariety]);

//    return (
//        <div className="manager-pond-container">
//            <h1 className="pond-title">My Pond</h1>
//            <div className="filter-search">
//                <input
//                    type="text"
//                    value={searchTerm}
//                    onChange={(e) => setSearchTerm(e.target.value)}
//                    placeholder="Search by name..."
//                    className="search-input"
//                />
//                <select
//                    value={selectedVariety}
//                    onChange={(e) => setSelectedVariety(e.target.value)}
//                    className="filter-select"
//                >
//                    <option value="">All Pond</option>
//                </select>
//            </div>
//            <div className="pond-dashboard">
//                {filteredPond.map((pond) => (
//                    <div key={pond.id} className="pond">
//                        <div className="pond-info">
//                            <h2 className="pond-name">{pond.name}</h2>
//                            <img src={pond.image} alt={pond.name} className="pond-image" />
//                            <p className="pond-detail">Number of fish: {pond.noFish}</p>
//                            <p className="pond-detail">Volume: {pond.volume} l</p>
//                            <p className="pond-detail">Depth: {pond.depth} m</p>
//                            <p className="pond-detail">Pump capacity: {pond.pCapacity} l/h</p>
//                            <p className="pond-detail">Drains: {pond.drains}</p>
//                            <p className="pond-detail">Skimmers: {pond.skimmers}</p>
//                            <button className="update-pond">Update Pond</button>
//                            <div class="modal-content hidden-modal">
//                                <div class="modal-header">
//                                    <h3>Change data of this pond</h3>
//                                    <button class="close-modal">&times;</button>
//                                </div>
//                                <div class="modal-body">
//                                    <p>updating...</p>
//                                </div>
//                            </div>
//                            <div class="blur-bg hidden-blur"></div>
//                        </div>
//                    </div>
//                ))}
//            </div>
//        </div>
//    );
//}

// let modalContent = document.querySelector(".modal-content");
// let openModal = document.querySelector(".update-pond");
// let closeModal = document.querySelector(".close-modal");
// let blurBg = document.querySelector(".blur-bg");

// openModal.addEventListener("click", function () {
//     modalContent.classList.remove("hidden-modal");
//     blurBg.classList.remove("hidden-blur");
// });
// let closeModalFunction = function () {
//     modalContent.classList.add("hidden-modal")
//     blurBg.classList.add("hidden-blur")
// }

// blurBg.addEventListener("click", closeModalFunction);
// closeModal.addEventListener("click", closeModalFunction);

// document.addEventListener("keydown", function (event) {
//     if (event.key === "Escape"
//         && !modalContent.classList.contains("hidden")
//     ) {
//         closeModalFunction();
//     }
// });


//export default ManagerPond;


import React, { useEffect, useState } from 'react';
import './manager-pond.css';

const ManagerPond = () => {
    const [ponds, setPond] = useState([]);
    const [filteredPond, setFilteredPond] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [selectedVariety, setSelectedVariety] = useState('');
    const [openModalId, setOpenModalId] = useState(null);

    const defaultImage = "https://koilover.vn/uploads/images/tac-dong-cua-anh-nang-mt-troi-doi-voi-ho-ca-koi-1.jpg";

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

    const openModal = (id) => {
        setOpenModalId(id);
    };

    const closeModal = () => {
        setOpenModalId(null);
    };

    const acceptModal = () => {
        setPond();
    }

    // Handle Escape key press
    useEffect(() => {
        const handleEscapeKey = (event) => {
            if (event.key === "Escape") {
                closeModal();
            }
        };

        document.addEventListener("keydown", handleEscapeKey);

        // Cleanup function
        return () => {
            document.removeEventListener("keydown", handleEscapeKey);
        };
    }, []);

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
                            <button className="update-pond" onClick={() => openModal(pond.id)}>Update Pond</button>
                            {openModalId === pond.id && (
                                <div className="modal-content">
                                    <div className="modal-header">
                                        <button className="close-modal" onClick={closeModal}>&times;</button>
                                        <button className="accept-modal" onClick={closeModal}>&#x2713;</button>
                                    </div>
                                    <h1>Change data of this pond</h1>
                                    <div className="modal-body">
                                        <input
                                            type="text"
                                            value={searchTerm}
                                            onChange={(e) => setSearchTerm(e.target.value)}
                                            placeholder="Search by name..."
                                            className="search-input"
                                        />
                                    </div>
                                </div>
                            )}
                        </div>
                    </div>
                ))}
            </div>
            {openModalId !== null && <div className="blur-bg" onClick={closeModal}></div>}
        </div>
    );
}

export default ManagerPond;