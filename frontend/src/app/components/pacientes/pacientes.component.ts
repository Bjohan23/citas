import { Component } from '@angular/core';
import { Paciente } from '../../models/paciente';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { PacientesService } from '../../services/pacientes.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-pacientes',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './pacientes.component.html',
  styleUrls: ['./pacientes.component.css'],
})
export class PacientesComponent {
  pacientes: Paciente[] = [];
  selectedPaciente: Paciente = new Paciente(); // Para almacenar el paciente seleccionado
  searchTerm: string = '';
  isEditing: boolean = false; // Para saber si estamos editando o registrando un paciente

  constructor(private pacienteService: PacientesService, private modalService: NgbModal) {}

  ngOnInit(): void {
    this.loadPacientes();
  }

  loadPacientes(): void {
    this.pacienteService.findAll().subscribe({
      next: (data) => {
        this.pacientes = data.body;
      },
      error: (err) => {
        console.error('Error al cargar pacientes', err);
      },
    });
  }

  filteredPacientes(): Paciente[] {
    if (!this.searchTerm.trim()) return this.pacientes;
    return this.pacientes.filter((paciente) =>
      paciente.nombre.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  // Llamado cuando se hace clic en "Registrar Nuevo Paciente"
  onRegister(): void {
    this.selectedPaciente = new Paciente(); // Resetear el formulario
    this.isEditing = false; // Asegurarnos de que es modo "registro"
    this.modalService.open('#editModal', { size: 'lg' }); // Abrir el modal
  }

  // Llamado cuando se hace clic en "Editar"
  onEditPaciente(paciente: Paciente): void {
    this.selectedPaciente = { ...paciente }; // Copiar los datos del paciente seleccionado
    this.isEditing = true; // Establecer que estamos en modo "edición"
    this.modalService.open('#editModal', { size: 'lg' }); // Abrir el modal
  }

  // Llamado cuando se guarda el formulario
  onSaveChanges(): void {
    if (this.isEditing) {
      // Lógica para actualizar paciente
      this.pacienteService.update(this.selectedPaciente.idPaciente, this.selectedPaciente).subscribe({
        next: () => {
          this.loadPacientes(); // Recargar la lista de pacientes
          this.modalService.dismissAll(); // Cerrar el modal
        },
        error: (err) => console.error('Error al actualizar paciente', err),
      });
    } else {
      // Lógica para registrar nuevo paciente
      this.pacienteService.create(this.selectedPaciente).subscribe({
        next: () => {
          this.loadPacientes(); // Recargar la lista de pacientes
          this.modalService.dismissAll(); // Cerrar el modal
        },
        error: (err) => console.error('Error al registrar paciente', err),
      });
    }
    this.selectedPaciente = new Paciente();
  }

  // Llamado cuando se hace clic en "Eliminar"
  onDeletePaciente(id: number): void {
    if (confirm('¿Estás seguro de eliminar este paciente?')) {
      this.pacienteService.delete(id).subscribe({
        next: () => {
          this.loadPacientes(); // Recargar la lista de pacientes
        },
        error: (err) => console.error('Error al eliminar paciente', err),
      });
    }
  }
}
