export class Medico {
    idMedico: number;
    nombre: string;
    especialidad: string;
    telefono: string;
  
    constructor(
      idMedico: number = 0,
      nombre: string = '',
      especialidad: string = '',
      telefono: string = ''
    ) {
      this.idMedico = idMedico;
      this.nombre = nombre;
      this.especialidad = especialidad;
      this.telefono = telefono;
    }
  }
  