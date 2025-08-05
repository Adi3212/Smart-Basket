import React from 'react'
import Button from './Button'

function AddGrocery() {
  return (
    <div className="flex justify-between items-center mb-4">
  <h2 className="text-xl font-semibold">Your Groceries</h2>
    <Button title={"Add Grocery"} design={"bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded inline-flex items-center"}></Button>
</div>
  )
}

export default AddGrocery