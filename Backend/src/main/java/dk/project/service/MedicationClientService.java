package dk.project.service;

import dk.project.dao.MedicationClientDAO;
import dk.project.entity.MedicationClient;
import jakarta.persistence.EntityManager;
import java.util.List;

public class MedicationClientService {

    // Attributes
    private final MedicationClientDAO medicationClientDAO;

    // ________________________________________

    public MedicationClientService(EntityManager em){
        this.medicationClientDAO = new MedicationClientDAO(em);
    }

    // ________________________________________

    public void createMedicationClient(MedicationClient medicationClient){
        validateNotEmpty(medicationClient.getClient(), "MedicationClient.client");
        validateNotEmpty(medicationClient.getMedication(), "MedicationClient.medication");
        validateNotEmpty(medicationClient.getTimeline(), "MedicationClient.timeline");
        medicationClientDAO.createMedicationClient(medicationClient);
    }

    // ________________________________________

    public void updateMedicationClient(MedicationClient medicationClient){
        validateNotEmpty(medicationClient.getClient(), "MedicationClient.client");
        validateNotEmpty(medicationClient.getMedication(), "MedicationClient.medication");
        validateNotEmpty(medicationClient.getTimeline(), "MedicationClient.timeline");
        medicationClientDAO.updateMedicationClient(medicationClient);
    }

    // ________________________________________

    public void deleteMedicationClient(int id){
        validateNotEmpty(id, "MedicationClient.id");
        medicationClientDAO.deleteMedicationClient(id);
    }

    // ________________________________________

    public int deleteAllMedicationClients(){
        return medicationClientDAO.deleteAllMedicationClients();
    }

    // ________________________________________

    public MedicationClient getMedicationClientById(int id){
        validateNotEmpty(id, "MedicationClient.id");
        return medicationClientDAO.getMedicationClientById(id);
    }

    // ________________________________________

    public List<MedicationClient> getAllMedicationClients(){
        List<MedicationClient> list = medicationClientDAO.getAllMedicationClients();
        return list != null ? list : null;
    }

    // ________________________________________

    public List<MedicationClient> findByClientId(String clientId){
        validateNotEmpty(clientId, "MedicationClient.clientId");
        List<MedicationClient> list = medicationClientDAO.findByClientId(clientId);
        return list != null ? list : null;
    }

    public List<MedicationClient> findByMedicationId(int medicationId){
        validateNotEmpty(medicationId, "MedicationClient.medicationId");
        List<MedicationClient> list = medicationClientDAO.findByMedicationId(medicationId);
        return list != null ? list : null;
    }

    // ________________________________________

    public void updateAmount(MedicationClient mc, int amount){
        mc.setAmount(amount);
        updateMedicationClient(mc);
    }

    // ________________________________________

    public void updateTimeline(MedicationClient mc, String timeline){
        validateNotEmpty(timeline, "MedicationClient.timeline");
        mc.setTimeline(timeline);
        updateMedicationClient(mc);
    }

    // ________________________________________

    private void validateNotEmpty(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " må ikke være null");
        }
        if (value instanceof String text && text.isBlank()) {
            throw new IllegalArgumentException(fieldName + " kan ikke være tom");
        }
    }

}