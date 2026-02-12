package dk.project.service;

import dk.project.dao.DiagnoseClientDAO;
import dk.project.entity.DiagnoseClient;
import jakarta.persistence.EntityManager;
import java.util.List;

public class DiagnoseClientService {

    // Attributes
    private final DiagnoseClientDAO diagnoseClientDAO;

    // ________________________________________

    public DiagnoseClientService(EntityManager em){
        this.diagnoseClientDAO = new DiagnoseClientDAO(em);
    }

    // ________________________________________

    public void createDiagnoseClient(DiagnoseClient diagnoseClient){
        validateNotEmpty(diagnoseClient.getClient(), "DiagnoseClient.client");
        validateNotEmpty(diagnoseClient.getDiagnose(), "DiagnoseClient.diagnose");
        diagnoseClientDAO.create(diagnoseClient);
    }

    // ________________________________________

    public void updateDiagnoseClient(DiagnoseClient diagnoseClient){
        validateNotEmpty(diagnoseClient.getClient(), "DiagnoseClient.client");
        validateNotEmpty(diagnoseClient.getDiagnose(), "DiagnoseClient.diagnose");
        diagnoseClientDAO.update(diagnoseClient);
    }

    // ________________________________________

    public void deleteDiagnoseClient(Object id){
        validateNotEmpty(id, "DiagnoseClient.id");
        diagnoseClientDAO.deleteById(id);
    }

    // ________________________________________

    public void deleteAllDiagnoseClients(){
        diagnoseClientDAO.deleteAll();
    }

    // ________________________________________

    public DiagnoseClient getDiagnoseClientById(Object id){
        validateNotEmpty(id, "DiagnoseClient.id");
        return diagnoseClientDAO.getById(id);
    }

    // ________________________________________

    public List<DiagnoseClient> getAllDiagnoseClients(){
        List<DiagnoseClient> list = diagnoseClientDAO.getAll();
        return list != null ? list : null;
    }

    // ________________________________________

    public List<DiagnoseClient> findByClientId(String clientId){
        validateNotEmpty(clientId, "DiagnoseClient.clientId");
        List<DiagnoseClient> list = diagnoseClientDAO.findByClientId(clientId);
        return list != null ? list : null;
    }

    // ________________________________________

    public List<DiagnoseClient> findByDiagnoseId(int diagnoseId){
        validateNotEmpty(diagnoseId, "DiagnoseClient.diagnoseId");
        List<DiagnoseClient> list = diagnoseClientDAO.findByDiagnoseId(diagnoseId);
        return list != null ? list : null;
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