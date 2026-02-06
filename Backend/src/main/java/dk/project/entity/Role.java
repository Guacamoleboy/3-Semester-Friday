package dk.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "roles")
public class Role {

    // Attributes
    // UUID for safety (access giving role)
    @Id
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id = UUID.randomUUID();

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

}