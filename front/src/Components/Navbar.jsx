import UserDropdown from "./UseDropdown";

export default function Navbar() {
  return (
    <div className="bg-green-600 text-white px-6 py-4 shadow-md flex justify-between items-center">
      <h1 className="text-2xl font-bold">SmartBasket 🛒</h1>
      <div>
        <span className="text-sm">Welcome, </span>
        <UserDropdown></UserDropdown>
      </div>
    </div>
  );
}
