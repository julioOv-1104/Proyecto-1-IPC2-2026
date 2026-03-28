import { Component } from '@angular/core';
import { Input } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReporteVentasModel } from '../../../modelos/reporte-ventas-model';
import { ReportesService } from '../../../servicios/reportes.service';



@Component({
  selector: 'app-reporte-ventas',
  imports: [FormsModule, CommonModule],
  templateUrl: './reporte-ventas.html',
  styleUrl: './reporte-ventas.css',
})
export class ReporteVentas {

  @Input() fechaInicio!: string;
  @Input() fechaFin!: string;


  reportes: ReporteVentasModel[] = [];//se almcenan los datos del reporte

constructor(private reportesService: ReportesService){}

ngOnInit(){
  this.obtenerReporte();
}

obtenerReporte(){

this.reportesService
.reporteVentas(this.fechaInicio, this.fechaFin)
.subscribe(data => {

this.reportes = data;

});

}

}
