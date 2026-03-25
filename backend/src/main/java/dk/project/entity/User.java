package dk.project.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {


    // _________________________________________________________________________________________________________________

    // Expected Column Layout in DB
    // __________________
    //
    //
    //              PgAmin
    //              _______
    //              id | username | email_hash | password_hash | role_id | created_at | last_login
    //
    // __________________
    // Tested: YES
    // Date: 24/03-2026

    // _________________________________________________________________________________________________________________


    // ______ | COLUMNS | ______________________________________________________________________________________________

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(name = "email_hash", nullable = false)
    private String email;
    @Column(name = "password_hash", nullable = false)
    private String password;
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;
    @Column(name = "last_login")
    private Timestamp lastLogin;

    // ______ | RELATIONS | ____________________________________________________________________________________________

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

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

    // ______ | NESTED COLUMNS | _______________________________________________________________________________________

    public static class Columns {
        public static final String ID = "id";
        public static final String USERNAME = "username";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String CREATED_AT = "created_at";
        public static final String LAST_LOGIN = "last_login";
    }

}