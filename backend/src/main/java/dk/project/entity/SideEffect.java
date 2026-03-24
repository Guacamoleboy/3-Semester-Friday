package dk.project.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "side_effects")
public class SideEffect{


    // _________________________________________________________________________________________________________________

    // Expected Column Layout in DB
    // __________________
    //
    //
    //              PgAmin
    //              _______
    //              id | note
    //
    // __________________
    // Tested: YES
    // Date: 24/03-2026

    // _________________________________________________________________________________________________________________


    // ______ | COLUMNS | ______________________________________________________________________________________________

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "note", nullable = false)
    private String note;


    // ______ | RELATIONS | ____________________________________________________________________________________________

    @ManyToMany(mappedBy = "sideEffects")
    private List<BaselineIndividual> baselineIndividuals = new ArrayList<>();

    // ______ | NESTED COLUMNS | _______________________________________________________________________________________

    public static class Columns {
        public static final String ID = "id";
        public static final String NOTE = "note";
    }

}