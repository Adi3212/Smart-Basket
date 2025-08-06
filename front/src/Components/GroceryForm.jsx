import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { jwtDecode } from 'jwt-decode';
const GroceryForm = ({ onSubmit, initialData = null }) => {
  const [formData, setFormData] = useState({
    name: '',
    quantity: 1,
    unit: 'KG',
    purchaseDate: '',
    expiryDate: '',
    categoryId: '',
    userId: '',
  });

const units = [
  { label: 'Kg', value: 'KILOGRAMS' },
  { label: 'Grams', value: 'GRAMS' },
  { label: 'Liters', value: 'LITERS' },
  { label: 'ml', value: 'ML' },
  { label: 'Bottle', value: 'BOTTLE' },
  { label: 'Pieces', value: 'PIECES' },
  { label: 'Packet', value: 'PACKET' },
  { label: 'Dozen', value: 'DOZEN' }
];

  // Set userId from localStorage and populate initial data if editing


useEffect(() => {
  const token = localStorage.getItem('token');
  let userId = '';

  if (token) {
    try {
      const decoded = jwtDecode(token);
      userId = decoded.userId || '';
    } catch (err) {
      console.error('Invalid JWT token:', err);
    }
  }

  if (initialData) {
    setFormData({
      name: initialData.name || '',
      quantity: initialData.quantity || 1,
      unit: initialData.unit || 'KG',
      purchaseDate: initialData.purchaseDate?.slice(0, 10) || '',
      expiryDate: initialData.expiryDate?.slice(0, 10) || '',
      categoryId: initialData.category?.id || '',
      userId: userId,
    });
  } else {
    setFormData((prev) => ({ ...prev, userId }));
  }
}, [initialData]);


  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

 


const handleSubmit = async (e) => {
  e.preventDefault();

  try {
    const token = localStorage.getItem("token");

    const response = await axios.post("http://localhost:8080/grocery/grocery", formData, {
      headers: {
        Authorization: `Bearer ${token}`, // include token for auth
        "Content-Type": "application/json"
      }
    });

    console.log("Grocery added:", response.data);
    alert("Grocery added successfully!");

    // Optional: reset form
    setFormData({
      name: '',
      quantity: 1,
      unit: '',
      purchaseDate: '',
      expiryDate: '',
      categoryId: '',
      userId: formData.userId, // keep userId
    });

  } catch (err) {
    console.error("Error adding grocery:", err);
    alert("Failed to add grocery. Please try again.");
  }
};


  return (
    <form onSubmit={handleSubmit} className="space-y-4 p-6 bg-white rounded shadow max-w-xl mx-auto">
      <div>
        <label className="block mb-1 font-medium">Grocery Name</label>
        <input
          type="text"
          name="name"
          value={formData.name}
          onChange={handleChange}
          required
          className="w-full border p-2"
        />
      </div>

      <div>
        <label className="block mb-1 font-medium">Quantity</label>
        <input
          type="number"
          name="quantity"
          value={formData.quantity}
          onChange={handleChange}
          required
          className="w-full border p-2"
        />
      </div>

      <div>
        <label className="block mb-1 font-medium">Unit</label>
        <select
          name="unit"
          value={formData.unit}
          onChange={handleChange}
          className="w-full border p-2"
        >
          {units.map((unit) => (
    <option key={unit.value} value={unit.value}>
      {unit.label}
    </option>
  ))}
        </select>
      </div>

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

      <div>
        <label className="block mb-1 font-medium">Category ID</label>
        <input
          type="text"
          name="categoryId"
          value={formData.categoryId}
          onChange={handleChange}
          className="w-full border p-2"
        />
      </div>

      {/* hidden userId field (optional for debug) */}
      <input type="hidden" name="userId" value={formData.userId} />

      <button
        type="submit"
        className="bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded"
      >
        {initialData ? 'Update Grocery' : 'Add Grocery'}
      </button>
    </form>
  );
};

export default GroceryForm;
