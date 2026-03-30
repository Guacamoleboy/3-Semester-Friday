package dk.project.config;

import dk.project.entity.*;
import dk.project.entity.api.Api;
import org.hibernate.cfg.Configuration;

public class HibernateAnnotation {

    // Attributes

    // _________________________________________________________________________________________________________________

    public static void registerEntities(Configuration configuration) {
        configuration.addAnnotatedClass(Baseline.class);
        configuration.addAnnotatedClass(BaselineIndividual.class);
        configuration.addAnnotatedClass(Client.class);
        configuration.addAnnotatedClass(Diagnose.class);
        configuration.addAnnotatedClass(DiagnoseClient.class);
        configuration.addAnnotatedClass(DiagnoseType.class);
        configuration.addAnnotatedClass(Medication.class);
        configuration.addAnnotatedClass(MedicationClient.class);
        configuration.addAnnotatedClass(Question.class);
        configuration.addAnnotatedClass(Role.class);
        configuration.addAnnotatedClass(SideEffect.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Api.class);
        configuration.addAnnotatedClass(MedicationCategory.class);
        configuration.addAnnotatedClass(SideEffectMedication.class);
    }

}