"use client";

import DevLogEntry, { DevLogEntryProps } from "./DevLogEntry";
import day1Json from "@/data/devlog/day1.json";

// Array of .json files
const day1 = day1Json as DevLogEntryProps;
const devLogData: DevLogEntryProps[] = [day1];

// UI Load
export default function DevLogUI() {
  return (
    <section className="dev-log-section">

      {/* Site Meta Description */}
      <header className="dev-log-meta">
        <p>MoodMap / dev-log</p>
        <h2>Developer Log for MoodMap</h2>
        <p>Development overview for my 3. Semester projekt called MoodMap</p>
      </header>

      {/* Main Box */}
      <div className="dev-log-content-wrapper">

        {/* Card Per Day */}
        {devLogData.map((entry, i) => (
          <DevLogEntry key={i} {...entry} />
        ))}
        
        {/* Divider pr article / card */}
        <div className="section-divider"></div>

      </div>
    </section>
  );
}
