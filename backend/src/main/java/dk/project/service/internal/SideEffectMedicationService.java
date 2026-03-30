package dk.project.service.internal;

import dk.project.dao.impl.SideEffectMedicationDAO;
import dk.project.entity.SideEffectMedication;
import jakarta.persistence.EntityManager;

public class SideEffectMedicationService extends EntityManagerService<SideEffectMedication> {

    // Attributes
    private final SideEffectMedicationDAO sideEffectMedicationDAO;

    // _________________________________________________________________________________________________________________

    public SideEffectMedicationService(EntityManager em) {
        super(new SideEffectMedicationDAO(em), SideEffectMedication.class);
        this.sideEffectMedicationDAO = (SideEffectMedicationDAO) this.entityManagerDAO;
    }

}