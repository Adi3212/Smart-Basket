import React, { useState } from 'react';
import axios from 'axios';

const SignUp = () => {
  const [form, setForm] = useState({
    name: '',
    email: '',
    password: '',
    phone_number: '',
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const requestBody = {
      ...form,
      roleIds: [1], //  1 = USER
    };

    try {
      const res = await axios.post('http://localhost:8080/users/signup', requestBody);
      alert('Signup successful! Now login.');
    } catch (err) {
      alert('Signup failed: ' + err.response?.data?.message || err.message);
    }
  };

  return (
    <div className="max-w-md mx-auto p-6 shadow rounded bg-green-60 mt-[5%]">
      <h2 className="text-xl mb-4 font-bold">Sign Up</h2>
      <form onSubmit={handleSubmit} className="space-y-4">
        <input name="name" placeholder="Name" onChange={handleChange} value={form.name} className="w-full p-2 border rounded" required />
        <input name="email" type="email" placeholder="Email" onChange={handleChange} value={form.email} className="w-full p-2 border rounded" required />
        <input name="password" type="password" placeholder="Password" onChange={handleChange} value={form.password} className="w-full p-2 border rounded" required />
        <input name="phone_number" placeholder="Phone Number" onChange={handleChange} value={form.phone_number} className="w-full p-2 border rounded" required />
        <button type="submit" className="bg-green-600 text-white py-2 px-4 rounded">Sign Up</button>
      </form>
    </div>
  );
};

export default SignUp;
