package pe.isil.seguridad.model;

import javax.persistence.*;

@Entity
public class UserApplication {

    @EmbeddedId
    private UserApplicationKey id;

    @ManyToOne
    @MapsId("idApplication")
    @JoinColumn(name ="application_id")
    private Application application;

    @ManyToOne
    @MapsId("idUser")
    @JoinColumn(name ="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name ="permission_id")
    private Permission permission;
}
