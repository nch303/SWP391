import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { Table } from "antd";
import api from "../../../config/axios";
import { PlusCircleOutlined } from "@ant-design/icons";

function ManagerPond() {
  const [ponds, setPonds] = useState([]);

  const fetchData = async () => {
    try {
      const response = await api.get("pond");
      console.log(response.data);
      setPonds(response.data);
    } catch (error) {
      console.error(error);
    }
  };
  useEffect(() => {
    fetchData();
  }, []);
  const navigate = useNavigate();

  const handleSearch = (searchTerm) => {
    const filteredPonds = ponds.filter((pond) =>
      pond.name.toLowerCase().includes(searchTerm.toLowerCase())
    );
    setPonds(filteredPonds);
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
      <Table
        columns={[
          {
            title: "Name",
            dataIndex: "name",
            key: "name",
          },
          {
            title: "Area",
            dataIndex: "area",
            key: "area",
          },
          {
            title: "Depth",
            dataIndex: "depth",
            key: "depth",
          },
          {
            title: "Volume",
            dataIndex: "volume",
            key: "volume",
          },
          {
            title: "Drain Count",
            dataIndex: "drainCount",
            key: "drainCount",
          },
          {
            title: "Skimmer Count",
            dataIndex: "skimmerCount",
            key: "skimmerCount",
          },
          {
            title: "Pumping Capacity",
            dataIndex: "pumpingCapacity",
            key: "pumpingCapacity",
          },
        ]}
        dataSource={ponds}
      />
    </div>
  );
}

export default ManagerPond;
