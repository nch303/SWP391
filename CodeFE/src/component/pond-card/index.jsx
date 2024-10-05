import React from 'react'
import { Link } from 'react-router-dom';

function PondCard({ pond }) {
  const { pondID, pondName, pondImage, area, depth, volume, drainCount, skimmerCount, pumpingCapacity, amountFish } = pond;
  return (
    <div className="card-pond">
      <p>Pond Name: {pondName}</p>
      <img src={pondImage} alt="pond" className="pond-image" />
      <p>Area: {area} m2</p>
      <p>Depth: {depth} m</p>
      <p>Volume: {volume} m3</p>
      <p>Drain Count: {drainCount}</p>
      <p>Skimmer Count: {skimmerCount}</p>
      <p>Pumping Capacity: {pumpingCapacity} m3/h</p>
      <p>Amount of Fish: {amountFish}</p>
      <Link to={`/pond-info/${pondID}`}>Detail</Link>
    </div>
  )
}

export default PondCard;