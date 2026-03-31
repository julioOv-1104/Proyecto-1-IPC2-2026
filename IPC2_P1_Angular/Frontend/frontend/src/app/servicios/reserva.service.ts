import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ReservaModel } from '../modelos/reserva-model';


@Injectable({
  providedIn: 'root',
})
export class ReservaService {

  constructor(private http: HttpClient) {}

  crearUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/ReservaServlet?accion=registrar';

  registrarReserva(reserva: Partial<ReservaModel>) {
    return this.http.post(this.crearUrl, reserva);
  }
  
}
