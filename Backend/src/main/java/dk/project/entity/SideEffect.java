package dk.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "side_effects")
public class SideEffect{

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "note", nullable = false)
    private String note;

    // ___________________________________________________________
    // Non DB | Bidirectional with BaselineIndividual

    @ManyToMany(mappedBy = "sideEffects")
    private List<BaselineIndividual> baselineIndividuals = new ArrayList<>();

}