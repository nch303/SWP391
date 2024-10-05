import React from "react";
import { Link } from "react-router-dom";
function KoiCard({ koi }) {
  const { koiFishID, koiName, birthday, koiSex, image, pondID, koiVarietyID } =
    koi;
  return (
    <div key={id} className="koi-fish">
      <div className="koi-fish-info">
        <h2 className="koi-fish-name">{name}</h2>
        <img src={image} alt={name} className="koi-fish-image" />
        <hr />

        <div>vvvvvvvvvvvvvv</div>
        <Link to={`/koi-info/${koiFishID}`} className="see-more-button">
          See More Details
        </Link>
      </div>
    </div>
  );
}

export default KoiCard;
