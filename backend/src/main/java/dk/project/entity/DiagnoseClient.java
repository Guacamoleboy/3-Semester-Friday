package dk.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "diagnose_client",
uniqueConstraints = @UniqueConstraint(columnNames = {"client_id", "diagnose_id"}))
public class DiagnoseClient {

    // @ManyToOne | Many (DiagnoseClient) | One (Diagnose)
    // @ManyToOne | Many (DiagnoseClient) | One (Client)

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "diagnose_id", referencedColumnName = "id",nullable = false)
    private Diagnose diagnose;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id_hash", nullable = false)
    private Client client;

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
