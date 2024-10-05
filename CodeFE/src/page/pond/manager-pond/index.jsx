import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import api from "../../../config/axios";
import { PlusCircleOutlined } from "@ant-design/icons";
import PondCard from "../../../component/pond-card";

function ManagerPond() {
  const [ponds, setPonds] = useState([
    // {
    //   pondID: 1,
    //   pondName: "Goldie",
    //   pondImage: "pond-goldie.jpg",
    //   area: 10,
    //   depth: 3,
    //   volume: 30,
    //   drainCount: 2,
    //   skimmerCount: 3,
    //   amountFish: 1,
    //   pumpingCapacity: 5,
    // },
    // {
    //   pondID: 2,
    //   pondName: "Scales",
    //   pondImage: "pond-scales.jpg",
    //   area: 20,
    //   depth: 4,
    //   volume: 60,
    //   drainCount: 3,
    //   skimmerCount: 4,
    //   amountFish: 2,
    //   pumpingCapacity: 7,
    // },
    // {
    //   pondID: 3,
    //   pondName: "Finley",
    //   pondImage: "pond-finley.jpg",
    //   area: 30,
    //   depth: 5,
    //   volume: 90,
    //   drainCount: 4,
    //   skimmerCount: 5,
    //   amountFish: 3,
    //   pumpingCapacity: 10,
    // },
  ]);
  const [searchTerm, setSearchTerm] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const fetchPonds = async () => {
      try {
        const response = await api.get("pond");
        setPonds(response.data);
        console.log(response.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchPonds();
  }, []);

  const filteredPonds = ponds.filter((pond) =>
    pond.pondName.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const handleSearch = (value) => {
    setSearchTerm(value);
  };

  return (
    <div className="ManagerPond-container">
      <h1 style={{ textAlign: "center" }}>Manager Pond</h1>
      <div className="filter-search">
        <input
          type="text"
          onChange={(e) => handleSearch(e.target.value)}
          placeholder="Search by name..."
          className="search-input"
        />
        <PlusCircleOutlined
          style={{ fontSize: "24px" }}
          onClick={() => navigate("/AddPond")}
        />
      </div>

      {filteredPonds.length === 0 ? (
        <p style={{ textAlign: "center" }}>You have no pond, Please add one</p>
      ) : (
        <div className="pond-dashboard">
          {filteredPonds.map((pond) => (
            <PondCard key={pond.id} pond={pond} />
          ))}
        </div>
      )}
    </div>
  );
}
export default ManagerPond;
