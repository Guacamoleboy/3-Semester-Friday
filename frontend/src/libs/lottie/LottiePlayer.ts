import lottie, { AnimationItem } from "lottie-web";

interface LottieOptions {
    container: HTMLElement;
    path: string;
    duration: number;
    loop: boolean;
    autoplay?: boolean;
    onComplete?: () => void;
}

export class LottiePlayer {

    // Attributes
    private animation: AnimationItem | null = null;

    // _____________________________________________________________
    
    constructor(private options: LottieOptions) {}

    // Play lottie animation
    public init() {
        const {
            container,
            path,
            loop = false,
            autoplay = true,
            duration,
            onComplete
        } = this.options;

        this.animation = lottie.loadAnimation({
            container,
            renderer: "svg",
            loop,
            autoplay,
            path,
        });

        this.animation.addEventListener("DOMLoaded", () => {
            if (!duration || !this.animation) return;

            const originalDuration = this.animation.getDuration(false);
            const speed = originalDuration / duration;
            this.animation.setSpeed(speed);
        });

        if (onComplete) {
            this.animation.addEventListener("complete", onComplete);
        }

    }

    // Destroy lottie player
    public destroy() {
        this.animation?.destroy();
        this.animation = null;
    }

}