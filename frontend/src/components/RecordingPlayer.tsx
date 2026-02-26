import React, { useEffect, useState } from "react";
import KaraokePlayer from "./KaraokePlayer";

type RecordingKaraokeDto = {
  id: string;
  audioUrl: string;
  startTimeSec: number;
  lines: string[];
  lineCount?: number;
};

const RecordingPlayer: React.FC = () => {
  const [recording, setRecording] = useState<RecordingKaraokeDto | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch("http://localhost:8080/api/recordings/demo-1/karaoke")
      .then((res) => res.json())
      .then((data) => {
        setRecording(data);
        setLoading(false);
      })
      .catch((err) => console.error("Błąd podczas pobierania:", err));
  }, []);

  if (loading || !recording) return <div>Ładowanie...</div>;

  return (
    <div
      style={{
        maxWidth: "600px",
        margin: "20px auto",
        background: "white",
        padding: "20px",
        borderRadius: "8px",
        boxShadow: "0 0 10px rgba(0,0,0,0.2)",
      }}
    >
      <h1>{recording.id}</h1>
      <KaraokePlayer
        audioUrl={recording.audioUrl}
        startTimeSec={recording.startTimeSec}
        lines={recording.lines}
        lineCount={recording.lineCount!}
      />
    </div>
  );
};

export default RecordingPlayer;