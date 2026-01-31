"use client"

import ClientDashboard from "@/components/client/ClientDashboard";
import { useRequiredRole } from "@/hooks/auth/useRequiredRole";

export default function ClientPage() {
  useRequiredRole("client");
  return <ClientDashboard />;
}