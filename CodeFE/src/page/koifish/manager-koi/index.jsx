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
    const fetchKoiFish = async () => {
      try {
        const response = await api.get("koifish");
        setKoiFish(response.data);
        console.log(response.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchKoiFish();
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
          <KoiCard key={koi.id} koi={koi} />
        ))}
      </div>
    </div>
  );
}

export default ManagerKoi;
