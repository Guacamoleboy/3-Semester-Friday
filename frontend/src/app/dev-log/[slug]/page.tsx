import DevLogEntry from "@/components/dev-log/DevLogEntry";
import DevLogLoader from "@/libs/dev-log/devlogLoader";
import DevLogHeader from "@/components/dev-log/DevLogHeader";
import ReturnButton from "@/components/ui/ReturnButton";
import ConnectionDots from "@/components/ui/ConnectionDots"
import { notFound } from "next/navigation";

interface DevLogPageProps {
  params: { slug: string };
}

export default async function DevLogDayPage({ params }: DevLogPageProps) {
  const { slug } = await params;

  let loader: DevLogLoader;

  try {
    const jsonData = await import(`@/data/devlog/${slug}.json`);
    loader = new DevLogLoader(slug, jsonData.default);
  } catch {
    return notFound();
  }

  return (
    <section className="dev-log-section">
      <ReturnButton to="/dev-log" />
      <DevLogHeader slug={slug}/>
      <ConnectionDots />
      <DevLogEntry loader={loader.toProps()} />
    </section>
  );
}
