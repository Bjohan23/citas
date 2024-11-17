import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  usuario: string = '';
  password: string = '';
  rememberMe: boolean = false;
  errorMessage: string = ''; 
  constructor(private login: LoginService, private router: Router) { }

  onSubmit() {
    this.errorMessage = '';  // Limpiar cualquier mensaje de error anterior

    // Llamar al servicio de login
    this.login.login(this.usuario).subscribe(
      (response) => {
        if(response.body.password == this.password){
          this.router.navigate(['/dashboard']);
        }else{
          this.errorMessage = 'Contraseña incorrecta o usuario no encontrado';
        }
        console.log('Login exitoso', response);
      },
      (error) => {
        // Si ocurre un error, mostramos el mensaje
        this.errorMessage = 'Contraseña incorrecta o usuario no encontrado';
        console.log('Error en login', error);
      }
    );

  }
}
