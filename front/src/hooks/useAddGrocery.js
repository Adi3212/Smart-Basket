
import axios from 'axios';

const useAddGrocery = () => {
  const addGrocery = async (groceryData) => {
    const response = await axios.post("http://localhost:8080/grocery", groceryData);
    return response.data;
  };

  return { addGrocery };
};

export default useAddGrocery;
