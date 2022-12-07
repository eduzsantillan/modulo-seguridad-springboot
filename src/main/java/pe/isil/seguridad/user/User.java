package pe.isil.seguridad.user;

import lombok.Data;
import pe.isil.seguridad.app.Application;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tbl_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email", name="u_email_u")
})
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastname;

    @Column(name="email", length = 100)
    private String email;

    private String urlPhoto;

    @ManyToMany(mappedBy = "users")
    private Set<Application> applications = new HashSet<>();


}
