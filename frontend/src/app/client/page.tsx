"use client"

import { useRequiredRole } from "@/hooks/auth/useRequiredRole";
import IntroBar from "@/components/ui/IntroBar";
import GraphStock from "@/components/ui/GraphStock";
import { generateFakeGraphData } from "@/libs/ui/GraphStockLibs";

// ## IMPORTANT ##

// Placeholder till REST API has been added

export default function ClientPage() {

  // Role validation
  useRequiredRole("client");

  return (
    
    <IntroBar />

  );
}