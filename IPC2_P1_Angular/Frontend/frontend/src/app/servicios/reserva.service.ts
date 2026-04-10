import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ReservaModel } from '../modelos/reserva-model';


@Injectable({
  providedIn: 'root',
})
export class ReservaService {

  constructor(private http: HttpClient) { }

  crearUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/ReservaServlet?accion=registrar';
  vincularUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/ReservaServlet';
  
  registrarReserva(reserva: Partial<ReservaModel>) {
    return this.http.post(this.crearUrl, reserva);
  }

  vincularCliente(numero_reserva: string, dpi: string) {
    const vinculo = {
        numero_reserva,
        dpi
      };
    return this.http.put(this.vincularUrl, vinculo);
  }

  private RESERVA_KEY = 'reserva_creada';
  private PERSONAS_KEY = 'personas_reserva';

  guardarNumeroReserva(reserva: Partial<ReservaModel>): void {
    sessionStorage.setItem(this.RESERVA_KEY, JSON.stringify(reserva.numero_reserva));

  }

  guardarPersonasReserva(reserva: Partial<ReservaModel>): void {
    sessionStorage.setItem(this.PERSONAS_KEY, JSON.stringify(reserva.cantidad_personas));

  }

  obtenerReserva(): any|string {
    const data = sessionStorage.getItem(this.RESERVA_KEY);
    return data ? JSON.parse(data) : '';
  }

  olvidarReserva(): void {
    sessionStorage.removeItem(this.RESERVA_KEY);
    sessionStorage.removeItem(this.PERSONAS_KEY);
  }



}
