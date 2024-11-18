import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Citas } from '../../models/citas';
import { Paciente } from '../../models/paciente';
import { Medico } from '../../models/medico';
import { MedicosService } from '../../services/medicos.service';
import { PacientesService } from '../../services/pacientes.service';
import { CitasService } from '../../services/citas.service';
@Component({
  selector: 'app-citas',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './citas.component.html',
  styleUrl: './citas.component.css'
})
export class CitasComponent {
  citas: Citas[] = [];
  pacientes: Paciente[] = []; // Lista de pacientes
  medicos: Medico[] = []; // Lista de médicos
  selectedCita: Citas = new Citas();
  searchTerm: string = '';
  isEditing: boolean = false;

  constructor(private citasService: CitasService,
    private medicoService: MedicosService,
    private pacienteServide: PacientesService
  ) {}

  ngOnInit(): void {
    this.loadCitas();
    this.loadPacientes();
    this.loadMedicos();
  }

  loadCitas(): void {
    this.citasService.findAll().subscribe({
      next: (data) => (this.citas = data.body),
      error: (err) => console.error('Error al cargar citas', err),
    });
  }

  loadPacientes(): void {
    this.pacienteServide.findAll().subscribe((response)=>{
      this.pacientes = response.body;
    })
  }

  loadMedicos(): void {
    this.medicoService.findAll().subscribe((response)=>{
      this.medicos = response.body;
    })
  }

  filteredCitas(): Citas[] {
    if (!this.searchTerm.trim()) return this.citas;
    return this.citas.filter((cita) =>
      cita.paciente.nombre.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  onRegister(): void {
    this.selectedCita = new Citas();
    this.isEditing = false;
  }

  onEditCita(cita: Citas): void {
    this.selectedCita = { ...cita };
    this.isEditing = true;
  }

  onSaveChanges(): void {
    if (this.isEditing) {
      this.citasService.update(this.selectedCita.idCita, this.selectedCita).subscribe({
        next: () => this.loadCitas(),
        error: (err) => console.error('Error al actualizar cita', err),
      });
    } else {
      this.citasService.create(this.selectedCita).subscribe({
        next: () => this.loadCitas(),
        error: (err) => console.error('Error al registrar cita', err),
      });
    }
  }

  onDeleteCita(id: number): void {
    if (confirm('¿Estás seguro de eliminar esta cita?')) {
      this.citasService.delete(id).subscribe({
        next: () => this.loadCitas(),
        error: (err) => console.error('Error al eliminar cita', err),
      });
    }
  }
}
