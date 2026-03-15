"use client";

import { useState, useEffect } from "react";
import DevLogOverview from "@/components/dev-log/DevLogOverview";
import DevLogLoader from "@/libs/dev-log/devlogLoader";
import DevLogHeader from "@/components/dev-log/DevLogHeader";
import ReturnButton from "@/components/ui/ReturnButton";
import ConnectionDots from "@/components/ui/ConnectionDots"

// Devlogs
import day1Json from "@/data/devlog/day1.json";
import day2Json from "@/data/devlog/day2.json";
import day3Json from "@/data/devlog/day3.json";
import day4Json from "@/data/devlog/day4.json";
import day5Json from "@/data/devlog/day5.json";
import day6Json from "@/data/devlog/day6.json";

export default function DevLogPage() {
  
  // Inital sort state & client check
  const [sortOrder, setSortOrder] = useState<"newest" | "oldest">("newest");
  const [ready, setReady] = useState(false);

  useEffect(() => {
    const saved = localStorage.getItem("devlogSortOrder");
    if (saved === "newest" || saved === "oldest") {
      // Red for no reason. Just let it be. Works as intended.
      // eslint-disable-next-line react-hooks/set-state-in-effect
      setSortOrder(saved);
    }
    setReady(true);
  }, []);

  const handleSortChange = (order: "newest" | "oldest") => {
    setSortOrder(order);
    localStorage.setItem("devlogSortOrder", order);
  };

  // Parse date first
  const parseDate = (dateStr: string) => {
    const [day, monthYear] = dateStr.split('/');
    const [month, year] = monthYear.split('-');
    return new Date(`${year}-${month}-${day}`).getTime();
  };

  // Original loaders
  const loaders = [
    new DevLogLoader("day6", day6Json).toProps(),
    new DevLogLoader("day5", day5Json).toProps(),
    new DevLogLoader("day4", day4Json).toProps(),
    new DevLogLoader("day3", day3Json).toProps(),
    new DevLogLoader("day2", day2Json).toProps(),
    new DevLogLoader("day1", day1Json).toProps(),
  ];

  // Sort
  const sortedLoaders = [...loaders].sort((a, b) => {
    const dateA = parseDate(a.date);
    const dateB = parseDate(b.date);
    return sortOrder === "newest" ? dateB - dateA : dateA - dateB;
  });

  // Client ready check
  if (!ready) return null;

  return (
    <section className="dev-log-section">
      <ReturnButton to="/" />
      <DevLogHeader />
      <ConnectionDots />

      {/* Sort Buttons */}
      <div className="dev-log-sort-buttons">
        <button
          className={sortOrder === "newest" ? "active" : ""}
          onClick={() => handleSortChange("newest")}
        >
          Nyeste
        </button>
        <button
          className={sortOrder === "oldest" ? "active" : ""}
          onClick={() => handleSortChange("oldest")}
        >
          Ældste
        </button>
      </div>

      {/* Overview */}
      <DevLogOverview
        loaders={sortedLoaders}
        key={sortOrder}
      />

    </section>

  );
  
}