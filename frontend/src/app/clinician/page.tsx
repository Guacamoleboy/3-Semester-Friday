"use client"

import { useEffect } from "react";
import { useRouter } from "next/navigation";
import ClinicianDashboard from "@/components/clinician/ClinicianDashboard";

export default function ClinicianPage() {
  const router = useRouter();
  useEffect(() => {
    const role = localStorage.getItem("userRole");
    if (role !== "clinician") {
      router.replace("/login");
    }
  }, [router]);
  return <ClinicianDashboard />;
}