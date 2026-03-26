package dk.project.service.internal;

import dk.project.dao.impl.ApiDAO;
import dk.project.entity.api.Api;
import jakarta.persistence.EntityManager;

public class ApiService extends EntityManagerService<Api> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public ApiService(EntityManager em) {
        super(new ApiDAO(em), Api.class);
    }

    // _________________________________________________________________________________________________________________



}