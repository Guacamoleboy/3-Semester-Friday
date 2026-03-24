package dk.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "questions",
uniqueConstraints = @UniqueConstraint(columnNames = {"diagnosis_id", "medication_id", "question_title"}))
public class Question {


    // _________________________________________________________________________________________________________________

    // Expected Column Layout in DB
    // __________________
    //
    //
    //              PgAmin
    //              _______
    //              id | question_title | question_description | value | diagnosis_id | medication_id
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
    @Column(name = "question_title" ,nullable = false)
    private String questionTitle;
    @Column(name = "question_description" ,nullable = false)
    private String questionDescription;
    @Column(name = "value")
    private byte questionValue = 6;

    // ______ | RELATIONS | ____________________________________________________________________________________________

    @ManyToOne
    @JoinColumn(name = "diagnosis_id")
    private Diagnose diagnosis;
    @ManyToOne
    @JoinColumn(name = "medication_id")
    private Medication medication;

    // ______ | NESTED COLUMNS | _______________________________________________________________________________________

    public static class Columns {
        public static final String ID = "id";
        public static final String QUESTION_TITLE = "question_title";
        public static final String QUESTION_DESCRIPTION = "question_description";
        public static final String VALUE = "value";
        public static final String DIAGNOSIS = "diagnosis";
        public static final String MEDICATION = "medication";
    }

}