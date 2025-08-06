import Navbar from "../Components/Navbar";
import GroceryTable from "../Components/GroceryTable";
import { useGrocery } from "../hooks/sample";
import AddGrocery from "../Components/AddGrocery";
import { useNavigate } from "react-router-dom";

const Dashboard = () => {
  const groceries = useGrocery();
  const navigate = useNavigate();
 const handleEdit = (item) => {
    navigate(`/edit/${item.id}`, { state: { grocery: item } });
  };
  return (
    <div className="min-h-screen bg-green-50">
      
      <main className="container mx-auto px-4 py-6">
        <AddGrocery></AddGrocery>
        <GroceryTable groceries={groceries}  onEdit={handleEdit}/>
      </main>
    </div>
  );
};

export default Dashboard;
