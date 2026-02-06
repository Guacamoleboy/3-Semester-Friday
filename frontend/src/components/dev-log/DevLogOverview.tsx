// DevLogOverview.tsx
"use client";
import Link from "next/link";
import type { DevLogEntryPropsPlain } from "@/libs/dev-log/devlogLoader";

interface DevLogOverviewProps {
  loaders: DevLogEntryPropsPlain[]; 
}

export default function DevLogOverview({ loaders }: DevLogOverviewProps) {
  return (
    <div className="dev-log-overview-wrapper">
      {loaders.map(loader => (
        <Link key={loader.slug} href={`/dev-log/${loader.slug}`} className="dev-log-overview-link">
          <div className="dev-log-overview-card">
            <div className="dev-log-overview-card-meta">
              <span>{loader.readingTime} min read</span>
              <span>{loader.date}</span>
            </div>
            <div className="dev-log-overview-card-center">
              <img src={loader.overviewPhoto} alt={loader.title} />
            </div>
            <div className="dev-log-overview-card-desc">
              <span>{loader.title}</span>
              <span>{loader.description}</span>
            </div>
          </div>
        </Link>
      ))}
    </div>
  );
}
