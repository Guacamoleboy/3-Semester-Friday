package dk.project.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "baselines")
public class Baseline {


    // _________________________________________________________________________________________________________________

    // Expected Column Layout in DB
    // __________________
    //
    //
    //              PgAmin
    //              _______
    //              id | client_id | diagnose_id | created_at | end_date | last_updated
    //
    // __________________
    // Tested: YES
    // Date: 24/03-2026

    // _________________________________________________________________________________________________________________


    // ______ | COLUMNS | ______________________________________________________________________________________________

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;
    @Column(name = "end_date", nullable = false)
    private Timestamp endDate;
    @Column(name = "last_updated")
    private Timestamp lastUpdated;

    // ______ | RELATIONS | ____________________________________________________________________________________________

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id_hash", nullable = false)
    private Client client;
    @ManyToOne
    @JoinColumn(name = "diagnose_id", referencedColumnName = "id", nullable = false)
    private Diagnose diagnose;
    @OneToMany(mappedBy = "baseline", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<BaselineIndividual> baselineIndividuals = new ArrayList<>();

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

    // ______ | NESTED FIELDS | ________________________________________________________________________________________

    public static class Fields {
        public static final String ID = "id";
        public static final String CLIENT = "client";
        public static final String DIAGNOSE = "diagnose";
        public static final String CREATED_AT = "created_at";
        public static final String END_DATE = "end_date";
        public static final String LAST_UPDATED = "last_updated";
    }

}