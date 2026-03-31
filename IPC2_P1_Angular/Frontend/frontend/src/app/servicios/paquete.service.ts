import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PaqueteModel } from '../modelos/paquete-model';
import { DetallePaquete } from '../modelos/detalle-paquete';
import { EditarPaquete } from '../paginas/editar-paquete/editar-paquete';
import { ServicioModel } from '../modelos/servicio-model';

@Injectable({
  providedIn: 'root',
})
export class PaqueteService {

  constructor(private http: HttpClient) { }

  registrarPaqueteURL = 'http://localhost:8080/Proyecto1_IPC2_2026/PaqueteServlet?accion=crear';
  obtenerPaquetesUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/PaqueteServlet';
  editarUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/PaqueteServlet?accion=editar';
  paqueteDisponibleUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/ConsultasOperacionesServlet?accion=disponibles';
  detallePaqueteUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/ConsultasOperacionesServlet?accion=detalle';
  obtenerServiciosUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/ServicioServlet';
  vincularUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/PaqueteServlet?accion=vincular';


  registrarPaquete(paquete: Partial<PaqueteModel>) {
    return this.http.post<PaqueteModel>(this.registrarPaqueteURL, paquete);
  }

  obtenerPaquetes(): Observable<PaqueteModel[]> {
    return this.http.get<PaqueteModel[]>(this.obtenerPaquetesUrl);
  }

  editarPaquete(paquete: Partial<PaqueteModel>) {
    return this.http.post<PaqueteModel>(this.editarUrl, paquete);
  }

  obtenerPaquetesDisponibles(paquete: Partial<PaqueteModel>): Observable<PaqueteModel[]> {
    return this.http.post<PaqueteModel[]>(this.paqueteDisponibleUrl, paquete);
  }

  obtenerDetallePaquete(paquete: Partial<DetallePaquete>): Observable<DetallePaquete[]> {
    return this.http.post<DetallePaquete[]>(this.detallePaqueteUrl, paquete);
  }


  obtenerServicios(): Observable<ServicioModel[]> {
    return this.http.get<ServicioModel[]>(this.obtenerServiciosUrl);
  }

   private PAQUETE_EN_USO = 'usuario_Logueado';
  
    guardarPaquete(paquete:PaqueteModel): void {
      const paqueteCreado = {
        nombre: paquete.nombre_paquete,
        id: paquete.id_paquete
      };
  
      sessionStorage.setItem(this.PAQUETE_EN_USO, JSON.stringify(paqueteCreado));
  
    }
  
    
    obtenerPaquete(): PaqueteModel | null {
      const data = sessionStorage.getItem(this.PAQUETE_EN_USO);
      return data ? JSON.parse(data) : null;
    }

    vincularServicio(id_paquete: number, id_servicio: number, costo: number) {
      const vinculo = {
        id_paquete,
        id_servicio,
        costo
      };
      return this.http.post(this.vincularUrl, vinculo);
    }


}
