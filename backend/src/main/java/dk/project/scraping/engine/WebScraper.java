package dk.project.scraping.engine;

import dk.project.exception.ApiException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.util.ArrayList;
import java.util.List;

public class WebScraper {

    // Attributes
    private Document document;

    // _________________________________________________________________________________________________________________

    public void setup(String url) {
        try {
            document = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .timeout(10000)
                    .get();
        } catch (Exception e) {
            throw new ApiException("Failed to load page: " + url, e, "WebScraper | Setup()");
        }
    }

    // _________________________________________________________________________________________________________________

    public Element findSection(String className, String title, String tag) {
        if (document == null) return null;
        for (Element section : document.getElementsByClass(className)) {
            Element header = section.selectFirst( tag + ":containsOwn(" + title + ")");
            if (header != null) return section;
        }
        return null;
    }

    // _________________________________________________________________________________________________________________

    public List<Element> findParent(Element section, String className, String tag) {
        if (section == null) return new ArrayList<>();
        return section.select(tag + "." + className);
    }

    // _________________________________________________________________________________________________________________

    //public List<> findChildrenAndSave() {

    //}

    // _________________________________________________________________________________________________________________
    // Initial Scrape learning method

    public boolean checkHeader(String title, String tag) {
        if (document == null) return false;
        Element header = document.selectFirst(tag + ":containsOwn(" + title + ")");
        return header != null;
    }

}