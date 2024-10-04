import { Button, Form, Input, } from 'antd'
import React from 'react'

function Register() {
  return (
    <div className='register'>
      <div className='regis-form-container'>
        <div className='regis-title'>Register</div>
        <Form>
          <Form.Item label= "Email" name= "email">
            <Input/>
          </Form.Item>
          <Form.Item label= "Username" name="username">
            <Input/>
          </Form.Item>
          <Form.Item label= "Password" name="password">
            <Input/>
          </Form.Item>
          <Form.Item >
            <Button type="primary" htmlType="submit">Submit</Button>
          </Form.Item>
        </Form>
        <div className="loginForm-register">
                    <p>Already have an account? Click <a href="/Login">here</a> </p>            
        </div>
      </div>
    </div>
  )
}

export default Register;