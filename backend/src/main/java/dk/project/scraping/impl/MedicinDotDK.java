package dk.project.scraping.impl;

import dk.project.entity.SideEffectMedication;
import dk.project.scraping.engine.WebScraper;
import dk.project.service.internal.SideEffectMedicationService;
import jakarta.persistence.EntityManager;
import org.jsoup.nodes.Element;
import java.util.List;

public class MedicinDotDK {

    // Attributes
    private final WebScraper webScraper = new WebScraper();
    private final SideEffectMedicationService sideEffectMedicationService;

    // _________________________________________________________________________________________________________________

    public MedicinDotDK(EntityManager em) {
        this.sideEffectMedicationService = new SideEffectMedicationService(em);
    }

    // _________________________________________________________________________________________________________________

    public void getSideEffects(String id) {

        // Setup
        String url = "https://pro.medicin.dk/Medicin/Praeparater/" + id;
        webScraper.setup(url);

        // Exec
        Element section = webScraper.findSection("glob-content-section-wrapper", "Bivirkninger", "h3");
        List<Element> parents = webScraper.findParent(section, "TrAlternate", "tr");
        List<Element> children = webScraper.findChildren(parents, "tr");

        // Store each
        for (Element child : children) {
            int header = 0;
            for (Element td : child.select("td")) {
                header++;
                if (header > 3) header = 1;
                SideEffectMedication entity = new SideEffectMedication();
                entity.setName(td.text());
                entity.setHeader(header);
                entity.setDescription(null);
                sideEffectMedicationService.create(entity);
            }
        }

    }

}