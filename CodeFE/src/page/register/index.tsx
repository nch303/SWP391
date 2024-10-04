import { Button, Form, Input, } from 'antd'
import React from 'react'
import './index.scss'

function Register() {
  return (
    <div className='register'>
      <div className='register-form-container'>
        <div className='register-title'>Register</div>
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
        <div className="registerForm-login">
                    <p>Already have an account? Click <a href="/Login">here</a> </p>            
        </div>
      </div>
    </div>
  )
}

export default Register;