import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Medico } from '../models/medico';
@Injectable({
  providedIn: 'root'
})
export class MedicosService {
  private apiUrl = 'http://localhost:8090/v1/medicos'; // URL base para las solicitudes al backend

  constructor(private http: HttpClient) {}

  
  findAll(): Observable<any> {
    return this.http.get(`${this.apiUrl}`);
  }

 
  findById(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }

  create(medico: Medico): Observable<any> {
    return this.http.post(`${this.apiUrl}`, medico);
  }

  
  update(id: number, medico: Medico): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}`, medico);
  }

  
  delete(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
