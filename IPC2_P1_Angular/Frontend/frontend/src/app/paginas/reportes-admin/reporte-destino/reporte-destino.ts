import { Component } from '@angular/core';
import { Input } from '@angular/core';
import { ReportesService } from '../../../servicios/reportes.service';
import { ReportesOcupacionDestinoModel } from '../../../modelos/reportes-ocupacion-destino-model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-reporte-destino',
  imports: [FormsModule, CommonModule],
  templateUrl: './reporte-destino.html',
  styleUrl: './reporte-destino.css',
})
export class ReporteDestino {

    @Input() fechaInicio!: string;
  @Input() fechaFin!: string;

  reportes: ReportesOcupacionDestinoModel[] = [];

  constructor(private reportesService: ReportesService) { }

  ngOnInit() {
    this.obtenerReporte();
  }

  obtenerReporte() {

    this.reportesService
      .reporteReservacionesDestino(this.fechaInicio, this.fechaFin)
      .subscribe(data => {

        this.reportes = data;

      });

  }

}
