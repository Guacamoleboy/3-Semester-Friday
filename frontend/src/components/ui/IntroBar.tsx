"use client";

import Tooltip from "@/components/ui/Tooltip";
import ConnectionDots from "@/components/ui/ConnectionDots"

export default function IntroBar() {
  return (
    <>

    <ConnectionDots />

    <div className="moodmap-intro-bar moodmap-flex">

      {/* Intro Bar Card */}
      <div className="moodmap-intro-bar-card moodmap-flex">
        <i className="fa fa-file-text-o"></i>
        <h2>Aktive skemaer</h2>
        <span>2</span>
      </div>

      {/* Intro Bar Card */}
      <div className="moodmap-intro-bar-card moodmap-flex">
        <i className="fa fa-line-chart"></i>
        <h2>Progress</h2>
        <span>60%</span>
      </div>

      {/* Intro Bar Card */}
      <div className="moodmap-intro-bar-card moodmap-flex">
        <i className="fa fa-fire"></i>
        <h2>Streak</h2>
        <span>2</span>
      </div>

      {/* Intro Bar Card */}
      <div className="moodmap-intro-bar-card moodmap-flex">
        <i className="fa fa-bell"></i>
        <h2>Husk din medicin</h2>

        <Tooltip content={["2 x noget", "1 x noget"]}>
          <button className="moodmap-intro-bar-button">
            Se liste
          </button>
        </Tooltip>

      </div>

    </div>
    </>
  );
}