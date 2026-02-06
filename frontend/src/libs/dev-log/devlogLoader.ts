export interface DevLogContentItem {
    type: string;         
    text?: string | string[];
    src?: string;
    alt?: string;
    class?: string;
}

export interface DevLogEntryPropsPlain {
    slug: string;
    title: string;
    date: string;
    readingTime: number;
    badges: string[];
    content: DevLogContentItem[];
    description: string;
    overviewPhoto: string;
}

interface DevLogJSON {
    date: string;
    title: string;
    description: string;
    overviewPhoto: string;
    badges: string[];
    content: DevLogContentItem[];
}

// Constants
const DEFAULT_WORDS_PER_MINUTE = 200;
const SECONDS_PER_IMAGE = 20;

export default class DevLogLoader {

    // Attributes
    private slug: string
    private data: DevLogJSON;

    // ___________________________________________________________________

    constructor(slug: string, jsonData: DevLogJSON) {
        this.data = jsonData;
        this.slug = slug;
    }

    // ___________________________________________________________________

    returnSlug(): string {
        return this.slug;
    }

    // ___________________________________________________________________

    returnTitle(): string {
        return this.data.title;
    }

    // ___________________________________________________________________

    returnDate(): string {
        return this.data.date;
    }

    // ___________________________________________________________________

    returnDescription(): string {
        return this.data.description;
    }

    // ___________________________________________________________________

    returnBadges(): string[] {
        return this.data.badges;
    }

    // ___________________________________________________________________

    returnContent(): DevLogContentItem[] {
        return this.data.content;
    }

    // ___________________________________________________________________

    returnOverviewPhoto(): string {
        return this.data.overviewPhoto;
    }

    // ___________________________________________________________________

    returnHeadings(): string[] {
        return this.data.content
        .filter(item => item.type === 'h3')
        .map(item => {
            if (!item.text) return '';
            return Array.isArray(item.text) ? item.text.join(' ') : item.text;
        });
    }

    // ___________________________________________________________________

    returnImages(): { src: string; alt: string }[] {
        return this.data.content
        .filter(item => item.type.startsWith('img')) 
        .map(item => ({ src: item.src || '', alt: item.alt || '' }));
    }

    // ___________________________________________________________________

    getTextArray(item: DevLogContentItem): string[] {
        if (!item.text) return [];
        return Array.isArray(item.text) ? item.text : [item.text];
    }

    // ___________________________________________________________________
    // Read Time For x-amount of Words from .json file

    private countWords(text: string): number {
        return text.trim().split(/\s+/).filter(Boolean).length;
    }

    // ___________________________________________________________________

    private countReadingTimeSeconds(): number {
        let wordCount = 0;
        let imageCount = 0;

        for (const block of this.data.content) {
            if ("text" in block && block.text) {
                const textArray = Array.isArray(block.text) ? block.text : [block.text];
                for (const t of textArray) {
                    wordCount += this.countWords(t);
                }
            }

            if (block.type.startsWith("img")) {
                imageCount += 1;
            }
        }

        const textSeconds = (wordCount / DEFAULT_WORDS_PER_MINUTE) * 60;
        const imageSeconds = imageCount * SECONDS_PER_IMAGE;

        return Math.ceil(textSeconds + imageSeconds);
    }

    // ___________________________________________________________________

    returnReadingTimeSeconds(): number {
        return this.countReadingTimeSeconds();
    }

    // ___________________________________________________________________

    returnReadingTimeMinutes(): number {
        return Math.ceil(this.countReadingTimeSeconds() / 60);
    }

    // ___________________________________________________________________

    toProps(): DevLogEntryPropsPlain {
        return {
        slug: this.slug,
        title: this.returnTitle(),
        date: this.returnDate(),
        readingTime: this.returnReadingTimeMinutes(),
        badges: this.returnBadges(),
        content: this.returnContent(),
        description: this.returnDescription(),
        overviewPhoto: this.returnOverviewPhoto()
        };
    }

}