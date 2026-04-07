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
import day7Json from "@/data/devlog/day7.json";
import day8Json from "@/data/devlog/day8.json";
import day9Json from "@/data/devlog/day9.json";
import day10Json from "@/data/devlog/day10.json";
import day11Json from "@/data/devlog/day11.json";
import day12Json from "@/data/devlog/day12.json";
import day13Json from "@/data/devlog/day13.json";
import day14Json from "@/data/devlog/day14.json";
import day15Json from "@/data/devlog/day15.json";
import day16Json from "@/data/devlog/day16.json";
import day17Json from "@/data/devlog/day17.json";

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
    new DevLogLoader("day17", day17Json).toProps(),
    new DevLogLoader("day16", day16Json).toProps(),
    new DevLogLoader("day15", day15Json).toProps(),
    new DevLogLoader("day14", day14Json).toProps(),
    new DevLogLoader("day13", day13Json).toProps(),
    new DevLogLoader("day12", day12Json).toProps(),
    new DevLogLoader("day11", day11Json).toProps(),
    new DevLogLoader("day10", day10Json).toProps(),
    new DevLogLoader("day9", day9Json).toProps(),
    new DevLogLoader("day8", day8Json).toProps(),
    new DevLogLoader("day7", day7Json).toProps(),
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