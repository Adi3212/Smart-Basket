import Navbar from "../Components/Navbar";
import GroceryTable from "../Components/GroceryTable";
import { useGrocery } from "../hooks/sample";
import AddGrocery from "../Components/AddGrocery";

const Dashboard = () => {
  const groceries = useGrocery();

  return (
    <div className="min-h-screen bg-green-50">
      
      <main className="container mx-auto px-4 py-6">
        <AddGrocery></AddGrocery>
        <GroceryTable groceries={groceries} />
      </main>
    </div>
  );
};

export default Dashboard;
