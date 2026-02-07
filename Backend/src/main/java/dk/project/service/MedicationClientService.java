package dk.project.service;

import dk.project.dao.MedicationClientDAO;
import jakarta.persistence.EntityManager;

public class MedicationClientService {

    // Attributes

    private final MedicationClientDAO medicationClientDAO;

    // _________________________________________________

    public MedicationClientService(EntityManager em){
        this.medicationClientDAO = new MedicationClientDAO(em);
    }

    // _________________________________________________



}