"use client";

import { useEffect, useState } from "react";

interface NotificationProps {
  message: string;
  type?: "correct" | "wrong" | "warning";
  duration?: number;
  onClose?: () => void;
}

export default function Notification({
    message,
    type = "wrong",
    duration = 3000,
    onClose,
    }: NotificationProps) {
    const [visible, setVisible] = useState(true);

    useEffect(() => {
    const timer = setTimeout(() => {
        setVisible(false);
        if (onClose) onClose();
    }, duration);

    return () => clearTimeout(timer);
    }, [duration, onClose]);

    if (!visible) return null;

    return (
    <div className={`moodmap-notification ${type}`}>
        {message}
    </div>
    );

}