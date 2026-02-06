package dk.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "clients")
public class Client {

    // id_hash is the users CPR Number
    // id_ending is the last 4 in the CPR number. Used for schema access later on.

    // Attributes
    @Id
    @Column(name = "id_hash", nullable = false, unique = true)
    private String id;

    @Column(name = "id_ending", nullable = false)
    private int idEnding;

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