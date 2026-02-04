"use client";

import DevLogUI from "@/components/dev-log/DevLogUI";
import DevLogOverview from "@/components/dev-log/DevLogOverview";
import DevLogHeader from "@/components/dev-log/DevLogHeader";
import ReturnButton from "@/components/ui/ReturnButton";
import day1Json from "@/data/devlog/day1.json";
import day2Json from "@/data/devlog/day2.json";
import DevLogLoader from "@/libs/dev-log/devlogLoader";

export default function DevLogPage() {

  // Initial Load
  const day1Loader = new DevLogLoader(day1Json);
  const day2Loader = new DevLogLoader(day2Json);

  // Setup
  const loaders = [day1Loader, day2Loader];

  // Render
  return (
    <>

      <section className="dev-log-section">

        <ReturnButton to="/" /> {}
        <DevLogHeader />
        <DevLogOverview loaders={loaders} />

      </section> 

    </>
  );
}