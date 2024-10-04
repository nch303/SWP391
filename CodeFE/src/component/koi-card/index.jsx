import React from "react";
import { Link } from "react-router-dom";
function Card({ koi }) {
  return (
    <div key={koi.id} className="koi-fish">
      <div className="koi-fish-info">
        <h2 className="koi-fish-name">{koi.name}</h2>
        <img src={koi.image} alt={koi.name} className="koi-fish-image" />
        <p className="koi-fish-detail">Age: {koi.age}</p>
        <p className="koi-fish-detail">Variety: {koi.variety}</p>
        <p className="koi-fish-detail">Length: {koi.length} inches</p>
        <p className="koi-fish-detail">Weight: {koi.weight} pounds</p>
        <Link to={`/koi-info/${koi.id}`} className="see-more-button">
          See More Details
        </Link>
      </div>
    </div>
  );
}

export default Card;
