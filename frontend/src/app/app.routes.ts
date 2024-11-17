import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { UsuariosComponent } from './components/usuarios/usuarios.component';
import { PacientesComponent } from './components/pacientes/pacientes.component';
import { MedicosComponent } from './components/medicos/medicos.component';
import { CitasComponent } from './components/citas/citas.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {
    path: '',
    component: DashboardComponent,
    children: [
      { path: 'usuarios', component: UsuariosComponent },
      { path: 'medicos', component: MedicosComponent },
      { path: 'pacientes', component: PacientesComponent },
      { path: 'citas', component: CitasComponent },
    ],
  },
  { path: '', redirectTo: 'login', pathMatch: 'full' }, // Redirección inicial
  { path: '**', redirectTo: 'login' }, // Ruta no encontrada
];
