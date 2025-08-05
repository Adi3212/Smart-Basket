// pages/AddGrocery.jsx
import React from 'react';
import GroceryForm from '../Components/GroceryForm';
import useAddGrocery from '../hooks/useAddGrocery';

const AddGroceryPage = () => {
  const { addGrocery } = useAddGrocery();

  const handleFormSubmit = async (data) => {
    try {
      await addGrocery(data);
      alert('Grocery added successfully!');
    } catch (err) {
      alert('Error adding grocery');
    }
  };

  return (
    <div className="max-w-xl mx-auto mt-10">
      <h2 className="text-2xl font-semibold mb-4 text-center">Add New Grocery</h2>
      <GroceryForm onSubmit={handleFormSubmit} />
    </div>
  );
};

export default AddGroceryPage;
