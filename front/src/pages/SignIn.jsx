import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from "react-router-dom";
const SignIn = () => {
    const navigate = useNavigate();
  const [form, setForm] = useState({
    email: '',
    password: ''
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const res = await axios.post('http://localhost:8080/users/signin', form);
      const { token, user } = res.data;
        console.log(token);
        
      localStorage.setItem('token', token);
      window.dispatchEvent(new Event("user-changed"));
      localStorage.setItem('user', JSON.stringify(user));

      alert('Login Successful');
      // redirect to dashboard or home
      navigate("/dashboard")

    } catch (err) {
      alert('Login failed: ' + err.response?.data?.message || err.message);
    }
  };

  return (
    <div className="max-w-md mx-auto p-6 shadow rounded mt-[5%]">
      <h2 className="text-xl mb-4 font-bold">Sign In</h2>
      <form onSubmit={handleLogin} className="space-y-4">
        <input name="email" type="email" placeholder="Email" onChange={handleChange} value={form.email} className="w-full p-2 border rounded" required />
        <input name="password" type="password" placeholder="Password" onChange={handleChange} value={form.password} className="w-full p-2 border rounded" required />
        <button type="submit" className="bg-green-600 text-white py-2 px-4 rounded">Sign In</button>
      </form>
    </div>
  );
};

export default SignIn;
