import DashboardCard from "./Dashboard";

export default function Dashboard() {
  return (
    <div className="p-6 space-y-6">
      <h2 className="text-2xl font-semibold">Dashboard</h2>
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        <DashboardCard title="Total Items" value={50} />
        <DashboardCard title="Low Stock" value={7} />
        <DashboardCard title="Expired Items" value={3} />
      </div>
    </div>
  );
}
