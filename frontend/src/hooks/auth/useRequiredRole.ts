"use client";

import { useEffect } from "react";
import { useRouter } from "next/navigation";

// ## IMPORTANT ##

// Placeholder till REST API has been added


export function useRequiredRole(requiredRole: string) {
  
  const router = useRouter();

  useEffect(() => {
    const role = localStorage.getItem("userRole");

    if (role !== requiredRole) {
      router.replace("/login");
    }
  }, [requiredRole, router]);

}