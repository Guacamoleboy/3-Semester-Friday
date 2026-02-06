import type { DevLogContentItem, DevLogEntryPropsPlain } from "@/libs/dev-log/devlogLoader";

interface DevLogEntryProps {
  loader: DevLogEntryPropsPlain;
}

export default function DevLogEntry({ loader }: DevLogEntryProps) {
    const { title, date, readingTime, badges, content } = loader;

    return (
        <article className="dev-log-entry dev-log-content-wrapper">
            
            {/* Header */}
            <header className="dev-log-entry-header">
                <div className="dev-log-entry-meta">
                    <div className="dev-log-entry-meta-left">
                    <span className="dev-log-title">{title}</span>
                    </div>
                    <div className="dev-log-entry-meta-right">
                    <span className="dev-log-reading-time">{readingTime} min read</span>
                    <span className="dev-log-dot-divider">•</span>
                    <span className="dev-log-date">{date}</span>
                    </div>
                </div>

                <div className="dev-log-badges">
                    {badges.map((b, i) => (
                    <span key={i} className={`dev-log-badge ${b.toLowerCase()}`}>
                        {b}
                    </span>
                    ))}
                </div>
            </header>

            {/* Content */}
            <div className="dev-log-entry-body">
                {content.map((item: DevLogContentItem, i: number) => {
                    const [baseType, size] = item.type.split(" ");
                    const additionalClass = size ? size : "";

                    switch (baseType) {
                    case "p":
                        return <p key={i} className={item.class}>{item.text}</p>;
                    case "h3":
                        return <h3 key={i}>{item.text}</h3>;
                    case "img":
                        return (
                        <img
                            key={i}
                            src={item.src}
                            alt={item.alt || ""}
                            className={additionalClass ? `dev-log-img ${additionalClass}` : "dev-log-img"}
                        />
                        );
                    case "code":
                        return <pre key={i} className="dev-log-code">{item.text}</pre>;
                    default:
                        return null;
                    }
                })}
            </div>
        </article>
    );
}
