import { Link } from "react-router-dom";

const Sidebar = () => {
  return (
    <div className="w-60 bg-gray-800 text-white min-h-screen p-5 space-y-6">
      <Link to="/" className="block hover:bg-gray-700 p-2 rounded">Dashboard</Link>
      <Link to="/inventory" className="block hover:bg-gray-700 p-2 rounded">Inventory</Link>
      <Link to="/add" className="block hover:bg-gray-700 p-2 rounded">Add Item</Link>
      <Link to="/expiry" className="block hover:bg-gray-700 p-2 rounded">Expiry Soon</Link>
      <Link to="/notifications" className="block hover:bg-gray-700 p-2 rounded">Notifications</Link>
    </div>
  );
};

export default Sidebar;
