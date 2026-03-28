import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Input } from '@angular/core';
import { ReportesCancelacionesModel } from '../../../modelos/reportes-cancelaciones-model';
import { ReportesService } from '../../../servicios/reportes.service';

@Component({
  selector: 'app-reporte-cancelaciones',
  imports: [FormsModule,CommonModule],
  templateUrl: './reporte-cancelaciones.html',
  styleUrl: './reporte-cancelaciones.css',
})
export class ReporteCancelaciones {

  @Input() fechaInicio!: string;
  @Input() fechaFin!: string;

  reportes: ReportesCancelacionesModel[] = [];

  constructor(private reportesService: ReportesService){}
  
  ngOnInit(){
    this.obtenerReporte();
  }
  
  obtenerReporte(){
  
  this.reportesService
  .reporteCancelaciones(this.fechaInicio, this.fechaFin)
  .subscribe(data => {
  
  this.reportes = data;
  
  });
  
  }

}
