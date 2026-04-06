"use client"

import ClinicianDashboard from "@/components/clinician/ClinicianDashboard";
import { useRequiredRole } from "@/hooks/auth/useRequiredRole";

// ## IMPORTANT ##

// Placeholder till REST API has been added

export default function ClinicianPage() {
  useRequiredRole("clinician");
  return <ClinicianDashboard />;
}