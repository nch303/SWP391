import React from "react";
import { useParams, useNavigate } from "react-router-dom";
import { useQueryClient } from "react-query";
import "./index.scss"
import api from "../../../config/axios"; // Commenting out real API calls

function PondInfo() {
  const { id } = useParams();
  const navigate = useNavigate();
  const queryClient = useQueryClient();

/*  // Simulated data for pond and water report
  const simulatedPond = {
    pondName: "Lotus Pond",
    area: 500,
    depth: 2.5,
    volume: 1250,
    drainCount: 3,
    skimmerCount: 2,
    amountFish: 40,
    pumpingCapacity: 15,
  };

  const simulatedWaterReport = {
    waterReportId: 101,
    waterReportUpdatedDate: "2024-10-05",
    waterReportTemperature: 26,
    waterReportOxygen: 8.5,
    waterReport_pH: 7.2,
    waterReportHardness: 10,
    waterReportAmmonia: 0.01,
    waterReportNitrite: 0.05,
    waterReportNitrate: 10,
    waterReportCarbonate: 100,
    waterReportSalt: 0.25,
    waterReportCarbonDioxide: 1.2,
    pondID: id,
  };*/

  // Simulating API response (commented real API call)
  const {
    data: pond,
    error: pondError,
    isLoading: pondIsLoading,
  } = useQuery(["pond", id], () => api.get(`pond?id=${id}`), {
    enabled: !!id,
    staleTime: Infinity,
  });

  const {
    data: waterReport,
    error: waterReportError,
    isLoading: waterReportIsLoading,
  } = useQuery(
    ["waterReport", id],
    () => api.get(`waterReport?id=${id}`),
    {
      enabled: !!id,
      staleTime: Infinity,
     }
   );

//  const pond = simulatedPond; // Use simulated data
//  const waterReport = simulatedWaterReport; // Use simulated data

  const handleDelete = async () => {
     await api.delete(`pond/${id}`); // Commented real API call
    queryClient.invalidateQueries("ponds");
    navigate("/managerPond");
  };

  return (
    <div className="pond-water-container">
      <h1 className="pond-title">{pond.pondName}</h1>
      <div className="pond-info">
        <h2>Pond Info</h2>
        <p>Name: {pond.pondName}</p>
        <p>Area: {pond.area} m²</p>
        <p>Depth: {pond.depth} m</p>
        <p>Volume: {pond.volume} m³</p>
        <p>Drain Count: {pond.drainCount}</p>
        <p>Skimmer Count: {pond.skimmerCount}</p>
        <p>Amount of Fish: {pond.amountFish}</p>
        <p>Pumping Capacity: {pond.pumpingCapacity} m³/h</p>
      </div>
      <div className="water-report">
        <h2>Water Report</h2>
        <p>Water Report ID: {waterReport.waterReportId}</p>
        <p>Updated: {waterReport.waterReportUpdatedDate}</p>
        <p>Temperature: {waterReport.waterReportTemperature} °C</p>
        <p>Oxygen: {waterReport.waterReportOxygen} ppm</p>
        <p>pH: {waterReport.waterReport_pH}</p>
        <p>Hardness: {waterReport.waterReportHardness} dGH</p>
        <p>Ammonia: {waterReport.waterReportAmmonia} ppm</p>
        <p>Nitrite: {waterReport.waterReportNitrite} ppm</p>
        <p>Nitrate: {waterReport.waterReportNitrate} ppm</p>
        <p>Carbonate: {waterReport.waterReportCarbonate} ppm</p>
        <p>Salt: {waterReport.waterReportSalt} ppm</p>
        <p>Carbon Dioxide: {waterReport.waterReportCarbonDioxide} ppm</p>
        <p>Pond ID: {waterReport.pondID}</p>
      </div>
      <button className="delete-button" onClick={handleDelete}>
        Delete
      </button>
    </div>
  );
}

export default PondInfo;
