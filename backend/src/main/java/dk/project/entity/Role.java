package dk.project.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Table(name = "roles")
public class Role {


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
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Column(name = "description")
    private String description;

    // ______ | RELATIONS | ____________________________________________________________________________________________

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users = new ArrayList<>();

    // ______ | NESTED COLUMNS | _______________________________________________________________________________________

    public static class Columns {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
    }

}


