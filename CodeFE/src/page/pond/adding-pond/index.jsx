import React, { useState } from 'react'
import api from '../../../config/axios'
import { useNavigate } from 'react-router-dom'

function AddPond() {

  const [pondName, setPondName] = useState('')
  const [pondImage, setPondImage] = useState('')
  const [area, setArea] = useState(0)
  const [depth, setDepth] = useState(0)
  const [volume, setVolume] = useState(0)
  const [drainCount, setDrainCount] = useState(0)
  const [skimmerCount, setSkimmerCount] = useState(0)
  const [pumpingCapacity, setPumpingCapacity] = useState(0)

  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault()
    const pond = {
      pondName,
      pondImage,
      area,
      depth,
      volume,
      drainCount,
      skimmerCount,
      pumpingCapacity
    }
    try {
      const response = await api.post('pond', pond)
      console.log(response.data)
      alert('Pond added successfully')
      navigate('/pond')
    } catch (error) {
      console.log('pond adding failed')
    }
  }

  return (
    <form className="pond-form" onSubmit={handleSubmit}>
      <label>
        Pond Name:
        <input type="text" name="pondName" value={pondName} onChange={e => setPondName(e.target.value)} />
      </label>
      <br />
      <label>
        Pond Image:
        <input type="text" name="pondImage" value={pondImage} onChange={e => setPondImage(e.target.value)} />
      </label>
      <br />
      <label>
        Area:
        <input type="number" name="area" value={area} onChange={e => setArea(e.target.valueAsNumber)} />
      </label>
      <br />
      <label>
        Depth:
        <input type="number" name="depth" value={depth} onChange={e => setDepth(e.target.valueAsNumber)} />
      </label>
      <br />
      <label>
        Volume:
        <input type="number" name="volume" value={volume} onChange={e => setVolume(e.target.valueAsNumber)}/>
      </label>
      <br />
      <label>
        Drain Count:
        <input type="number" name="drainCount" value={drainCount} onChange={e => setDrainCount(e.target.valueAsNumber)} />
      </label>
      <br />
      <label>
        Skimmer Count:
        <input type="number" name="skimmerCount" value={skimmerCount} onChange={e => setSkimmerCount(e.target.valueAsNumber)} />
      </label>
      <br />
      <label>
        Pumping Capacity:
        <input type="number" name="pumpingCapacity" value={pumpingCapacity} onChange={e => setPumpingCapacity(e.target.valueAsNumber)} />
      </label>
      <br />
      <button type="submit">Add Pond</button>
    </form>  
    )
}

export default AddPond

