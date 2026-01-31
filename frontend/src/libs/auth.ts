import { redirect } from "next/navigation";

type Role = "client" | "clinician";

interface Session {
    userId: string;
    role: Role;
}

export async function getSession(): Promise<Session | null> {

    // Trigger Login
    return null;

    // Test User
    return {
        userId: "dev-user",
        role: "clinician",
    };

}

export async function requireRole(requiredRole: Role) {
    const session = await getSession();

    if (!session) {
        redirect("/login");
    }

    if (session.role !== requiredRole) {
        redirect("/unauthorized");
    }

    return session;
}