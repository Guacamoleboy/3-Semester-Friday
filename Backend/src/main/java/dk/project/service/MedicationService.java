package dk.project.service;

import dk.project.dao.MedicationDAO;
import jakarta.persistence.EntityManager;

public class MedicationService {

    // Attributes

    private final MedicationDAO medicationDAO;

    // _________________________________________________

    public MedicationService(EntityManager em){
        this.medicationDAO = new MedicationDAO(em);
    }

    // _________________________________________________



}