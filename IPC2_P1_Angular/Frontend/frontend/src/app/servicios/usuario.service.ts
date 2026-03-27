import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from '../modelos/usuario';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {

  url = 'http://localhost:8080/Proyecto1_IPC2_2026/UsuarioServlet';
  urlRegistro = 'http://localhost:8080/Proyecto1_IPC2_2026/AdminServlet?accion=registrar';

  constructor(private http: HttpClient) { }

  login(usuario: Partial<Usuario>): Observable<Usuario> {
    return this.http.post<Usuario>(this.url, usuario);
  }

  registrar(usuario: Partial<Usuario>): Observable<Usuario> {
    return this.http.post<Usuario>(this.urlRegistro, usuario);
  }


}
