export default function DashboardCard({ title, value }) {
  return (
    <div className="bg-white shadow-md rounded-xl p-5 text-center border">
      <h3 className="text-lg font-semibold text-gray-700">{title}</h3>
      <p className="text-3xl font-bold text-green-600">{value}</p>
    </div>
  );
}
