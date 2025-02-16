package iuh.fit.com.exercise_2.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "user_id")
    private Long id;
    @Column(columnDefinition = "varchar(45)", unique = true, nullable = false)
    private String username;
    @Column(columnDefinition = "varchar(45)", nullable = false)
    private String password;
    @Column(columnDefinition = "varchar(45)", unique = true, nullable = false)
    private String email;
    @ManyToMany
    @JoinTable(
            name = "users_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    @ToString.Include
    private Set<Group> groups;
}
