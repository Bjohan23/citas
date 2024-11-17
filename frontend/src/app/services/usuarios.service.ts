import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { usuario } from '../models/usuario';
@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  private baseUrl = 'http://localhost:8090/v1/usuarios';

  constructor(private http: HttpClient) {}

  // Obtener todos los usuarios
  findAll(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}`);
  }

  // Crear un nuevo usuario
  create(user: usuario): Observable<any> {
    return this.http.post(`${this.baseUrl}`, user);
  }

  // Actualizar un usuario existente
  update(id: number, user: usuario): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, user);
  }

  // Obtener un usuario por su ID
  findById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  // Eliminar un usuario
  delete(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
}
