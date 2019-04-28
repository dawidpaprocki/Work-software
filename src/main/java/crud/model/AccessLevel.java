package crud.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AccessLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "RoleId", nullable = false, columnDefinition = "int default 0")
    private Role role;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "UserId", nullable = false, columnDefinition = "int default 0")
    private User user;
}
