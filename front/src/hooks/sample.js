export const sampleGroceryItems = [
  {
    id: 1,
    name: "Wheat Flour",
    quantity: 5,
    unit: "KG",
    purchaseDate: "2025-07-01",
    expiryDate: "2025-10-01",
    category: {
      id: 101,
      name: "Grains",
    },
    user: {
      id: 201,
      name: "Aditya Andhale",
      email: "aditya@example.com",
    },
  },
  {
    id: 2,
    name: "Milk",
    quantity: 2,
    unit: "LITRE",
    purchaseDate: "2025-08-01",
    expiryDate: "2025-08-06",
    category: {
      id: 102,
      name: "Dairy",
    },
    user: {
      id: 201,
      name: "Aditya Andhale",
      email: "aditya@example.com",
    },
  },
  {
    id: 3,
    name: "Apples",
    quantity: 6,
    unit: "PIECES",
    purchaseDate: "2025-08-03",
    expiryDate: "2025-08-10",
    category: {
      id: 103,
      name: "Fruits",
    },
    user: {
      id: 201,
      name: "Aditya Andhale",
      email: "aditya@example.com",
    },
  },
  {
    id: 4,
    name: "Toor Dal",
    quantity: 1,
    unit: "KG",
    purchaseDate: "2025-06-15",
    expiryDate: "2026-01-15",
    category: {
      id: 104,
      name: "Pulses",
    },
    user: {
      id: 201,
      name: "Aditya Andhale",
      email: "aditya@example.com",
    },
  },
];

export function useGrocery(){
    return sampleGroceryItems;
}
