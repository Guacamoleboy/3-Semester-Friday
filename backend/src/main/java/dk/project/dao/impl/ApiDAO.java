package dk.project.dao.impl;

import dk.project.entity.api.Api;
import jakarta.persistence.EntityManager;

public class ApiDAO extends EntityManagerDAO<Api> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public ApiDAO(EntityManager em) {
        super(em, Api.class);
    }

}