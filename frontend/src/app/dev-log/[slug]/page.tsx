import { readingTimeMinutes } from "@/libs/dev-log/readingTime";
import DevLogEntry from "@/components/dev-log/DevLogEntry";
import { notFound } from "next/navigation";
import type { DevLogEntryProps } from "@/components/dev-log/DevLogEntry";

interface DevLogPageProps {
  params: { slug: string };
}

export default async function DevLogDayPage({ params }: DevLogPageProps) {
  const { slug } = params;

  let entry: DevLogEntryProps;
  try {
    const module = await import(`@/data/devlog/${slug}.json`);
    entry = module.default as DevLogEntryProps;
  } catch {
    return notFound();
  }

  const readingTime = readingTimeMinutes({ content: entry.content });

  return (
    <section className="dev-log-section">
      <DevLogEntry {...entry} readingTime={readingTime} />
    </section>
  );
}