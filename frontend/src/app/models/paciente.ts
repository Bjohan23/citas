export class Paciente {
    idPaciente: number;
    nombre: string;
    telefono: string;
    email: string;
  
    constructor(
      idPaciente: number = 0,
      nombre: string = '',
      telefono: string = '',
      email: string = ''
    ) {
      this.idPaciente = idPaciente;
      this.nombre = nombre;
      this.telefono = telefono;
      this.email = email;
    }
  }
  