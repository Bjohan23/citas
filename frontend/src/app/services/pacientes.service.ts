import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Paciente } from '../models/paciente';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class PacientesService {

  private apiUrl = 'http://localhost:8090/v1/pacientes'; // URL base para las solicitudes al backend

  constructor(private http: HttpClient) {}


  findAll(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}`);
  }

  findById(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }

  create(paciente: Paciente): Observable<Paciente> {
    return this.http.post<Paciente>(`${this.apiUrl}`, paciente);
  }


  update(id: number, paciente: Paciente): Observable<Paciente> {
    return this.http.put<Paciente>(`${this.apiUrl}/${id}`, paciente);
  }


  delete(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
