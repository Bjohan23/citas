import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Citas } from '../models/citas';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class CitasService {
  private baseUrl = 'http://localhost:8090/v1/citas'; // Base URL del backend para citas

  constructor(private http: HttpClient) {}

  // Obtener todas las citas
  findAll(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}`);
  }

  // Crear una nueva cita
  create(cita: Citas): Observable<any> {
    return this.http.post(`${this.baseUrl}`, cita);
  }

  // Actualizar una cita existente
  update(id: number, cita: Citas): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, cita);
  }

  // Obtener una cita por su ID
  findById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  // Eliminar una cita
  delete(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
}
