"use client"

import ClinicianDashboard from "@/components/clinician/ClinicianDashboard";
import { useRequiredRole } from "@/hooks/auth/useRequiredRole";

export default function ClinicianPage() {
  useRequiredRole("clinician");
  return <ClinicianDashboard />;
}