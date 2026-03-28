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


  registrarPaquete(paquete: Partial<PaqueteModel>) {
      return this.http.post<PaqueteModel>(this.registrarPaqueteURL, paquete);
    }

}
