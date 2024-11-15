package citas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Medicos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Medicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico")
    private int idMedico;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "especialidad", length = 50)
    private String especialidad;

    @Column(name = "telefono", length = 15)
    private String telefono;
}
