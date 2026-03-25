package dk.project.service;

import dk.project.dao.impl.MedicationDAO;
import dk.project.entity.Medication;
import jakarta.persistence.EntityManager;

public class MedicationService extends EntityManagerService<Medication> {

    // Attributes
    private final MedicationDAO medicationDAO;

    // _________________________________________________________________________________________________________________

    public MedicationService(EntityManager em) {
        super(new MedicationDAO(em), Medication.class);
        this.medicationDAO = (MedicationDAO) this.entityManagerDAO;
    }

    // _________________________________________________________________________________________________________________

    public String getNameById(int id) {
        return getColumnById(id, "name");
    }

    // _________________________________________________________________________________________________________________

    public String getDescriptionById(int id) {
        return getColumnById(id, "description");
    }

    // _________________________________________________________________________________________________________________

    public boolean existsByName(String name) {
        validateNotEmpty(name, classSpecific.getSimpleName() + ".name");
        return medicationDAO.existsByName(name);
    }

    // _________________________________________________________________________________________________________________

    public Medication findByName(String name) {
        validateNotEmpty(name, classSpecific.getSimpleName() + ".name");
        return medicationDAO.findByName(name);
    }

}