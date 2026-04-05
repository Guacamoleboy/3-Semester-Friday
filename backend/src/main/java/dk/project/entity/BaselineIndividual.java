package dk.project.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "baseline_individuals",
uniqueConstraints = @UniqueConstraint(columnNames = {"baseline_id", "question_id"}))
public class BaselineIndividual {


    // _________________________________________________________________________________________________________________

    // Expected Column Layout in DB
    // __________________
    //
    //
    //              PgAmin
    //              _______
    //              id | baseline_id | question_id | value | notes | created_at
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
    @Column(name = "value")
    private byte value;
    @Column(name = "notes")
    private String note;
    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;

    // ______ | RELATIONS | ____________________________________________________________________________________________

    @ManyToOne
    @JoinColumn(name = "baseline_id", referencedColumnName = "id", nullable = false)
    private Baseline baseline;
    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id", nullable = false)
    private Question question;
    @ManyToMany
    @JoinTable(name = "baseline_individual_side_effects",
        joinColumns = @JoinColumn(name = "baseline_individual_id"),
        inverseJoinColumns = @JoinColumn(name = "side_effect_id"))
    private List<SideEffect> sideEffects = new ArrayList<>();

    // ______ | PERSIST LOGIC | ________________________________________________________________________________________

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
    }

    // ______ | NESTED COLUMNS | _______________________________________________________________________________________

    public static class Columns {
        public static final String ID = "id";
        public static final String BASELINE = "baseline";
        public static final String QUESTION = "question";
        public static final String VALUE = "value";
        public static final String NOTE = "notes";
        public static final String CREATED_AT = "created_at";
    }

}