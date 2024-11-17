import { Component } from '@angular/core';
import { Medico } from '../../models/medico';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MedicosService } from '../../services/medicos.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-medicos',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './medicos.component.html',
  styleUrls: ['./medicos.component.css'],
})
export class MedicosComponent {
  medicos: Medico[] = [];
  selectedMedico: Medico = new Medico(); // Para almacenar el médico seleccionado
  searchTerm: string = '';
  isEditing: boolean = false; // Para saber si estamos editando o registrando un médico

  // Lista de especialidades predefinidas
  especialidades: string[] = [
    'Cardiología',
    'Pediatría',
    'Gastroenterología',
    'Neurocirugía',
    'Dermatología',
    'Psiquiatría',
    'Oncología',
    'Endocrinología',
    'Traumatología',
    'Oftalmología',
    'Ginecología',
    'Urología',
    'Reumatología',
    'Otorrinolaringología',
  ];

  constructor(private medicosService: MedicosService, private modalService: NgbModal) {}

  ngOnInit(): void {
    this.loadMedicos();
  }

  loadMedicos(): void {
    this.medicosService.findAll().subscribe({
      next: (data) => {
        this.medicos = data.body;
      },
      error: (err) => {
        console.error('Error al cargar médicos', err);
      },
    });
  }

  filteredMedicos(): Medico[] {
    if (!this.searchTerm.trim()) return this.medicos;
    return this.medicos.filter((medico) =>
      medico.nombre.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  // Llamado cuando se hace clic en "Registrar Nuevo Médico"
  onRegister(): void {
    this.selectedMedico = new Medico(); // Resetear el formulario
    this.isEditing = false; // Asegurarnos de que es modo "registro"
    this.modalService.open('#editModal', { size: 'lg' }); // Abrir el modal
  }

  // Llamado cuando se hace clic en "Editar"
  onEditMedico(medico: Medico): void {
    this.selectedMedico = { ...medico }; // Copiar los datos del médico seleccionado
    this.isEditing = true; // Establecer que estamos en modo "edición"
    this.modalService.open('#editModal', { size: 'lg' }); // Abrir el modal
  }

  // Llamado cuando se guarda el formulario
  onSaveChanges(): void {
    if (this.isEditing) {
      // Lógica para actualizar médico
      this.medicosService.update(this.selectedMedico.idMedico, this.selectedMedico).subscribe({
        next: () => {
          this.loadMedicos(); // Recargar la lista de médicos
          this.modalService.dismissAll(); // Cerrar el modal
        },
        error: (err) => console.error('Error al actualizar médico', err),
      });
    } else {
      // Lógica para registrar nuevo médico
      this.medicosService.create(this.selectedMedico).subscribe({
        next: () => {
          this.loadMedicos(); // Recargar la lista de médicos
          this.modalService.dismissAll(); // Cerrar el modal
        },
        error: (err) => console.error('Error al registrar médico', err),
      });
    }
  }

  // Llamado cuando se hace clic en "Eliminar"
  onDeleteMedico(id: number): void {
    if (confirm('¿Estás seguro de eliminar este médico?')) {
      this.medicosService.delete(id).subscribe({
        next: () => {
          this.loadMedicos(); // Recargar la lista de médicos
        },
        error: (err) => console.error('Error al eliminar médico', err),
      });
    }
  }
}
