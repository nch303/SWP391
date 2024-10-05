import React, { useEffect, useState } from "react";
import "./index.scss";
import api from "../../../config/axios";
import { useNavigate } from "react-router-dom";
import KoiCard from "../../../component/koi-card";

function ManagerKoi() {
  const navigate = useNavigate();
  const [koiFish, setKoiFish] = useState([]);
  const [filteredKoiFish, setFilteredKoiFish] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [selectedVariety, setSelectedVariety] = useState("");

  useEffect(() => {
    api
      .get("koifish")
      .then((response) => {
        setKoiFish(response.data);
        setFilteredKoiFish(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  useEffect(() => {
    const filtered = koiFish.filter((koi) =>
      koi.koiName.toLowerCase().includes(searchTerm.toLowerCase())
    );
    if (selectedVariety) {
      setFilteredKoiFish(
        filtered.filter((koi) => koi.koiVarietyID === selectedVariety)
      );
    } else {
      setFilteredKoiFish(filtered);
    }
  }, [searchTerm, selectedVariety, koiFish]);

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
        <button onClick={() => navigate("/addKoi")}>+</button>
      </div>

      {filteredKoiFish.length === 0 ? (
        <p style={{ textAlign: "center" }}>
          You have no koi fish, Please add one
        </p>
      ) : (
        <div className="koi-fish-dashboard">  
          {filteredKoiFish.map((koi) => (
            <KoiCard key={koi.koiFishID} koi={koi} />
          ))}
        </div>
      )}
    </div>
  );
}

export default ManagerKoi;
