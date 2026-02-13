package dk.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "medication_client",
uniqueConstraints = @UniqueConstraint(columnNames = {"client_id", "medication_id", "timeline"}))
public class MedicationClient {

    // @ManyToOne | Many (MedicationClient) | One (Medication)
    // @ManyToOne | Many (MedicationClient) | One (Client)
    // Timeline -> Days

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "medication_id", referencedColumnName = "id",nullable = false)
    private Medication medication;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id_hash", nullable = false)
    private Client client;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "timeline", nullable = false)
    private String timeline;

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @Column(name = "last_updated")
    private Timestamp lastUpdated;

    // __________________________________________________________
    // First Commit

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
        lastUpdated = createdAt;
    }

    // __________________________________________________________
    // Update

    @PreUpdate
    protected void onUpdate() {
        lastUpdated = new Timestamp(System.currentTimeMillis());
    }

}