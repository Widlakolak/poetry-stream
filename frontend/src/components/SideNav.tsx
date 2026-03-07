import { NavLink } from "react-router-dom";

interface SideNavProps {
  onSearchClick: () => void;
}

const items = [
  { label: "Strona główna", icon: "⌂", to: "/#today" },
  { label: "Edukacja", icon: "🎓", to: "/education" },
  { label: "Biblioteka", icon: "📚", to: "/library" },
  { label: "Ulubione", icon: "★", to: "/favorites" },
];

export default function SideNav({ onSearchClick }: SideNavProps) {
  return (
    <aside className="side-nav" aria-label="Nawigacja boczna">
      {items.map((item) => (
        <NavLink key={item.label} to={item.to} className="side-nav-item" title={item.label}>
          <span>{item.icon}</span>
        </NavLink>
      ))}

      <button className="side-nav-item side-nav-search" title="Szukaj" onClick={onSearchClick}>
        <span>⌕</span>
      </button>
    </aside>
  );
}