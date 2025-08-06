import { jwtDecode } from "jwt-decode";
import axios from "axios";
import { useEffect, useState } from "react";
import GroceryTable from "../Components/GroceryTable";
import AddGrocery from "../Components/AddGrocery";
import { useNavigate } from "react-router-dom";

const Dashboard = () => {
  const [groceries, setGroceries] = useState([]);
  const navigate = useNavigate();

  // Get token from localStorage and decode userId
  const token = localStorage.getItem("token"); // make sure it's stored on login
  console.log(token);
  
  const decoded = jwtDecode(token);
  console.log(decoded);
  
  const userId = decoded.userId;
  console.log(userId);
  

  useEffect(() => {
    if (!token) return;

    axios
      .get(`http://localhost:8080/grocery/user/${userId}`, {
        headers: {
          Authorization: `Bearer ${token}`, // Optional if backend uses spring security auth
        },
      })
      .then((res) => {
        setGroceries(res.data);
      })
      .catch((err) => {
        console.error("Error fetching groceries:", err);
      });
  }, [userId]);

  const handleEdit = (item) => {
    navigate(`/edit/${item.id}`, { state: { grocery: item } });
  };

  return (
    <div className="min-h-screen bg-green-50">
      <main className="container mx-auto px-4 py-6">
        <AddGrocery />
        <GroceryTable groceries={groceries} onEdit={handleEdit} />
      </main>
    </div>
  );
};

export default Dashboard;
