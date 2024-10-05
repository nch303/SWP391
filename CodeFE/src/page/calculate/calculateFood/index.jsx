import React, { useState, useEffect } from 'react'
import { Select, InputNumber, Button } from 'antd'
import api from '../../../config/axios'


function CalculateFood() {
  const [ponds, setPonds] = useState([])
  const [selectedPond, setSelectedPond] = useState('')
  const [temparature, setTemparature] = useState(0)
  const [totalWeight, setTotalWeight] = useState(0)
  const [totalType, setTotalType] = useState('')

  useEffect(() => {
    const fetchPonds = async () => {
      try {
        const response = await api.get('pond')
        setPonds(response.data)
      } catch (error) {
        console.log(error)
      }
    }
    fetchPonds()
  }, [])

  const handleSelectPond = (value) => {
    setSelectedPond(value)
  }

  const handleTemparatureChange = (value) => {
    setTemparature(value)
  }

  const calculateFood = async () => {
    try {
      const response = await api.post('calculateFood', {
        pondId: selectedPond,
        temparature
      })
      setTotalWeight(response.data.totalWeight)
      setTotalType(response.data.totalType)
    } catch (error) {
      console.log(error)
    }
  }

  return (
    <div>
      <Select
        style={{ width: 200 }}
        placeholder="Select pond"
        onChange={handleSelectPond}
        value={selectedPond}
      >
        {ponds.map(pond => (
          <Select.Option key={pond.id} value={pond.id}>{pond.pondName}</Select.Option>
        ))}
      </Select>
      <InputNumber
        style={{ width: 200, margin: '0 10px' }}
        placeholder="Temparature"
        onChange={handleTemparatureChange}
        value={temparature}
      />
      <Button type="primary" onClick={calculateFood}>
        Calculate
      </Button>
      <p>Total weight: {totalWeight} kg</p>
      <p>Total type: {totalType}</p>

    </div>
  )
}

export default CalculateFood
