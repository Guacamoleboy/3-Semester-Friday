package dk.project.route;

import dk.project.route.data.PopulateRouting;
import dk.project.route.health.HealthRouting;
import dk.project.route.impl.*;
import dk.project.route.scrape.MedicinScraperRouting;
import dk.project.route.status.StatusRouting;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

public class Routes {

    // Attributes

    // _______________________________________________________________________

    public static EndpointGroup registerRoutes(EntityManagerFactory emf) {

        // Routings
        AuthRouting authRouting = new AuthRouting(emf);
        UserRouting userRouting = new UserRouting(emf);
        ApiRouting apiRouting = new ApiRouting(emf);
        PopulateRouting populateRouting = new PopulateRouting(emf);
        MedicinScraperRouting medicinScraperRouting = new MedicinScraperRouting(emf);
        StatusRouting statusRouting = new StatusRouting(emf);
        HealthRouting healthRouting = new HealthRouting(emf);
        RoleRouting roleRouting = new RoleRouting(emf);
        BaselineRouting baselineRouting = new BaselineRouting(emf);
        BaselineIndividualRouting baselineIndividualRouting = new BaselineIndividualRouting(emf);
        BaselineIndividualSideEffectRouting baselineIndividualSideEffectRouting = new BaselineIndividualSideEffectRouting(emf);
        DiagnoseRouting diagnoseRouting = new DiagnoseRouting(emf);
        DiagnoseClientRouting diagnoseClientRouting = new DiagnoseClientRouting(emf);
        DiagnoseTypeRouting diagnoseTypeRouting = new DiagnoseTypeRouting(emf);
        MedicationClientRouting medicationClientRouting = new MedicationClientRouting(emf);
        MedicationRouting medicationRouting = new MedicationRouting(emf);

        // EndpointGroup Return to server
        return () -> {
            authRouting.routes().addEndpoints();
            userRouting.routes().addEndpoints();
            apiRouting.routes().addEndpoints();
            populateRouting.routes().addEndpoints();
            medicinScraperRouting.routes().addEndpoints();
            statusRouting.routes().addEndpoints();
            healthRouting.routes().addEndpoints();
            roleRouting.routes().addEndpoints();
            baselineRouting.routes().addEndpoints();
            baselineIndividualRouting.routes().addEndpoints();
            baselineIndividualSideEffectRouting.routes().addEndpoints();
            diagnoseRouting.routes().addEndpoints();
            diagnoseClientRouting.routes().addEndpoints();
            diagnoseTypeRouting.routes().addEndpoints();
            medicationRouting.routes().addEndpoints();
            medicationClientRouting.routes().addEndpoints();
        };

    }

}