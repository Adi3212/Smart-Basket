// components/GroceryForm.jsx
import React, { useEffect, useState } from 'react';

const GroceryForm = ({ onSubmit }) => {
  const [formData, setFormData] = useState({
    name: '',
    quantity: 1,
    unit: 'KG',
    purchaseDate: '',
    expiryDate: '',
    categoryId: '',
    userId: '', // will be set automatically
  });

  const units = ['KG', 'LITRE', 'PCS', 'GRAM']; // Your ENUMs

  // 👉 Fetch userId from localStorage/session on mount
  useEffect(() => {
    const user = JSON.parse(localStorage.getItem('user'));
    if (user && user.id) {
      setFormData((prev) => ({ ...prev, userId: user.id }));
    }
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(formData);
  };

  return (
    <form onSubmit={handleSubmit} className="space-y-4 p-6 bg-white rounded shadow max-w-xl mx-auto">
      <input
        type="text"
        name="name"
        placeholder="Grocery Name"
        value={formData.name}
        onChange={handleChange}
        required
        className="w-full border p-2"
      />

      <input
        type="number"
        name="quantity"
        placeholder="Quantity"
        value={formData.quantity}
        onChange={handleChange}
        required
        className="w-full border p-2"
      />

      <select name="unit" value={formData.unit} onChange={handleChange} className="w-full border p-2">
        {units.map((u) => (
          <option key={u} value={u}>{u}</option>
        ))}
      </select>

      <div>
        <label className="block mb-1 font-medium">Purchase Date</label>
        <input
          type="date"
          name="purchaseDate"
          value={formData.purchaseDate}
          onChange={handleChange}
          className="w-full border p-2"
        />
      </div>

      <div>
        <label className="block mb-1 font-medium">Expiry Date</label>
        <input
          type="date"
          name="expiryDate"
          value={formData.expiryDate}
          onChange={handleChange}
          className="w-full border p-2"
        />
      </div>

      <input
        type="text"
        name="categoryId"
        placeholder="Category ID"
        value={formData.categoryId}
        onChange={handleChange}
        className="w-full border p-2"
      />

      {/* 👇 Hidden userId input (just for debug, or remove it) */}
      <input
        type="hidden"
        name="userId"
        value={formData.userId}
      />

      <button type="submit" className="bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded">
        Add Grocery
      </button>
    </form>
  );
};

export default GroceryForm;
