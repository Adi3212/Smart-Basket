import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import PrivateRoute from "./Components/PrivateRoute";

import Dashboard from "./pages/Dashboard";
import AddGroceryPage from "./pages/AddGroceryPage";
import EditGroceryPage from "./pages/EditGroceryPage";
import SignIn from "./pages/SignIn";
import SignUp from "./pages/SignUp";
import Navbar from "./Components/Navbar";

function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/sign-in" element={<SignIn />} />
        <Route path="/sign-up" element={<SignUp />} />

        <Route
          path="/dashboard"
          element={
            <PrivateRoute>
              <Dashboard />
            </PrivateRoute>
          }
        />
        <Route
          path="/add-grocery"
          element={
            <PrivateRoute>
              <AddGroceryPage />
            </PrivateRoute>
          }
        />
        <Route
          path="/edit/:id"
          element={
            <PrivateRoute>
              <EditGroceryPage />
            </PrivateRoute>
          }
        />
      </Routes>
    </Router>
  );
}

export default App;
