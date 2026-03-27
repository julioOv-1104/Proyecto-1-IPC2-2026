import { Component } from '@angular/core';
import { MenuAdmin } from '../../compartidos/menu-admin/menu-admin';
import { ReportesService } from '../../servicios/reportes.service';
import { ReporteVentas } from '../../modelos/reporte-ventas';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-reportes-admin',
  imports: [MenuAdmin, FormsModule, CommonModule],
  templateUrl: './reportes-admin.html',
  styleUrl: './reportes-admin.css',
})
export class ReportesAdmin {

  constructor(private reportesService: ReportesService) { }

  reportesVentas: ReporteVentas[] = [];

  fechaInicio!: string;
  fechaFin!: string;


  obtenerReporteVentas() {

    this.reportesService
      .reporteVentas(this.fechaInicio, this.fechaFin)
      .subscribe(data => {

        this.reportesVentas = data;

      });

  }

  prueba(){
    console.log(this.fechaInicio);
  }


}
