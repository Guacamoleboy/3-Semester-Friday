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
@Table(name = "baseline")
public class Baseline {

    // @ManyToOne | Many (Baseline) | One (Client)
    // @ManyToOne | Many (Baseline) | One (Diagnose)

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id_hash", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "diagnose_id", referencedColumnName = "id", nullable = false)
    private Diagnose diagnose;

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @Column(name = "end_date", nullable = false)
    private Timestamp endDate;

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