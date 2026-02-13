package dk.project.config;

import dk.project.entity.*;
import org.hibernate.cfg.Configuration;

public class HibernateAnnotation {

    // Attributes

    // ______________________________________________________________________

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
    }

}