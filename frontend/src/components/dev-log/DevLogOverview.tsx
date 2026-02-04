"use client";

import DevLogLoader from "@/libs/dev-log/devlogLoader";

interface DevLogOverviewProps {
    loaders: DevLogLoader[];
}

export default function DevLogOverview({ loaders }: DevLogOverviewProps) {
    return (
        <div className="dev-log-overview-wrapper">
            {loaders.map((loader, index) => (
            <div className="dev-log-overview-card" key={index}>

                {/* Meta Per Card */}
                <div className="dev-log-overview-card-meta">
                    <span>{loader.returnReadingTimeMinutes()} min read</span>
                    <span>{loader.returnDate()}</span>
                </div>

                {/* Center Content */}
                <div className="dev-log-overview-card-center">
                    <img
                        src={loader.returnOverviewPhoto()}
                        alt={loader.returnTitle()}
                    />
                </div>

                {/* Header & subtitle */}
                <div className="dev-log-overview-card-desc">
                    <span>{loader.returnTitle()}</span>
                    <span>{loader.returnDescription()}</span>
                </div>

            </div>
            ))}
        </div>
    );
}