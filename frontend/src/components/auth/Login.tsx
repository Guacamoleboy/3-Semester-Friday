"use client";

import { useState, useEffect } from "react";
import { useRouter } from "next/navigation";
import Notification from "@/components/ui/Notification";

export default function Login() {

  // Disables scroll
  useEffect(() => {
    document.body.style.overflow = "hidden";
    return () => {
      document.body.style.overflow = "auto";
    };
  }, []);

  const router = useRouter();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [notification, setNotification] = useState("");
  const [notificationType, setNotificationType] = useState<"correct" | "wrong" | "warning">("wrong");

  const hardcodedUser = {
    email: "jonas68@live.dk",
    password: "password",
  };

  const handleLogin = (e: React.FormEvent) => {
    e.preventDefault();
    if (email === hardcodedUser.email && password === hardcodedUser.password) {
      localStorage.setItem("userRole", "clinician"); // Debug
      router.push("/clinician")
    } else {
      setNotification("Ugyldig email eller kodeord");
      setNotificationType("wrong");
    }
  };

  return (
    <section className="login-section">
      <div className="login-box">

        {/* Left side */}
        <div className="login-page-left">

          <div className="login-form-wrapper">
            <h2>Medarbejder Portal</h2>
            <form onSubmit={handleLogin}>
              <input
                type="email"
                placeholder="Indtast email"
                value={email}
                onChange={e => setEmail(e.target.value)}
                required
              />
              <input
                type="password"
                placeholder="Indtast kodeord"
                value={password}
                onChange={e => setPassword(e.target.value)}
                required
              />
              <div className="button-wrapper-login">
                <button type="submit">Log Ind</button>
                <button type="submit">Glemt noget?</button>
              </div>
            </form>
          </div>

        </div>

        {/* Right */}
        <div className="login-page-right">
          <img src="images/logo/logo-white.png" alt="Logo" className="login-right-logo"/>
        </div>

      </div>

      {/* Notification */}
      {notification && (
        <Notification
          message={notification}
          type={notificationType}
          duration={4000}
          onClose={() => setNotification("")}
        />
      )}

    </section>
    
  );
}