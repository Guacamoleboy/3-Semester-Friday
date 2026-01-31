"use client";

import { usePathname, useRouter } from "next/navigation";
import { useState, useEffect } from "react";

interface User {
  role: "clinician" | "client";
  name?: string;
  id?: string;
}

export default function Header() {
  const pathname = usePathname();
  const router = useRouter();
  const [user, setUser] = useState<User | null | undefined>(undefined);

  useEffect(() => {
    if (typeof window === "undefined") return;

    const role = localStorage.getItem("userRole");

    switch (role) {
      case "clinician":
        setUser({ role, name: "Jonas Meinert Larsen" });
        break;
      case "client":
        setUser({ role, id: "6712-ID95-5010-0017" });
        break;
      default:
        setUser(null);
        break;
    }
  }, []);

  // Hide header
  if (pathname.startsWith("/login") || pathname.startsWith("/dev-log") || user === undefined || user === null) return null;

  // Logout Button Handle
  const handleLogout = () => {
    if (typeof window === "undefined") return;
    localStorage.removeItem("userRole");
    router.replace("/login");
  };

  return (
    <header className="header-wrap">
      <div className="announcement-bar">
        <div className="announcement-track">
          <img src="images/logo/danish-flag.png" alt="DK Flag" className="announcement-bar-flag" />
          <span>MoodMap er under udvikling | Kom venligst tilbage senere</span>
          <img src="images/logo/danish-flag.png" alt="DK Flag" className="announcement-bar-flag" />
        </div>
      </div>

      <div className="moodmap-navbar">
        <div className="moodmap-navbar-left">
          <div className="moodmap-navbar-logo">
            <img src="/images/logo/logo-white.png" alt="moodmap-logo" className="moodmap-logo-size" />
          </div>
          <div className="moodmap-navbar-content">
            <span>Lægerne i Skævinge</span>
            <span>•</span>
            <span>{user.role === "client" ? user.id : user.name}</span>
          </div>
        </div>

        <div className="moodmap-navbar-right">
          <div className="moodmap-navbar-buttons">
            {user.role === "clinician" && <button>Opret Skema</button>}
            {user.role === "clinician" && <button onClick={handleLogout}>Log Ud</button>}
          </div>
        </div>
      </div>
    </header>
  );
}