package dk.project.service;

import dk.project.dao.MedicationDAO;
import dk.project.entity.Medication;
import jakarta.persistence.EntityManager;
import java.util.List;

public class MedicationService {

    // Attributes
    private final MedicationDAO medicationDAO;

    // _________________________________________________

    public MedicationService(EntityManager em){
        this.medicationDAO = new MedicationDAO(em);
    }

    // _________________________________________________

    public void createMedication(Medication medication){
        validateNotEmpty(medication.getName(), "Medication.name");
        medicationDAO.createMedication(medication);
    }

    // _________________________________________________

    public void updateMedication(Medication medication){
        validateNotEmpty(medication.getName(), "Medication.name");
        medicationDAO.updateMedication(medication);
    }

    // _________________________________________________

    public void deleteMedication(int id){
        validateNotEmpty(id, "Medication.id");
        medicationDAO.deleteMedication(id);
    }

    // _________________________________________________

    public int deleteAllMedications(){
        return medicationDAO.deleteAllMedications();
    }

    // _________________________________________________

    public Medication getMedicationById(int id){
        validateNotEmpty(id, "Medication.id");
        return medicationDAO.getMedicationById(id);
    }

    // _________________________________________________

    public String getNameById(int id){
        validateNotEmpty(id, "Medication.id");
        return medicationDAO.getNameById(id);
    }

    // _________________________________________________

    public String getDescriptionById(int id){
        validateNotEmpty(id, "Medication.id");
        return medicationDAO.getDescriptionById(id);
    }

    // _________________________________________________

    public List<Medication> getAllMedications(){
        List<Medication> medicationList = medicationDAO.getAllMedications();
        return medicationList;
    }

    // _________________________________________________

    public boolean existsByName(String name){
        validateNotEmpty(name, "Medication.name");
        return medicationDAO.existsByName(name);
    }

    // _________________________________________________

    public Medication findByName(String name){
        validateNotEmpty(name, "Medication.name");
        return medicationDAO.findByName(name);
    }

    // _________________________________________________

    private void validateNotEmpty(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " må ikke være null");
        }
        if (value instanceof String text && text.isBlank()) {
            throw new IllegalArgumentException(fieldName + " kan ikke være tom");
        }
    }

}