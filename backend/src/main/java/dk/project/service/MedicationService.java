package dk.project.service;

import dk.project.dao.MedicationDAO;
import dk.project.entity.Medication;
import jakarta.persistence.EntityManager;

public class MedicationService extends EntityManagerService<Medication> {

    // Attributes
    private final MedicationDAO medicationDAO;

    // _________________________________________________

    public MedicationService(EntityManager em) {
        super(new MedicationDAO(em), Medication.class);
        this.medicationDAO = (MedicationDAO) this.entityManagerDAO;
    }

    // _________________________________________________

    public String getNameById(int id) {
        return getColumnById(id, "name");
    }

    // _________________________________________________

    public String getDescriptionById(int id) {
        return getColumnById(id, "description");
    }

    // _________________________________________________

    public boolean existsByName(String name) {
        validateNotEmpty(name, classSpecific.getSimpleName() + ".name");
        return medicationDAO.existsByName(name);
    }

    // _________________________________________________

    public Medication findByName(String name) {
        validateNotEmpty(name, classSpecific.getSimpleName() + ".name");
        return medicationDAO.findByName(name);
    }

}