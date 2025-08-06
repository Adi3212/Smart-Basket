import React, { useEffect, useState } from 'react';

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

  const units = ['KG', 'LITRE', 'PCS', 'GRAM'];

  // Set userId from localStorage and populate initial data if editing
  useEffect(() => {
    const user = JSON.parse(localStorage.getItem('user'));
    const userId = user?.id || '';

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

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(formData);
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
          {units.map((u) => (
            <option key={u} value={u}>{u}</option>
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
