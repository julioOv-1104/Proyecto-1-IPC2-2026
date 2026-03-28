import { Component } from '@angular/core';
import { MenuAdmin } from '../../compartidos/menu-admin/menu-admin';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReporteVentas } from './reporte-ventas/reporte-ventas';
import { ReporteCancelaciones } from './reporte-cancelaciones/reporte-cancelaciones';
import { ReporteGanancias } from './reporte-ganancias/reporte-ganancias';
import { ReporteAgenteVentas } from './reporte-agente-ventas/reporte-agente-ventas';
import { ReporteAgenteGanancias } from './reporte-agente-ganancias/reporte-agente-ganancias';
import { ReportePaqueteMas } from './reporte-paquete-mas/reporte-paquete-mas';
import { ReportePaqueteMenos } from './reporte-paquete-menos/reporte-paquete-menos';
import { ReporteDestino } from './reporte-destino/reporte-destino';

@Component({
  selector: 'app-reportes-admin',
  imports: [MenuAdmin, FormsModule, CommonModule, ReporteVentas, ReporteCancelaciones, ReporteGanancias, 
    ReporteAgenteVentas, ReporteAgenteGanancias, ReportePaqueteMas, ReportePaqueteMenos, ReporteDestino],
  templateUrl: './reportes-admin.html',
  styleUrl: './reportes-admin.css',
})
export class ReportesAdmin {


  tipoReporte = "";//almacena el tipo de reporte seleccionado por el usuario
  fechaInicio!: string;
  fechaFin!: string;

  mostrarReporte(tipo: string){
  this.tipoReporte = tipo;
}

  prueba(){
    console.log(this.fechaInicio);
  }


}
