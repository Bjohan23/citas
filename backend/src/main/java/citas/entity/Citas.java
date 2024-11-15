package citas.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Citas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Citas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private int idCita;

    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false, foreignKey = @ForeignKey(name = "fk_citas_paciente"))
    private Pacientes paciente;

    @ManyToOne
    @JoinColumn(name = "id_medico", nullable = false, foreignKey = @ForeignKey(name = "fk_citas_medico"))
    private Medicos medico;

    @Column(name = "fecha_cita", nullable = false)
    private LocalDate fechaCita;

    @Column(name = "hora_cita", nullable = false)
    private LocalTime horaCita;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;
}
