import { Component } from '@angular/core';
import { ReportesAgenteMasVentas } from '../../../modelos/reportes-agente-mas-ventas';
import { Input } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReportesService } from '../../../servicios/reportes.service';

@Component({
  selector: 'app-reporte-agente-ganancias',
  imports: [FormsModule, CommonModule],
  templateUrl: './reporte-agente-ganancias.html',
  styleUrl: './reporte-agente-ganancias.css',
})
export class ReporteAgenteGanancias {

    @Input() fechaInicio!: string;
  @Input() fechaFin!: string;


  reportes: ReportesAgenteMasVentas[] = [];//se almcenan los datos del reporte

constructor(private reportesService: ReportesService){}

ngOnInit(){
  this.obtenerReporte();
}

obtenerReporte(){

this.reportesService
.reporteAgenteMasGanancias(this.fechaInicio, this.fechaFin)
.subscribe(data => {

this.reportes = data;

});

}

}
