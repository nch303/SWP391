import React, { useEffect, useState } from "react";
import "./index.scss";
import api from "../../../config/axios";
import { useNavigate } from "react-router-dom";
import Card from "../../../component/koi-card";

function ManagerKoi() {
  const navigate = useNavigate();
  const [koiFish, setKoiFish] = useState([]);
  const [filteredKoiFish, setFilteredKoiFish] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [selectedVariety, setSelectedVariety] = useState("");

  const defaultImage =
    "png-transparent-koi-fish-koi-aquarium-japanese-pond-water-japan-swim-aquatic-goldfish-thumbnail.png";

  useEffect(() => {
    // Sample data for Koi fish
    const sampleData = [
      {
        id: 1,
        name: "Goldie",
        age: 3,
        variety: "Kohaku",
        length: 10,
        weight: 2,
        image: "koi-goldie-kohaku.jpg",
      },
      {
        id: 2,
        name: "Scales",
        age: 2,
        variety: "Sanke",
        length: 8,
        weight: 1.5,
        image: "koi-scales-sanke.jpg",
      },
      {
        id: 3,
        name: "Finley",
        age: 4,
        variety: "Showa",
        length: 12,
        weight: 3,
        image: "koi-finley-showa.jpg",
      },
    ];
    setKoiFish(sampleData);
  }, []);

  useEffect(() => {
    const filtered = koiFish.filter(
      (koi) =>
        koi.name.toLowerCase().includes(searchTerm.toLowerCase()) &&
        (selectedVariety === "" || koi.variety === selectedVariety)
    );
    setFilteredKoiFish(filtered);
  }, [koiFish, searchTerm, selectedVariety]);

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
        <button onClick={() => navigate("/AddKoi")}>+</button>
      </div>

      <div className="koi-fish-dashboard">
        {filteredKoiFish.map((koi) => (
          <Card key={koi.id} koi={koi} />
        ))}
      </div>
    </div>
  );
}

export default ManagerKoi;
