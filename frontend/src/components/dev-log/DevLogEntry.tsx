//
//
//  Each Card in the /dev-log section is loaded dynamicly from .json files
//  This sets up the Entry (Card) per day as an article.
//
//  - Guacamoleboy
//
//

export interface DevLogEntryProps {
    date: string;
    title: string;
    badges: string[];
    content: {
        type: string;
        text: string;
        src?: string;
        alt?: string;
        class?: string;
    }[];
}

export default function DevLogEntry({ date, title, badges, content }: DevLogEntryProps) {
  return (
    <article className="dev-log-entry">
        
        {/* Meta Information */}
        <div className="dev-log-entry-header">
            <div className="dev-log-entry-meta">
                <span className="dev-log-title">{title}</span>
                <span className="dev-log-date">{date}</span>
            </div>
            <div className="dev-log-badges">
                {badges.map((b, i) => (
                <span key={i} className={`dev-log-badge ${b.toLowerCase()}`}>{b}</span>
                ))}
            </div>
        </div>

        {/* Card Information */}            
        <div className="dev-log-entry-body">
        {content.map((item, i) => {
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
                alt={item.alt}
                className={additionalClass ? `dev-log-img ${additionalClass}` : "dev-log-img"}
                />
                );
            case "code":
                return <p key={i} className="dev-log-code">{item.text}</p>;
            default:
                return null;
            }
        })}
        </div>

    </article>
  );
}
