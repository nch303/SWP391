import React from 'react';
import { Card } from 'antd';
import './index.scss';

interface Fish {
  id: number;
  name: string;
  type: string;
  age: number;
  weight: number;
  length: number;
  color: string;
  sex: string;
  image: string;
}

const sampleFish: Fish[] = [
  {
    id: 1,
    name: 'Koi 1',
    type: 'Soragoi',
    age: 2,
    weight: 5,
    length: 10,
    color: 'white',
    sex: 'male',
    image: 'https://picsum.photos/200',
  },
  {
    id: 2,
    name: 'Koi 2',
    type: 'Kohaku',
    age: 3,
    weight: 7,
    length: 12,
    color: 'red',
    sex: 'female',
    image: 'https://picsum.photos/200',
  },
  {
    id: 3,
    name: 'Koi 3',
    type: 'Ogon',
    age: 4,
    weight: 10,
    length: 15,
    color: 'gold',
    sex: 'male',
    image: 'https://picsum.photos/200',
  },
  {
    id: 4,
    name: 'Koi 4',
    type: 'Bekko',
    age: 5,
    weight: 12,
    length: 18,
    color: 'black',
    sex: 'female',
    image: 'https://picsum.photos/200',
  },
  {
    id: 5,
    name: 'Koi 5',
    type: 'Butterfly',
    age: 6,
    weight: 15,
    length: 20,
    color: 'tricolor',
    sex: 'male',
    image: 'https://picsum.photos/200',
  },
  {
    id: 6,
    name: 'Koi 6',
    type: 'Ryukin',
    age: 7,
    weight: 18,
    length: 22,
    color: 'white',
    sex: 'female',
    image: 'https://picsum.photos/200',
  },
]

function ManagerKoi() {
    return (
        <div> 
            <h1 style={{ textAlign: 'center' }}>Manager Koi</h1>
        <div className="fish-list">
        
          {sampleFish.map((fish) => (
            <div key={fish.id} className="fish-item">
              <img src={fish.image} alt={fish.name} className="fish-image" />
              <div className="fish-info">
                <h2 className="fish-name">{fish.name}</h2>
                <p className="fish-type">{fish.type}</p>
                <p>Age: {fish.age}</p>
                <p>Weight: {fish.weight} kg</p>
                <p>Length: {fish.length} cm</p>
                <p>Color: {fish.color}</p>
                <p>Sex: {fish.sex}</p>
              </div>
            </div>
          ))}
        </div>
        </div>
      );
};

export default ManagerKoi;


