"use client"

import { useEffect, useState } from "react";

export default function Preloader() {
    const [show, setShow] = useState(true);

    useEffect(() => {
        const timer = setTimeout(() => setShow(false), 1000); 
        return () => clearTimeout(timer);
    }, []);

    if (!show) return null;

    return (
        <div className="preloader">
        <img src="images/logo/logo-white.png" className="preloader-img" />
        </div>
    );
}