package dk.project.scraping.engine;

class BrowserData {

    static final String[] AGENTS = {
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36",
            "Mozilla/5.0 (X11; Linux x86_64; rv:123.0) Gecko/20100101 Firefox/123.0",
            "Mozilla/5.0 (iPhone; CPU iPhone OS 17_3_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.3.1 Mobile/15E148 Safari/604.1"
    };

    // _________________________________________________________________________________________________________________

    static final String[] REFERRERS = {
            "https://www.google.com",
            "https://www.facebook.com",
            "https://www.instagram.com",
            "https://www.discord.com",
            "https://www.twitch.com",
            "https://www.kick.com"
    };

    // _________________________________________________________________________________________________________________

    static final String[] LANGUAGES = {
            "da-DK,da;q=0.9,en-US;q=0.8,en;q=0.7",
            "en-US,en;q=0.9",
            "en-GB,en;q=0.8",
            "nb-NO,nb;q=0.9,no;q=0.8"
    };

}