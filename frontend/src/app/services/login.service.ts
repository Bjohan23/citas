import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'; 
import { Observable } from 'rxjs'; 
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private baseUrl = 'http://localhost:8090/v1/usuarios/username/';

  constructor(private http: HttpClient) { }

  login(usuario: string): Observable<any> {
    const url = `${this.baseUrl}${usuario}`;  // Asegúrate de que la URL sea correcta
    return this.http.get<any>(url);  // El tipo de la respuesta es 'any' en este caso, puedes ajustarlo según tu backend
  }
}
