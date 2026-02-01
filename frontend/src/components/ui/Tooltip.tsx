import { ReactNode, useState } from "react";

export default function Tooltip({content, children}: {content: string[]; children: ReactNode}) {

    // Initial Setup
    const [visible, setVisible] = useState(false);
    const [position, setPosition] = useState({ x: 0, y: 0 });

    // Mouse Movement
    const handleMouseMove = (e: React.MouseEvent) => {
    setPosition({ x: e.clientX, y: e.clientY });
    };

    // Visible & Actions
    return (
    <>

        {/* Tooltip Wrapper */}
        <div
        className="tooltip-wrapper"
        onMouseEnter={() => setVisible(true)}
        onMouseLeave={() => setVisible(false)}
        onFocus={() => setVisible(true)}
        onBlur={() => setVisible(false)}
        onMouseMove={handleMouseMove}
        >

        {/* <Tooltip> Content */}
        {children}

        </div>
        
        {/* tooltip-wrapper :hover */}
        {visible && (
        <span
        className="tooltip tooltip-follow"
        style={{
        top: position.y + 15 + "px",
        left: position.x + "px",
        }}
        >

            {/* Array of Strings */}
            {content.map((line, idx) => (
            <div key={idx} className="tooltip-line">
                {line}
            </div>
            ))}
            
        </span>
        )}
    </>
    );
    
}