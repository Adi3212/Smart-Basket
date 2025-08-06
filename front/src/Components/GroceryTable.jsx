import * as DropdownMenu from '@radix-ui/react-dropdown-menu';

import { useGrocery } from "../hooks/sample";
import { useNavigate } from "react-router-dom";


export default function GroceryTable({ groceries, onEdit, onDelete }) {

  return (
    <div className="overflow-x-auto w-full">
      <table className="min-w-full bg-white border border-gray-200 rounded-lg shadow-md">
        <thead className="bg-sky-100 text-left text-sm font-semibold">
          <tr>
            <th className="px-4 py-2">Name</th>
            <th className="px-4 py-2">Quantity</th>
            <th className="px-4 py-2">Unit</th>
            <th className="px-4 py-2">Purchase Date</th>
            <th className="px-4 py-2">Expiry Date</th>
            <th className="px-4 py-2">Category</th>
            <th className="px-4 py-2">Actions</th>
          </tr>
        </thead>
        <tbody>
          {groceries.length === 0 ? (
            <tr>
              <td colSpan={7} className="text-center px-4 py-6 text-gray-500">
                No groceries found.
              </td>
            </tr>
          ) : (
            groceries.map((item) => (
              <tr
                key={item.id}
                className="hover:bg-gray-50 transition-colors text-sm border-t border-gray-200"
              >
                <td className="px-4 py-2">{item.name}</td>
                <td className="px-4 py-2">{item.quantity}</td>
                <td className="px-4 py-2">{item.unit}</td>
                <td className="px-4 py-2">{item.purchaseDate}</td>
                <td className="px-4 py-2">{item.expiryDate}</td>
                <td className="px-4 py-2">{item.category?.name || '—'}</td>
                <td className="px-4 py-2">
                  <DropdownMenu.Root>
                    <DropdownMenu.Trigger className="p-1 px-2 bg-sky-100 text-sky-700 rounded hover:bg-sky-200">
                      ⋮
                    </DropdownMenu.Trigger>
                    <DropdownMenu.Content
                      sideOffset={5}
                      className="bg-white border rounded-md shadow-lg p-1 text-sm"
                    >
                      <DropdownMenu.Item
                        className="px-4 py-2 hover:bg-sky-100 cursor-pointer rounded"
                        onClick={() => onEdit(item)}
                      >
                        Edit
                      </DropdownMenu.Item>
                      <DropdownMenu.Separator className="h-px bg-gray-200 my-1" />
                      <DropdownMenu.Item
                        className="px-4 py-2 text-red-600 hover:bg-red-100 cursor-pointer rounded"
                        onClick={() => onDelete(item)}
                      >
                        Delete
                      </DropdownMenu.Item>
                    </DropdownMenu.Content>
                  </DropdownMenu.Root>
                </td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
}
