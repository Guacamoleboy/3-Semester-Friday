"use client";

import { useEffect } from "react";
import { useRouter } from "next/navigation";

// ## IMPORTANT ##

// Placeholder till REST API has been added

export default function HomePage() {
  const router = useRouter();

  useEffect(() => {
    const role = localStorage.getItem("userRole");

    switch (role) {
      case "client":
        router.replace("/client");
        break;
      case "clinician":
        router.replace("/clinician");
        break;
      default:
        router.replace("/login");
        break;
    }
  }, [router]);

  return null;
  
}