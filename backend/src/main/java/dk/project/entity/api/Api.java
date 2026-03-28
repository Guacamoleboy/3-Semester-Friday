package dk.project.entity.api;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "apis")
public class Api {

    // _________________________________________________________________________________________________________________

    // Expected Column Layout in DB
    // __________________
    //
    //
    //              PgAmin
    //              _______
    //              id | name | key_hash | key_id | created_at | active
    //
    // __________________
    // Tested: YES
    // Date: 28/03-2026

    // _________________________________________________________________________________________________________________


    // ______ | COLUMNS | ______________________________________________________________________________________________

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "key_hash", nullable = false, unique = true)
    private String keyHash;
    @Column(name = "key_id", nullable = false, unique = true)
    private String keyId;
    @Column(name = "active", nullable = false)
    private boolean active;
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;
    @Column(name = "last_used")
    private Timestamp lastUsed;

    // ______ | PERSIST LOGIC | ________________________________________________________________________________________

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
        active = true;
    }

    // ______ | NESTED FIELDS | ________________________________________________________________________________________

    public static class Fields {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String KEY_HASH = "keyHash";
        public static final String KEY_ID = "keyId";
        public static final String ACTIVE = "active";
        public static final String CREATED_AT = "createdAt";
    }

}