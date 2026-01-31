"use client";

import { useRouter } from "next/navigation";

interface ReturnButtonProps {
  to?: string;
}

export default function ReturnButton({ to }: ReturnButtonProps) {
  const router = useRouter();

  const handleClick = () => {
    if (to) {
      router.push(to);
    } else {
      router.back();
    }
  };

  return (
    <div className="return-button-moodmap">
      <button onClick={handleClick} aria-label="Go back">
        <i className="fa fa-arrow-left"></i>
      </button>
    </div>
  );
}