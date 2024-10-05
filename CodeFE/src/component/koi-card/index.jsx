import React from "react";
import { useNavigate } from "react-router-dom";
function KoiCard({ koi: { koiFishID, koiName, birthday, koiSex, image, pondID, koiVarietyID } }) {
  const navigate = useNavigate();
  return (
    <div key={koiFishID} className="koi-fish">
      <div className="koi-fish-info">
        <h1 className="koi-fish-title">Koi Fish</h1>
        <h2 className="koi-fish-name">{koiName}</h2>
        <img src={image} alt={koiName} className="koi-fish-image" />
        <hr />
        <p className="koi-fish-birthday">Birthday: {birthday}</p>
        <p className="koi-fish-sex">Sex: {koiSex}</p>
        <p className="koi-fish-pond">Pond: {pondID}</p>
        <p className="koi-fish-variety">Variety: {koiVarietyID}</p>

        <button className="see-more-button" onClick={() => navigate(`/koi-info/${koiFishID}`)}>
          See More Details
        </button>
      </div>
    </div>
  );
}

export default KoiCard;

