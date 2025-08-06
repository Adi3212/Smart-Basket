import React from 'react'
import Button from './Button'
import { useNavigate } from "react-router-dom";
function AddGrocery() {
  const navigate = useNavigate();
  return (
    <div className="flex justify-between items-center mb-4">
  <h2 className="text-xl font-semibold">Your Groceries</h2>
    <Button title={"Add Grocery"} design={"bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded inline-flex items-center"}    
    onclick={()=>navigate("/add-grocery")}
    ></Button>
</div>
  )
}

export default AddGrocery