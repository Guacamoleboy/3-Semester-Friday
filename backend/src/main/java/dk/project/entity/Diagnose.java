package dk.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "diagnoses")
public class Diagnose {


    // _________________________________________________________________________________________________________________

    // Expected Column Layout in DB
    // __________________
    //
    //
    //              PgAmin
    //              _______
    //              id | name | description
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
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "description")
    private String description;

    // ______ | NESTED COLUMNS | _______________________________________________________________________________________

    public static class Columns {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
    }

}