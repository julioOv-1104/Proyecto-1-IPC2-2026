import { Component } from '@angular/core';
import { Input } from '@angular/core';
import { ReportesPaqueteVendidoModel } from '../../../modelos/reportes-paquete-vendido-model';
import { ReportesService } from '../../../servicios/reportes.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-reporte-paquete-menos',
  imports: [FormsModule, CommonModule],
  templateUrl: './reporte-paquete-menos.html',
  styleUrl: './reporte-paquete-menos.css',
})
export class ReportePaqueteMenos {

  @Input() fechaInicio!: string;
  @Input() fechaFin!: string;

  reportes: ReportesPaqueteVendidoModel[] = [];

  constructor(private reportesService: ReportesService) { }

  ngOnInit() {
    this.obtenerReporte();
  }

  obtenerReporte() {

    this.reportesService
      .reportePaquetesMenosVendidos(this.fechaInicio, this.fechaFin)
      .subscribe(data => {

        this.reportes = data;

      });

  }

}
