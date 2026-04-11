import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HistorialReservaciones } from '../modelos/historial-reservaciones';
import { ClienteModel } from '../modelos/cliente-model';
import { ReservaModel } from '../modelos/reserva-model';
import { HistorialPagos } from '../modelos/historial-pagos';
import { ReservacionFechaDestino } from '../modelos/reservacion-fecha-destino';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class HistorialClienteService {

  constructor(private http: HttpClient) { }

  historialReservacionesUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/HistorialAtencionClienteServlet?accion=reservaciones';
  historialPagosReservaUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/HistorialAtencionClienteServlet?accion=pagos';
  reservacionesDisponiblesUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/HistorialAtencionClienteServlet?accion=disponibles';
  reservacionesHoyUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/HistorialAtencionClienteServlet';



  obtenerHistorialReservaciones(cliente: Partial<ClienteModel>) {
    return this.http.post<HistorialReservaciones[]>(this.historialReservacionesUrl, cliente);

  }

  obtenerHistorialPagos(reserva: Partial<ReservaModel>) {
    return this.http.post<HistorialPagos[]>(this.historialPagosReservaUrl, reserva);
  }

  obtenerReservacionesDisponibles(fecha: string, destino: string) {
      return this.http.post<ReservacionFechaDestino[]>(this.reservacionesDisponiblesUrl, { fecha, destino });
    }


     obtenerReservasHoy(): Observable<ReservaModel[]> {
        return this.http.get<ReservaModel[]>(this.historialPagosReservaUrl);
      }

}
