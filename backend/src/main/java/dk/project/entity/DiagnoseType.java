package dk.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "diagnose_types")
public class DiagnoseType {

    // Genoptræning (Fysioterapi)..
    // Medicin mod ADHD (Mentalt)..
    // Osv.

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

}