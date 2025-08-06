
import { Outlet } from 'react-router-dom';
import Navbar from '../Components/Navbar';

const Layout = () => {
  return (
    <>
     
      <main className="p-4">
        <Outlet />
      </main>
    </>
  );
};

export default Layout;
