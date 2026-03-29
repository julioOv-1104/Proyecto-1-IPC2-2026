import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PaqueteModel } from '../modelos/paquete-model';

@Injectable({
  providedIn: 'root',
})
export class PaqueteService {

  constructor(private http: HttpClient) { }

  registrarPaqueteURL = 'http://localhost:8080/Proyecto1_IPC2_2026/PaqueteServlet?accion=crear';
  obtenerPaquetesUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/PaqueteServlet';
  editarUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/PaqueteServlet?accion=editar';


  registrarPaquete(paquete: Partial<PaqueteModel>) {
    return this.http.post<PaqueteModel>(this.registrarPaqueteURL, paquete);
  }

  obtenerPaquetes(): Observable<PaqueteModel[]> {
    return this.http.get<PaqueteModel[]>(this.obtenerPaquetesUrl);
  }

  editarPaquete(paquete: Partial<PaqueteModel>) {
    return this.http.post<PaqueteModel>(this.editarUrl, paquete);
  }

}
