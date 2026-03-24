package dk.project.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medication_client",
uniqueConstraints = @UniqueConstraint(columnNames = {"client_id", "medication_id", "timeline"}))
public class MedicationClient {

    // _________________________________________________________________________________________________________________

    // Expected Column Layout in DB
    // __________________
    //
    //
    //              PgAmin
    //              _______
    //              id | medication_id | client_id | amount | timeline | created_at | last_updated
    //
    // __________________
    // Tested: YES
    // Date: 24/03-2026

    // _________________________________________________________________________________________________________________


    // ______ | COLUMNS | ______________________________________________________________________________________________

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "amount", nullable = false)
    private int amount;
    @Column(name = "timeline", nullable = false)
    private String timeline;
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;
    @Column(name = "last_updated")
    private Timestamp lastUpdated;

    // ______ | RELATIONS | ____________________________________________________________________________________________

    @ManyToOne
    @JoinColumn(name = "medication_id", referencedColumnName = "id",nullable = false)
    private Medication medication;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id_hash", nullable = false)
    private Client client;

    // ______ | PERSIST LOGIC | ________________________________________________________________________________________

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
        lastUpdated = createdAt;
    }
    @PreUpdate
    protected void onUpdate() {
        lastUpdated = new Timestamp(System.currentTimeMillis());
    }

    // ______ | NESTED COLUMNS | _______________________________________________________________________________________

    public static class Columns {
        public static final String ID = "id";
        public static final String MEDICATION = "medication";
        public static final String CLIENT = "client";
        public static final String AMOUNT = "amount";
        public static final String TIMELINE = "timeline";
        public static final String CREATED_AT = "created_at";
        public static final String LAST_UPDATED = "last_updated";
    }

}