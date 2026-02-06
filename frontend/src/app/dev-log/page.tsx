import DevLogOverview from "@/components/dev-log/DevLogOverview";
import DevLogLoader from "@/libs/dev-log/devlogLoader";
import DevLogHeader from "@/components/dev-log/DevLogHeader";
import ReturnButton from "@/components/ui/ReturnButton";

// Devlogs
import day1Json from "@/data/devlog/day1.json";
import day2Json from "@/data/devlog/day2.json";
import day3Json from "@/data/devlog/day3.json";

export default function DevLogPage() {
  
  // Loader setup
  const loaders = [
    new DevLogLoader("day1", day1Json).toProps(),
    new DevLogLoader("day2", day2Json).toProps(),
    new DevLogLoader("day3", day3Json).toProps()
  ];

  // Visuals & Component render for /dev-log
  return (
    <section className="dev-log-section">
      <ReturnButton to="/" />
      <DevLogHeader />
      <DevLogOverview loaders={loaders} />
    </section>
  );

}