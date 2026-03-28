import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Input } from '@angular/core';
import { ReportesGananciasModel } from '../../../modelos/reportes-ganancias-model';
import { ReportesService } from '../../../servicios/reportes.service';

@Component({
  selector: 'app-reporte-ganancias',
  imports: [FormsModule, CommonModule],
  templateUrl: './reporte-ganancias.html',
  styleUrl: './reporte-ganancias.css',
})
export class ReporteGanancias {

  @Input() fechaInicio!: string;
  @Input() fechaFin!: string;

  reportes: ReportesGananciasModel[] = [];

  constructor(private reportesService: ReportesService) { }

  ngOnInit() {
    this.obtenerReporte();
  }

  obtenerReporte() {

    this.reportesService
      .reporteGanancias(this.fechaInicio, this.fechaFin)
      .subscribe(data => {

        this.reportes = data;

      });

  }

}
