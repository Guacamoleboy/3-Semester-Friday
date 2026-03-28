package dk.project.service.internal;

import dk.project.dao.impl.ApiDAO;
import dk.project.dto.response.ApiResponseDTO;
import dk.project.entity.api.Api;
import dk.project.exception.ApiException;
import dk.project.mapper.response.ApiResponseMapper;
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

    public ApiResponseDTO createApiKey(String name) {
        String firstId = KeyGenerator.generate16Key();
        String lastId = KeyGenerator.generate64Key();
        Api api = Api.builder()
                .name(name)
                .keyId(firstId)
                .keyHash(BCryptHash.hash(lastId))
                .active(true)
                .build();
        create(api);
        return ApiResponseMapper.toCreateDto(api, lastId);
    }

    // _________________________________________________________________________________________________________________

    public ApiResponseDTO getApiMeta(String keyId) {
        Api api = findEntityByColumn(keyId, Api.Fields.KEY_ID);
        if (api == null || !api.isActive()) {
            throw new ApiException(404, "API not found or inactive");
        }
        return ApiResponseMapper.toDto(api);
    }

    // _________________________________________________________________________________________________________________

    public boolean validateKey(String apiKey, String keyId) {
        Api api = findEntityByColumn(keyId, Api.Fields.KEY_ID);
        if (api == null || !api.isActive()) return false;
        return BCryptHash.check(apiKey, api.getKeyHash());
    }

    // _________________________________________________________________________________________________________________

    public String refreshKey(String keyId, String rawApiKey) {
        Api api = findEntityByColumn(keyId, Api.Fields.KEY_ID);
        if (api == null || !api.isActive() || !BCryptHash.check(rawApiKey, api.getKeyHash())) {
            return null;
        }
        String rawNewApiKey = KeyGenerator.generate64Key();
        api.setKeyHash(BCryptHash.hash(rawNewApiKey));
        api.setLastUsed(new Timestamp(System.currentTimeMillis()));
        update(api);
        return rawNewApiKey;
    }

    // _________________________________________________________________________________________________________________

    public boolean deleteKey(String keyId, String rawApiKey) {
        Api api = findEntityByColumn(keyId, Api.Fields.KEY_ID);
        if (api == null || !BCryptHash.check(rawApiKey, api.getKeyHash())) {
            return false;
        }
        delete(api);
        return true;
    }

}