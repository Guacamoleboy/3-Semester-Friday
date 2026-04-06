package dk.project.service.internal;

import dk.project.dao.impl.MedicationClientDAO;
import dk.project.entity.MedicationClient;
import jakarta.persistence.EntityManager;
import java.util.List;

public class MedicationClientService extends EntityManagerService<MedicationClient> {

    // Attributes
    private final MedicationClientDAO medicationClientDAO;

    // _________________________________________________________________________________________________________________

    public MedicationClientService(EntityManager em) {
        super(new MedicationClientDAO(em), MedicationClient.class);
        this.medicationClientDAO = (MedicationClientDAO) this.entityManagerDAO;
    }

    // _________________________________________________________________________________________________________________

    public List<MedicationClient> findByClientId(String clientId) {
        return medicationClientDAO.findByClientId(clientId);
    }

    // _________________________________________________________________________________________________________________

    public List<MedicationClient> findByMedicationId(int medicationId) {
        return medicationClientDAO.findByMedicationId(medicationId);
    }

    // _________________________________________________________________________________________________________________

    public boolean existsByClientId(String clientId, int id) {
        return medicationClientDAO.existByClientId(clientId, id);
    }

    // _________________________________________________________________________________________________________________

    public void updateAmount(MedicationClient medicationClient, int amount) {
        medicationClient.setAmount(amount);
        update(medicationClient);
    }

    // _________________________________________________________________________________________________________________

    public void updateTimeline(MedicationClient medicationClient, String timeline) {
        medicationClient.setTimeline(timeline);
        update(medicationClient);
    }

}