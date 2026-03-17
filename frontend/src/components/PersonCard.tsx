import { Link } from "react-router-dom";

interface PersonCardProps {
  name: string;
  subtitle?: string;
  photoUrl: string;
  to: string;
}

export default function PersonCard({ name, subtitle, photoUrl, to }: PersonCardProps) {
  return (
    <Link to={to} className="tile person-tile">
      <img src={photoUrl} alt={name} loading="lazy" />
      <div>
        <h3>{name}</h3>
        {subtitle && <p>{subtitle}</p>}
      </div>
    </Link>
  );
}