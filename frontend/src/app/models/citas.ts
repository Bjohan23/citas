import { Medico } from "./medico";
import { Paciente } from "./paciente";
export class Citas {
    idCita: number;
    paciente: Paciente; // Relación con Pacientes
    medico: Medico; // Relación con Medicos
    fechaCita: Date; // LocalDate en Java corresponde a Date en TypeScript
    horaCita: string; // LocalTime se maneja como string en formato HH:mm:ss
    descripcion: string;
  
    constructor(
      idCita: number = 0,
      paciente: Paciente = new Paciente(),
      medico: Medico = new Medico(),
      fechaCita: Date = new Date(),
      horaCita: string = '00:00:00',
      descripcion: string = ''
    ) {
      this.idCita = idCita;
      this.paciente = paciente;
      this.medico = medico;
      this.fechaCita = fechaCita;
      this.horaCita = horaCita;
      this.descripcion = descripcion;
    }
  }
  
 