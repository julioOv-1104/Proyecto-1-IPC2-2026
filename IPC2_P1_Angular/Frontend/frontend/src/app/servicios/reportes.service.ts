import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ReporteVentas } from '../modelos/reporte-ventas';

@Injectable({
  providedIn: 'root',
})
export class ReportesService {

  private ventasUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/ReportesAdminServlet?accion=ventasIntervalo';

  constructor(private http: HttpClient) {}

  reporteVentas(fecha1: string, fecha2: string){
    return this.http.post<ReporteVentas[]>(this.ventasUrl, {fecha1, fecha2});
  }
  
}
