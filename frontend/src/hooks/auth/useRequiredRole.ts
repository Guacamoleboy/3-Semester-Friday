"use client";

import { useEffect } from "react";
import { useRouter } from "next/navigation";

export function useRequiredRole(requiredRole: string) {
  const router = useRouter();

  useEffect(() => {
    const role = localStorage.getItem("userRole");

    if (role !== requiredRole) {
      router.replace("/login");
    }
  }, [requiredRole, router]);

}