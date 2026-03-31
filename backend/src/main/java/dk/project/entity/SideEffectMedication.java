package dk.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "side_effect_medications")
public class SideEffectMedication {


    // _________________________________________________________________________________________________________________

    // Expected Column Layout in DB
    // __________________
    //
    //
    //              PgAmin
    //              _______
    //              id | name | description | header | medication_category
    //
    // __________________
    // Tested: NO
    // Date: N/A

    // _________________________________________________________________________________________________________________


    // ______ | COLUMNS | ______________________________________________________________________________________________

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "header", nullable = false)
    private int header;
    @ManyToOne
    @JoinColumn(name = "medication_category", referencedColumnName = "id")
    private MedicationCategory medicationCategory;

    // ______ | NESTED FIELDS | ________________________________________________________________________________________

    public static class Fields {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String HEADER = "header";
        public static final String MEDICATION_CATEGORY = "medicationCategory";
    }

}
