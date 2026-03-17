import { useState } from "react";
import { Navigate, Route, Routes, useNavigate } from "react-router-dom";
import SideNav from "./components/SideNav";
import TopBar from "./components/TopBar";
import SearchModal from "./components/SearchModal";
import HomePage from "./pages/HomePage";
import PoetPage from "./pages/PoetPage";
import ActorPage from "./pages/ActorPage";
import PlaylistPage from "./pages/PlaylistPage";
import EducationPage from "./pages/EducationPage";
import FavoritesPage from "./pages/FavoritesPage";
import LibraryPage from "./pages/LibraryPage";
import SearchResultsPage from "./pages/SearchResultsPage";

export default function App() {
  const navigate = useNavigate();
  const [searchOpen, setSearchOpen] = useState(false);

  return (
    <div className="app-shell">
      <SideNav onSearchClick={() => setSearchOpen(true)} />
      <div className="app-main">
        <TopBar />
        <main className="main-scroll">
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/poets/:id" element={<PoetPage />} />
            <Route path="/actors/:id" element={<ActorPage />} />
            <Route path="/playlists/:id" element={<PlaylistPage />} />
            <Route path="/education" element={<EducationPage />} />
            <Route path="/library" element={<LibraryPage />} />
            <Route path="/favorites" element={<FavoritesPage />} />
            <Route path="/search" element={<SearchResultsPage />} />
            <Route path="*" element={<Navigate to="/" replace />} />
          </Routes>
        </main>
      </div>

      <SearchModal
        isOpen={searchOpen}
        onClose={() => setSearchOpen(false)}
        onSubmit={(query) => {
          setSearchOpen(false);
          navigate(`/search?q=${encodeURIComponent(query)}`);
        }}
      />
    </div>
  );
}