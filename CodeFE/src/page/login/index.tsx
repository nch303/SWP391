
import React from 'react';
import './index.scss';
import { FaUser } from "react-icons/fa";
import { FaClock } from "react-icons/fa";
import { Button, Form, Input } from 'antd';
import api from '../../config/axios';
import { useNavigate } from 'react-router-dom';

function Login() {
    const navigate = useNavigate();
    const handleSubmit = async (values) =>{
        try{
const reponse = await api.post("login",values);
console.log(reponse.data)
alert("dang nhap thanh cong")
navigate("/");
    const {token} = reponse.data;
localStorage.setItem("token",token);
        }catch(err){
            alert("Dang nhap that bai")
        }
    }
    return (
        <div className='login'>
            <div className="login-form-container"> 
                <div className="loginForm-title">Login</div>
                <Form onFinish={handleSubmit}>
                    <Form.Item label="Username" name="username" >
                        <Input/>
                    </Form.Item>
                    <div className='loginForm-remember'>
                        <input type="checkbox" name="remember" /> Remember Me
                    </div>
                    <Form.Item label="Password" name="password">
                        <Input/>
                    </Form.Item>
                    <div className='loginForm-forgot'>
                        <p>Forgot your password? Click <a href="/forgot-password">here</a></p>
                    </div>
                    <Form.Item >
                        <Button type="primary" htmlType="submit">Submit</Button>
                    </Form.Item>
                </Form>
                <div className="loginForm-register">
                    <p>Don't have an account? <a href="/Register">register</a> </p>
                    
                </div>

                
            </div>
        </div>
    );
}

export default Login;
