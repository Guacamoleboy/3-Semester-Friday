package dk.project.scraping.engine;

import java.util.Random;

public class BrowserIdentity {

    // Attributes
    private static final Random RANDOM = new Random();

    // _________________________________________________________________________________________________________________

    public static String agentRotation() {
        return BrowserData.AGENTS[RANDOM.nextInt(BrowserData.AGENTS.length)];
    }

    // _________________________________________________________________________________________________________________

    public static String referrerRotation() {
        return BrowserData.REFERRERS[RANDOM.nextInt(BrowserData.REFERRERS.length)];
    }

    // _________________________________________________________________________________________________________________

    public static String languageRotation() {
        return BrowserData.LANGUAGES[RANDOM.nextInt(BrowserData.LANGUAGES.length)];
    }

}