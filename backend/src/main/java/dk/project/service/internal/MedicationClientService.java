package dk.project.service.internal;

import dk.project.dao.impl.MedicationClientDAO;
import dk.project.entity.MedicationClient;
import jakarta.persistence.EntityManager;
import java.util.List;

public class MedicationClientService {

    // Attributes
    private final MedicationClientDAO medicationClientDAO;

    // _________________________________________________________________________________________________________________

    public MedicationClientService(EntityManager em){
        this.medicationClientDAO = new MedicationClientDAO(em);
    }

    // _________________________________________________________________________________________________________________

    public void createMedicationClient(MedicationClient medicationClient){
        validateNotEmpty(medicationClient.getClient(), "MedicationClient.client");
        validateNotEmpty(medicationClient.getMedication(), "MedicationClient.medication");
        validateNotEmpty(medicationClient.getTimeline(), "MedicationClient.timeline");
        medicationClientDAO.create(medicationClient);
    }

    // _________________________________________________________________________________________________________________

    public void updateMedicationClient(MedicationClient medicationClient){
        validateNotEmpty(medicationClient.getClient(), "MedicationClient.client");
        validateNotEmpty(medicationClient.getMedication(), "MedicationClient.medication");
        validateNotEmpty(medicationClient.getTimeline(), "MedicationClient.timeline");
        medicationClientDAO.update(medicationClient);
    }

    // _________________________________________________________________________________________________________________

    public void deleteMedicationClient(int id){
        validateNotEmpty(id, "MedicationClient.id");
        medicationClientDAO.deleteById(id);
    }

    // _________________________________________________________________________________________________________________

    public void deleteAllMedicationClients(){
        medicationClientDAO.deleteAll();
    }

    // _________________________________________________________________________________________________________________

    public MedicationClient getMedicationClientById(int id){
        validateNotEmpty(id, "MedicationClient.id");
        return medicationClientDAO.getById(id);
    }

    // _________________________________________________________________________________________________________________

    public List<MedicationClient> getAllMedicationClients(){
        List<MedicationClient> list = medicationClientDAO.getAll();
        return list != null ? list : null;
    }

    // _________________________________________________________________________________________________________________

    public List<MedicationClient> findByClientId(String clientId){
        validateNotEmpty(clientId, "MedicationClient.clientId");
        List<MedicationClient> list = medicationClientDAO.findByClientId(clientId);
        return list != null ? list : null;
    }

    // _________________________________________________________________________________________________________________

    public List<MedicationClient> findByMedicationId(int medicationId){
        validateNotEmpty(medicationId, "MedicationClient.medicationId");
        List<MedicationClient> list = medicationClientDAO.findByMedicationId(medicationId);
        return list != null ? list : null;
    }

    // _________________________________________________________________________________________________________________

    public void updateAmount(MedicationClient mc, int amount){
        mc.setAmount(amount);
        updateMedicationClient(mc);
    }

    // _________________________________________________________________________________________________________________

    public void updateTimeline(MedicationClient mc, String timeline){
        validateNotEmpty(timeline, "MedicationClient.timeline");
        mc.setTimeline(timeline);
        updateMedicationClient(mc);
    }

    // _________________________________________________________________________________________________________________

    private void validateNotEmpty(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " må ikke være null");
        }
        if (value instanceof String text && text.isBlank()) {
            throw new IllegalArgumentException(fieldName + " kan ikke være tom");
        }
    }

}