import '@/styles/style.css';
import '@/styles/animation.css';
import Header from '@/components/Header';
import Preloader from '@/components/ui/Preloader';
import { ReactNode } from "react";

export const metadata = {
  title: 'MoodMap - Mood tracking og velvære',
  description:
    'MoodMap hjælper med at overvåge og forstå mental sundhed, så vi sammen kan tage bedre beslutninger for dit velbefindende.',
    icons: {
    icon: 'images/logo/logo-icon.png',           
    shortcut: 'images/logo/logo-icon-16.png', 
    apple: 'images/logo/logo-icon.png', 
  },
};

export default function RootLayout({ children }: { children: ReactNode }) {

  return (
    <html lang="en">
        <head>
            <link
                rel="stylesheet"
                href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
            />
        </head>
        <body>

            {/* Preloader */}
            <Preloader />

            {/* Header */}
            <Header />

            {/* Page Specific */}
            {children}

        </body>
    </html>
  );
}