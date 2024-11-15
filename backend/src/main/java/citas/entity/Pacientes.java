package citas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Pacientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pacientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private int idPaciente;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "email", length = 100)
    private String email;
}
