"use client";

import { useEffect, useRef, useState } from "react";
import { LottiePlayer } from "@/libs/lottie/LottiePlayer";

export default function Preloader() {

    // Initial setup
    const [isDone, setIsDone] = useState(false);
    const containerRef = useRef<HTMLDivElement>(null);

    useEffect(() => {

        // No container validation
        if (!containerRef.current) return;
        
        // Lottie call with param
        const player = new LottiePlayer({
            container: containerRef.current,
            path: "/images/logo/logo-svg.json",
            duration: 0.5,
            loop: false,
            onComplete: () => setIsDone(true),
        });

        // Init player
        player.init();

        // Destroy lottie
        return () => player.destroy();

    }, []);

    // Insert preloader to DOM
    return (
        <div className={`preloader ${isDone ? "fade-out" : ""}`}>
            <div ref={containerRef} className="preloader-img" />
        </div>
    );

}