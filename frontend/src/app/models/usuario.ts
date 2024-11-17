export class usuario {
    idUsuario: number;
    username: string;
    password: string;
  
    constructor(
      idUsuario: number = 0,
      username: string = '',
      password: string = ''
    ) {
      this.idUsuario = idUsuario;
      this.username = username;
      this.password = password;
    }
  }
  