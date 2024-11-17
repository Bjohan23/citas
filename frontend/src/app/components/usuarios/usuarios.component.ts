import { Component } from '@angular/core';
import { usuario } from '../../models/usuario';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { UsuariosService } from '../../services/usuarios.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap'; // Importar NgbModal

@Component({
  selector: 'app-usuarios',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css'],
})
export class UsuariosComponent {
  usuarios: usuario[] = [];
  selectedUser: usuario = new usuario(); // Para almacenar el usuario seleccionado
  searchTerm: string = '';
  isEditing: boolean = false; // Para saber si estamos editando o registrando un usuario
  showPassword: boolean[] = []; 
  constructor(private usuarioService: UsuariosService, private modalService: NgbModal) {}

  ngOnInit(): void {
    this.loadUsuarios();
  }
  // Alternar visibilidad de la contraseña
  togglePasswordVisibility(index: number): void {
    this.showPassword[index] = !this.showPassword[index];
  }
  loadUsuarios(): void {
    this.usuarioService.findAll().subscribe({
      next: (data) => {
        this.usuarios = data.body;
        this.showPassword = new Array(this.usuarios.length).fill(false);
      },
      error: (err) => {
        console.error('Error al cargar usuarios', err);
      },
    });
  }

  filteredUsers(): usuario[] {
    if (!this.searchTerm.trim()) return this.usuarios;
    return this.usuarios.filter((user) =>
      user.username.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  // Llamado cuando se hace clic en "Registrar Nuevo Usuario"
  onRegister(): void {
    this.selectedUser = new usuario();  // Resetear el formulario
    this.isEditing = false;  // Asegurarnos de que es modo "registro"
    this.modalService.open('#editModal', { size: 'lg' }); // Abrir el modal
  }

  // Llamado cuando se hace clic en "Editar"
  onEditUser(user: usuario): void {
    this.selectedUser = { ...user };  // Copiar los datos del usuario seleccionado
    this.isEditing = true;  // Establecer que estamos en modo "edición"
    this.modalService.open('#editModal', { size: 'lg' }); // Abrir el modal
  }

  // Llamado cuando se guarda el formulario
  onSaveChanges(): void {
    if (this.isEditing) {
      // Lógica para actualizar usuario
      this.usuarioService.update(this.selectedUser.idUsuario, this.selectedUser).subscribe({
        next: () => {
          this.loadUsuarios();  // Recargar la lista de usuarios
          this.modalService.dismissAll(); // Cerrar el modal
        },
        error: (err) => console.error('Error al actualizar usuario', err),
      });
    } else {
      // Lógica para registrar nuevo usuario
      this.usuarioService.create(this.selectedUser).subscribe({
        next: () => {
          this.loadUsuarios();  // Recargar la lista de usuarios
          this.modalService.dismissAll(); // Cerrar el modal
        },
        error: (err) => console.error('Error al registrar usuario', err),
      });
    }
    this.selectedUser = new usuario(); 
  }

  // Llamado cuando se hace clic en "Eliminar"
  onDeleteUser(id: number): void {
    if (confirm('¿Estás seguro de eliminar este usuario?')) {
      this.usuarioService.delete(id).subscribe({
        next: () => {
          this.loadUsuarios();  // Recargar la lista de usuarios
        },
        error: (err) => console.error('Error al eliminar usuario', err),
      });
    }
  }
}
