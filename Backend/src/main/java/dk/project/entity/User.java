package dk.project.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    // Using UUID for protection of DATA + GDPR compliant.
    // Using hash on email and password for safety.
    // @ManyToOne | Many (users) | One (role)

    @Id
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id = UUID.randomUUID();

    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "email_hash", nullable = false)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id",nullable = false)
    private Role role;

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @Column(name = "last_login")
    private Timestamp lastLogin;

    // __________________________________________________________
    // First Commit

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
        lastLogin = createdAt;
    }

    // __________________________________________________________
    // Update

    @PreUpdate
    protected void onUpdate() {
        lastLogin = new Timestamp(System.currentTimeMillis());
    }

}