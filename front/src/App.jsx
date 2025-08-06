import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './Components/Navbar';
import Dashboard from './pages/Dashboard';
import AddGroceryPage from './pages/AddGroceryPage';
import EditGroceryPage from './pages/EditGroceryPage ';
import SignIn from './pages/SignIn ';
import SignUp from './pages/SignUp ';


function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<Dashboard />} />
        <Route path="/add-grocery" element={<AddGroceryPage></AddGroceryPage>} />
        <Route path="/edit/:id" element={<EditGroceryPage />} />
        <Route path="/sign-in" element={<SignIn></SignIn>} />
        <Route path="/sign-up" element={<SignUp />} />
        {/* <Route path="/login" element={<Login />} /> */}
        {/* <Route path="*" element={<NotFound />} /> */}
      </Routes>
    </Router>
  );
}

export default App;
