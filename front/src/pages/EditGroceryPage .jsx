import { useLocation, useNavigate } from "react-router-dom";
import GroceryForm from "../Components/GroceryForm";

const EditGroceryPage = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const grocery = location.state?.grocery;

  const handleUpdate = (updatedData) => {
    console.log("Sending updated data to backend:", updatedData);
    // axios.put(`/api/groceries/${grocery.id}`, updatedData)
    //   .then(() => navigate("/"));
    navigate("/"); // Redirect after success
  };

  if (!grocery) return <p className="p-4">No grocery data provided.</p>;

  return (
    <div className="max-w-xl mx-auto mt-8">
      <h2 className="text-xl font-semibold mb-4">Edit Grocery</h2>
      <GroceryForm initialData={grocery} onSubmit={handleUpdate} />
    </div>
  );
};

export default EditGroceryPage;
