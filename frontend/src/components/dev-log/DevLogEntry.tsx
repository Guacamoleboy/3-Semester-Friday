import type { DevLogContentItem, DevLogEntryPropsPlain } from "@/libs/dev-log/devlogLoader";

interface DevLogEntryProps {
  loader: DevLogEntryPropsPlain;
}

export default function DevLogEntry({ loader }: DevLogEntryProps) {

    // Initial
    const { title, date, readingTime, badges, content } = loader;

    // Parser
    const parseMoodmapText = (text: string, keyPrefix: string) => {
        const root: any = { children: [] };
        const stack = [root];

        const regex = /<moodmap-([a-z]+)>|<moodmap-stop>|<moodmap-lc>|<moodmap-lcpush>/g;
        let lastIndex = 0;
        let match;

        while ((match = regex.exec(text)) !== null) {
            const before = text.slice(lastIndex, match.index);
            if (before) {
                stack[stack.length - 1].children.push(before);
            }

            if (match[0] === "<moodmap-stop>") {
                stack.pop();
            } 
            else if (match[0] === "<moodmap-lc>") {
                stack[stack.length - 1].children.push("\n");
            }
            else if (match[0] === "<moodmap-lctwo>") {
                stack[stack.length - 1].children.push("\n\n");
            }  
            else if (match[0] === "<moodmap-lcpush>") {
                stack[stack.length - 1].children.push("\n\t");
            } 
            else if (match[1]) {
                const node = {
                    type: match[1],
                    children: []
                };
                stack[stack.length - 1].children.push(node);
                stack.push(node);
            }

            lastIndex = regex.lastIndex;
        }

        const after = text.slice(lastIndex);
        if (after) {
            stack[stack.length - 1].children.push(after);
        }

        return renderNodes(root.children, keyPrefix);
    };

    // Nodes
    const renderNodes = (nodes: any[], keyPrefix: string) => {
        return nodes.map((node, index) => {
            if (typeof node === "string") return node;

            return (
                <span
                    key={`${keyPrefix}-${index}`}
                    className={`moodmap-${node.type}`}
                    style={
                        node.type === "terminal"
                            ? { whiteSpace: "pre-wrap" }
                            : undefined
                    }
                >
                    {renderNodes(node.children, `${keyPrefix}-${index}`)}
                </span>
            );
        });
    };


    // Entry
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
                        return (
                            <p key={i} className={item.class}>
                            {Array.isArray(item.text)
                                ? item.text.map((t, idx) =>
                                    t ? (
                                    <span key={`${i}-${idx}`}>{parseMoodmapText(t, `${i}-${idx}`)}</span>
                                    ) : null
                                )
                                : item.text
                                ? parseMoodmapText(item.text, `${i}-0`)
                                : null}
                            </p>
                        );
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
