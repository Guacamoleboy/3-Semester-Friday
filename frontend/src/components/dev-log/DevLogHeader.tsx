interface DevLogHeaderProps {
    slug?: string;
}

export default function DevLogHeader({ slug }: DevLogHeaderProps) {

    // Slug formatting
    const formatSlug = (s: string) => {
        return s.replace(/([a-zA-Z]+)(\d+)/, (_, letters, numbers) => {
            return letters.charAt(0).toUpperCase() + letters.slice(1) + " " + numbers;
        });
    }

    return (
        <header className="dev-log-meta">
            <p>MoodMap / dev-log{slug ? ` / ${formatSlug(slug)}` : ""}</p>
            <h2>Developer Log for MoodMap</h2>
            <p>Development overview for my 3. Semester projekt called MoodMap</p>
            <div className="section-divider-devlog"></div>
        </header>
    );

}