import { Component } from '@angular/core';
import { ReportesAgenteMasVentas } from '../../../modelos/reportes-agente-mas-ventas';
import { Input } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReportesService } from '../../../servicios/reportes.service';

@Component({
  selector: 'app-reporte-agente-ventas',
  imports: [FormsModule, CommonModule],
  templateUrl: './reporte-agente-ventas.html',
  styleUrl: './reporte-agente-ventas.css',
})
export class ReporteAgenteVentas {

  @Input() fechaInicio!: string;
  @Input() fechaFin!: string;


  reportes: ReportesAgenteMasVentas[] = [];//se almcenan los datos del reporte

constructor(private reportesService: ReportesService){}

ngOnInit(){
  this.obtenerReporte();
}

obtenerReporte(){

this.reportesService
.reporteAgenteMasVentas(this.fechaInicio, this.fechaFin)
.subscribe(data => {

this.reportes = data;

});

}

}
