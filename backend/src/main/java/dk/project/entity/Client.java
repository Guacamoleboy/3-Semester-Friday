package dk.project.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clients")
public class Client {


    // _________________________________________________________________________________________________________________

    // Expected Column Layout in DB
    // __________________
    //
    //
    //              PgAmin
    //              _______
    //              id_hash | id_ending | created_at | last_login
    //
    // __________________
    // Tested: YES
    // Date: 24/03-2026

    // _________________________________________________________________________________________________________________


    // ______ | COLUMNS | ______________________________________________________________________________________________

    @Id
    @Column(name = "id_hash", nullable = false, unique = true)
    private String id;
    @Column(name = "id_ending", nullable = false)
    private Integer idEnding;
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;
    @Column(name = "last_login")
    private Timestamp lastLogin;

    // ______ | PERSIST LOGIC | ________________________________________________________________________________________

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
        lastLogin = createdAt;
    }
    @PreUpdate
    protected void onUpdate() {
        lastLogin = new Timestamp(System.currentTimeMillis());
    }

    // ______ | NESTED FIELDS | ________________________________________________________________________________________

    public static class Fields {
        public static final String ID_HASH = "id_hash";
        public static final String ID_ENDING = "id_ending";
        public static final String CREATED_AT = "created_at";
        public static final String LAST_LOGIN = "last_login";
    }

}