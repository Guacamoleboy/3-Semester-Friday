package dk.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "diagnose_types")
public class DiagnoseType {


    // _________________________________________________________________________________________________________________

    // Expected Column Layout in DB
    // __________________
    //
    //
    //              PgAmin
    //              _______
    //              id | name
    //
    // __________________
    // Tested: YES
    // Date: 24/03-2026

    // _________________________________________________________________________________________________________________


    // ______ | COLUMNS | ______________________________________________________________________________________________

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String name;

    // ______ | NESTED COLUMNS | _______________________________________________________________________________________

    public static class Columns {
        public static final String ID = "id";
        public static final String NAME = "name";
    }

}