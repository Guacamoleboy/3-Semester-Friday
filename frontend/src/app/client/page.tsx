"use client"

import ClientDashboard from "@/components/client/ClientDashboard";
import { useEffect } from "react";
import { useRouter } from "next/navigation";

export default function ClientPage() {
    const router = useRouter();
      useEffect(() => {
        const role = localStorage.getItem("userRole");
        if (role !== "client") {
          router.replace("/login");
        }
      }, [router]);
      return <ClientDashboard />;
}