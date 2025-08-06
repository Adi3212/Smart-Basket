import * as DropdownMenu from '@radix-ui/react-dropdown-menu';
import { jwtDecode } from "jwt-decode";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export default function UserDropdown() {
  const [username, setUsername] = useState("User");
  const navigate = useNavigate();

  const updateUsernameFromToken = () => {
    const token = localStorage.getItem("token");
    if (token) {
      try {
        const decoded = jwtDecode(token);
        setUsername(decoded.sub || "User");
      } catch (err) {
        console.error("Invalid token:", err.message);
        setUsername("User");
      }
    } else {
      setUsername("User");
    }
  };

  useEffect(() => {
    updateUsernameFromToken();

    // ✅ Listen for both custom and storage events
    window.addEventListener("user-changed", updateUsernameFromToken);
    window.addEventListener("storage", updateUsernameFromToken);

    return () => {
      window.removeEventListener("user-changed", updateUsernameFromToken);
      window.removeEventListener("storage", updateUsernameFromToken);
    };
  }, []);

  const handleOpenChange = () => {
    updateUsernameFromToken();
  };

  const handleLogout = () => {
    localStorage.removeItem("token");
    setUsername("User");
    window.dispatchEvent(new Event("user-changed")); // ✅ dispatch logout event too
    navigate("/sign-in");
  };

  return (
    <DropdownMenu.Root onOpenChange={handleOpenChange}>
      <DropdownMenu.Trigger className="p-2 bg-green-600 rounded-full hover:bg-green-700 text-white focus:outline-none">
        {username}
      </DropdownMenu.Trigger>

      <DropdownMenu.Content
        className="bg-white border border-gray-200 rounded-md p-2 shadow-lg"
        sideOffset={5}
      >
        <DropdownMenu.Item className="px-4 py-2 text-sm text-green-700 hover:bg-green-100 rounded-md cursor-pointer">
          Profile
        </DropdownMenu.Item>
        <DropdownMenu.Item className="px-4 py-2 text-sm text-green-700 hover:bg-green-100 rounded-md cursor-pointer">
          Settings
        </DropdownMenu.Item>

        <DropdownMenu.Separator className="h-px bg-gray-200 my-1" />

        <DropdownMenu.Item
          onSelect={handleLogout}
          className="px-4 py-2 text-sm text-red-600 hover:bg-red-100 rounded-md cursor-pointer"
        >
          Logout
        </DropdownMenu.Item>
      </DropdownMenu.Content>
    </DropdownMenu.Root>
  );
}
