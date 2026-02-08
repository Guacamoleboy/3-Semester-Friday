package dk.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "questions",
uniqueConstraints = @UniqueConstraint(columnNames = {"diagnosis_id", "medication_id", "title"}))
public class Question{

    // byte -> smallint in PostgreSQL (-128 | +127)
    // 1-6 value as default (instantiated)

    // Question based off of diagnosis. Medication can occur. Not needed.

    // @ManyToOne | Many (Question) | One (Diagnose)
    // @ManyToOne | Many (Question) | One (Medication)

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "question_title" ,nullable = false)
    private String questionTitle;

    @Column(name = "question_description" ,nullable = false)
    private String questionDescription;

    @Column(name = "value")
    private byte questionValue = 6;

    @ManyToOne
    @JoinColumn(name = "diagnosis_id")
    private Diagnose diagnosis;

    @ManyToOne
    @JoinColumn(name = "medication_id")
    private Medication medication;

}