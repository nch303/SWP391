import React, { useState } from 'react'
import { Form, Input, Button } from 'antd'

function AddKoi() {
  const [name, setName] = useState('')
  const [age, setAge] = useState(0)
  const [variety, setVariety] = useState('')
  const [length, setLength] = useState(0)
  const [weight, setWeight] = useState(0)
  const [image, setImage] = useState('')

  const handleSubmit = async () => {
    
        
  }

  return (
    <div>
      <h1>Add Koi</h1>
      <Form layout="vertical" onFinish={handleSubmit}>
        <Form.Item label="Name" name="koiName">
          <Input />
        </Form.Item>
        <Form.Item label="Birthday" name="birthday">
          <Input type="date"/>
        </Form.Item>
        <Form.Item label="Sex" name="koiSex">
          <Input />
        </Form.Item>
        <Form.Item label="Image" name="image">
          <Input />
        </Form.Item>
        <Form.Item label="Pond ID" name="pondID">
          <Input type="number"/>
        </Form.Item>
        <Form.Item label="Variety ID" name="koiVarietyID">
          <Input type="number"/>
        </Form.Item>
        <Form.Item>
          <Button type="primary" onClick={handleSubmit}>
            Submit
          </Button>
        </Form.Item>
      </Form>
    </div>
  )
}

export default AddKoi
