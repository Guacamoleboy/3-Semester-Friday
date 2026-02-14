"use client"

import Link from "next/link";

export default function Footer() {
    return (
        <div className="moodmap-footer">
            <span className="moodmap-footer-title">
                MoodMap - Keeping track of YOUR mood.
            </span>

            <div className="moodmap-footer-links">
                <span className="moodmap-footer-divider">|</span>

                <Link href="/privacy" className="moodmap-footer-link">
                    Privatlivspolitik
                </Link>

                <span className="moodmap-footer-divider">•</span>

                <Link href="/terms" className="moodmap-footer-link">
                    Terms of Use
                </Link>

                <span className="moodmap-footer-divider">•</span>

                <Link href="/cookies" className="moodmap-footer-link">
                    Cookies
                </Link>
            </div>
        </div>
    );
}