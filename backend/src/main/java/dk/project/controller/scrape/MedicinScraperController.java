package dk.project.controller.scrape;

import dk.project.scraping.impl.MedicinDotDK;
import dk.project.util.TryCatchHelper;
import io.javalin.http.Context;
import jakarta.persistence.EntityManager;

public class MedicinScraperController {

    // Attributes
    private final EntityManager em;

    // _________________________________________________________________________________________________________________

    public MedicinScraperController(EntityManager em) {
        this.em = em;
    }

    // _________________________________________________________________________________________________________________

    public void getSideEffects(Context ctx) {
        TryCatchHelper.tryCatchHelperVoid(ctx, () -> {
            String id = ctx.pathParam("id");
            MedicinDotDK medicinDotDK = new MedicinDotDK(em);
            medicinDotDK.getSideEffects(id);
        },"Successfully scraped the ID");
    }

}