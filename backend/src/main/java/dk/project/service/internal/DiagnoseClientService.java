package dk.project.service.internal;

import dk.project.dao.impl.DiagnoseClientDAO;
import dk.project.entity.DiagnoseClient;
import jakarta.persistence.EntityManager;
import java.util.List;

public class DiagnoseClientService {

    // Attributes
    private final DiagnoseClientDAO diagnoseClientDAO;

    // _________________________________________________________________________________________________________________

    public DiagnoseClientService(EntityManager em){
        this.diagnoseClientDAO = new DiagnoseClientDAO(em);
    }

    // _________________________________________________________________________________________________________________

    public void createDiagnoseClient(DiagnoseClient diagnoseClient){
        validateNotEmpty(diagnoseClient.getClient(), "DiagnoseClient.client");
        validateNotEmpty(diagnoseClient.getDiagnose(), "DiagnoseClient.diagnose");
        diagnoseClientDAO.create(diagnoseClient);
    }

    // _________________________________________________________________________________________________________________

    public void updateDiagnoseClient(DiagnoseClient diagnoseClient){
        validateNotEmpty(diagnoseClient.getClient(), "DiagnoseClient.client");
        validateNotEmpty(diagnoseClient.getDiagnose(), "DiagnoseClient.diagnose");
        diagnoseClientDAO.update(diagnoseClient);
    }

    // _________________________________________________________________________________________________________________

    public void deleteDiagnoseClient(Object id){
        validateNotEmpty(id, "DiagnoseClient.id");
        diagnoseClientDAO.deleteById(id);
    }

    // _________________________________________________________________________________________________________________

    public void deleteAllDiagnoseClients(){
        diagnoseClientDAO.deleteAll();
    }

    // _________________________________________________________________________________________________________________

    public DiagnoseClient getDiagnoseClientById(Object id){
        validateNotEmpty(id, "DiagnoseClient.id");
        return diagnoseClientDAO.getById(id);
    }

    // _________________________________________________________________________________________________________________

    public List<DiagnoseClient> getAllDiagnoseClients(){
        List<DiagnoseClient> list = diagnoseClientDAO.getAll();
        return list != null ? list : null;
    }

    // _________________________________________________________________________________________________________________

    public List<DiagnoseClient> findByClientId(String clientId){
        validateNotEmpty(clientId, "DiagnoseClient.clientId");
        List<DiagnoseClient> list = diagnoseClientDAO.findByClientId(clientId);
        return list != null ? list : null;
    }

    // _________________________________________________________________________________________________________________

    public List<DiagnoseClient> findByDiagnoseId(int diagnoseId){
        validateNotEmpty(diagnoseId, "DiagnoseClient.diagnoseId");
        List<DiagnoseClient> list = diagnoseClientDAO.findByDiagnoseId(diagnoseId);
        return list != null ? list : null;
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