package citas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int idUsuario;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String password;
}
