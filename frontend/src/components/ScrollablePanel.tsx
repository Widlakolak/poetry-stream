import type { ReactNode } from "react";

interface ScrollablePanelProps {
  title: string;
  children: ReactNode;
}

export default function ScrollablePanel({ title, children }: ScrollablePanelProps) {
  return (
    <section className="panel">
      <header className="panel-header">
        <h2>{title}</h2>
      </header>
      <div className="horizontal-scroll">{children}</div>
    </section>
  );
}