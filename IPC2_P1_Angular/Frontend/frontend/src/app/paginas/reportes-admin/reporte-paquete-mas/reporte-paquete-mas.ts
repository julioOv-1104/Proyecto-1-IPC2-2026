import { Component } from '@angular/core';
import { Input } from '@angular/core';
import { ReportesPaqueteVendidoModel } from '../../../modelos/reportes-paquete-vendido-model';
import { ReportesService } from '../../../servicios/reportes.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-reporte-paquete-mas',
  imports: [FormsModule, CommonModule],
  templateUrl: './reporte-paquete-mas.html',
  styleUrl: './reporte-paquete-mas.css',
})
export class ReportePaqueteMas {

  @Input() fechaInicio!: string;
  @Input() fechaFin!: string;

  reportes: ReportesPaqueteVendidoModel[] = [];

  constructor(private reportesService: ReportesService) { }

  ngOnInit() {
    this.obtenerReporte();
  }

  obtenerReporte() {

    this.reportesService
      .reportePaquetesMasVendidos(this.fechaInicio, this.fechaFin)
      .subscribe(data => {

        this.reportes = data;

      });

  }

}
