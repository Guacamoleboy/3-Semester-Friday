"use client"

import { useRequiredRole } from "@/hooks/auth/useRequiredRole";
import IntroBar from "@/components/ui/IntroBar";
import GraphStock from "@/components/ui/GraphStock";
import { generateFakeGraphData } from "@/libs/ui/GraphStockLibs";

export default function ClientPage() {

  // Role validation
  useRequiredRole("client");

  return (
    
    <IntroBar />

    /*
    // Graph Stock
    <div className="graph-container">
      <h1 className="graph-container-title">Humør</h1>
      <div className="graph-stock-wrapper">
        <GraphStock />
      </div>
    </div>
    */
  );
}