import { PropsWithChildren } from "react"

export default function ClientLayout({ children }: PropsWithChildren) {
  return (
    <section className="client-section">
      {children}
    </section>
  )
}