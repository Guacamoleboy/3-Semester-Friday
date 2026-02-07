package dk.project.service;

import dk.project.dao.QuestionDAO;
import jakarta.persistence.EntityManager;

public class QuestionService {

    // Attributes

    private final QuestionDAO questionDAO;

    // _________________________________________________

    public QuestionService(EntityManager em){
        this.questionDAO = new QuestionDAO(em);
    }

    // _________________________________________________



}