"use client";

import DevLogEntry, { DevLogEntryProps } from "./DevLogEntry";
import { readingTimeMinutes } from "@/libs/dev-log/readingTime";
import day1Json from "@/data/devlog/day1.json";
import day2Json from "@/data/devlog/day2.json";

// Array of .json files
const day1 = day1Json as DevLogEntryProps;
const day2 = day2Json as DevLogEntryProps;
const devLogData: DevLogEntryProps[] = [day1, day2];
const devLogDataWithReadingTime = devLogData.map(entry => ({...entry,readingTime: readingTimeMinutes({ content: entry.content })}));

// UI Load
export default function DevLogUI() {
  return (
    <>
      {/* Main Box */}
      <div className="dev-log-content-wrapper">

        {/* Card Per Day */}
        {devLogDataWithReadingTime.map((entry, i) => (
          <div key={i}>
            <DevLogEntry {...entry} /> 
            <div className="section-divider"></div>
          </div>
        ))}

      </div>
    </>
  );
}
