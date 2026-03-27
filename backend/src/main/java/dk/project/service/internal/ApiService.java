package dk.project.service.internal;

import dk.project.dao.impl.ApiDAO;
import dk.project.entity.api.Api;
import dk.project.util.BCryptHash;
import dk.project.util.KeyGenerator;
import jakarta.persistence.EntityManager;
import java.sql.Timestamp;

public class ApiService extends EntityManagerService<Api> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public ApiService(EntityManager em) {
        super(new ApiDAO(em), Api.class);
    }

    // _________________________________________________________________________________________________________________

    public String createApiKey(String name) {
        String rawKey = KeyGenerator.generate64Key();
        String hashedKey = BCryptHash.hash(rawKey);
        Api api = Api.builder()
                .name(name)
                .keyHash(hashedKey)
                .active(true)
                .build();
        entityManagerDAO.create(api);
        return rawKey;
    }

    // _________________________________________________________________________________________________________________
    // Implement cache to prevent multi search

    public boolean validateKey(String apiKey) {
        for (Api api : entityManagerDAO.getAll()) {
            if (!api.isActive()) {
                continue;
            }
            if (BCryptHash.check(apiKey, api.getKeyHash())) {
                api.setLastUsed(new Timestamp(System.currentTimeMillis()));
                entityManagerDAO.update(api);
                return true;
            }
        }
        return false;
    }

    // _________________________________________________________________________________________________________________

    public String refreshKey(String apiKey) {
        Api api = findEntityByColumn(apiKey, Api.Columns.KEY);
        if (api == null || !api.isActive()) {
            return null;
        }
        String newRawKey = KeyGenerator.generate64Key();
        api.setKeyHash(BCryptHash.hash(newRawKey));
        api.setLastUsed(new Timestamp(System.currentTimeMillis()));
        update(api);
        return newRawKey;
    }

    // _________________________________________________________________________________________________________________

    public boolean deleteKey(String apiKey) {
        Api api = findEntityByColumn(apiKey, Api.Columns.KEY);
        if (api == null) return false;
        api.setActive(false);
        update(api);
        return true;
    }

}