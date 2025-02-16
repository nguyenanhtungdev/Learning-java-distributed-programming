package iuh.fit.com.exercise_2.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "groups")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    @EqualsAndHashCode.Include
    private Long id;
    @Column(columnDefinition = "varchar(45)", unique = true, nullable = false)
    private String name;
    @ManyToMany(mappedBy = "groups")
    @ToString.Include
    private Set<User> users;
}
