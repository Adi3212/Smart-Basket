import * as DropdownMenu from '@radix-ui/react-dropdown-menu';

export default function UserDropdown() {
  return (
    <DropdownMenu.Root>
      <DropdownMenu.Trigger className="p-2 bg-green-600 rounded-full hover:bg-green-700 text-white focus:outline-none">
        Admin
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

        <DropdownMenu.Item className="px-4 py-2 text-sm text-red-600 hover:bg-red-100 rounded-md cursor-pointer">
          Logout
        </DropdownMenu.Item>
      </DropdownMenu.Content>
    </DropdownMenu.Root>
  );
}
