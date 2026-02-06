package dk.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "baseline_individual",
uniqueConstraints = @UniqueConstraint(columnNames = {"baseline_id", "question_id"}))
public class BaselineIndividual {

    // @ManyToOne | Many (BaselineIndividual) | One (Baseline)
    // @OneToOne | One (BaselineIndividual) | One (Question)
    // @ManyToOne | Many (BaselineIndividual) | Many (SideEffect)
    // Notes as optional

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "baseline_id", referencedColumnName = "id", nullable = false)
    private Baseline baseline;

    @OneToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id", nullable = false)
    private Question question;

    @ManyToMany
    @JoinTable(
    name = "baseline_individual_side_effects",
    joinColumns = @JoinColumn(name = "baseline_individual_id"),
    inverseJoinColumns = @JoinColumn(name = "side_effect_id"))
    private List<SideEffect> sideEffects = new ArrayList<>();

    @Column(name = "value")
    private byte value;

    @Column(name = "notes")
    private String note;

    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;

    // __________________________________________________________
    // First Commit

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
    }

}