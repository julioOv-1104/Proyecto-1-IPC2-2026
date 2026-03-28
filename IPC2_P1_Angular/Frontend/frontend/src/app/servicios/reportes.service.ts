import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ReporteVentasModel } from '../modelos/reporte-ventas-model';
import { ReportesCancelacionesModel } from '../modelos/reportes-cancelaciones-model';
import { ReportesGananciasModel } from '../modelos/reportes-ganancias-model';
import { ReportesAgenteMasVentas } from '../modelos/reportes-agente-mas-ventas';
import { ReportesPaqueteVendidoModel } from '../modelos/reportes-paquete-vendido-model';
import { ReportesOcupacionDestinoModel } from '../modelos/reportes-ocupacion-destino-model';

@Injectable({
  providedIn: 'root',
})
export class ReportesService {

  private ventasUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/ReportesAdminServlet?accion=ventasIntervalo';
  private cancelacionesUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/ReportesAdminServlet?accion=cancelacionesIntervalo';
  private gananciasUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/ReportesAdminServlet?accion=gananciaIntervalo';
  private agenteMasVentasUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/ReportesAdminServlet?accion=masVentas';
  private agenteMasGananciasUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/ReportesAdminServlet?accion=masGanancias';
  private paquetesMasVendidosUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/ReportesAdminServlet?accion=paqueteMasVendido';
  private paquetesMenosVendidosUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/ReportesAdminServlet?accion=paqueteMenosVendido';
  private reservacionesDestinoUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/ReportesAdminServlet?accion=ocupacionDestino';

  constructor(private http: HttpClient) { }

  reporteVentas(fecha1: string, fecha2: string) {
    return this.http.post<ReporteVentasModel[]>(this.ventasUrl, { fecha1, fecha2 });
  }

  reporteCancelaciones(fecha1: string, fecha2: string) {
    return this.http.post<ReportesCancelacionesModel[]>(this.cancelacionesUrl, { fecha1, fecha2 });
  }

  reporteGanancias(fecha1: string, fecha2: string) {
    return this.http.post<ReportesGananciasModel[]>(this.gananciasUrl, { fecha1, fecha2 });
  }

  reporteAgenteMasVentas(fecha1: string, fecha2: string) {
    return this.http.post<ReportesAgenteMasVentas[]>(this.agenteMasVentasUrl, { fecha1, fecha2 });
  }

  reporteAgenteMasGanancias(fecha1: string, fecha2: string) {
    return this.http.post<ReportesAgenteMasVentas[]>(this.agenteMasGananciasUrl, { fecha1, fecha2 });
    //usa el mismo modelo porque la respuesta es similar
  }

  reportePaquetesMasVendidos(fecha1: string, fecha2: string) {
    return this.http.post<ReportesPaqueteVendidoModel[]>(this.paquetesMasVendidosUrl, { fecha1, fecha2 });
  }

 reportePaquetesMenosVendidos(fecha1: string, fecha2: string) {
    return this.http.post<ReportesPaqueteVendidoModel[]>(this.paquetesMenosVendidosUrl, { fecha1, fecha2 });
    //tambien usa el mismo modelo porque la respuesta es similar
  }

reporteReservacionesDestino(fecha1: string, fecha2: string) {
    return this.http.post<ReportesOcupacionDestinoModel[]>(this.reservacionesDestinoUrl, { fecha1, fecha2 });
  }

}
