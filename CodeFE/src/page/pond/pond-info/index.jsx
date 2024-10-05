import React from "react";
import { useParams, useNavigate } from "react-router-dom";
import { useQuery, useQueryClient } from "react-query";
import api from "../../../config/axios";

function PondInfo() {
  const { id } = useParams();
  const navigate = useNavigate();
  const queryClient = useQueryClient();

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
    "  () => api.get(`waterReport?id=${id}`)",
    {
      enabled: !!id,
      staleTime: Infinity,
    }
  );

  if (pondError) return <div>Error: {pondError.message}</div>;
  if (waterReportError) return <div>Error: {waterReportError.message}</div>;
  if (pondIsLoading || waterReportIsLoading) return <div>Loading...</div>;

  const handleDelete = async () => {
    await api.delete(`pond/${id}`);
    queryClient.invalidateQueries("ponds");
    navigate("/managerPond");
  };

  return (
    <div>
      <h1>PondInfo: {pond.pondName}</h1>
      <div>
        <p>Name: {pond.pondName}</p>
        <p>Area: {pond.area} m2</p>
        <p>Depth: {pond.depth} m</p>
        <p>Volume: {pond.volume} m3</p>
        <p>Drain Count: {pond.drainCount}</p>
        <p>Skimmer Count: {pond.skimmerCount}</p>
        <p>Amount Fish: {pond.amountFish}</p>
        <p>Pumping Capacity: {pond.pumpingCapacity} m3/h</p>
      </div>
      <div>
        <p>Water Report:</p>
        <p>Water Report ID: {waterReport.waterReportId}</p>
        <p>Water Report Updated Date: {waterReport.waterReportUpdatedDate}</p>
        <p>Water Report Temperature: {waterReport.waterReportTemperature} C</p>
        <p>Water Report Oxygen: {waterReport.waterReportOxygen} ppm</p>
        <p>Water Report PH: {waterReport.waterReport_pH}</p>
        <p>Water Report Hardness: {waterReport.waterReportHardness} dGH</p>
        <p>Water Report Ammonia: {waterReport.waterReportAmmonia} ppm</p>
        <p>Water Report Nitrite: {waterReport.waterReportNitrite} ppm</p>
        <p>Water Report Nitrate: {waterReport.waterReportNitrate} ppm</p>
        <p>Water Report Carbonate: {waterReport.waterReportCarbonate} ppm</p>
        <p>Water Report Salt: {waterReport.waterReportSalt} ppm</p>
        <p>
          Water Report Carbon Dioxide: {waterReport.waterReportCarbonDioxide}{" "}
          ppm
        </p>
        <p>Pond ID: {waterReport.pondID}</p>
      </div>
      <button onClick={handleDelete}>Delete</button>
    </div>
  );
}

export default PondInfo;
