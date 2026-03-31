import { Component } from '@angular/core';
import { MenuOperaciones } from '../../compartidos/menu-operaciones/menu-operaciones';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { PaqueteService } from '../../servicios/paquete.service';
import { DetallePaquete } from '../../modelos/detalle-paquete';

@Component({
  selector: 'app-consultar-detalle-paquete',
  imports: [MenuOperaciones, FormsModule, CommonModule],
  templateUrl: './consultar-detalle-paquete.html',
  styleUrl: './consultar-detalle-paquete.css',
})
export class ConsultarDetallePaquete {

  constructor(private paqueteService: PaqueteService) { }

  detalle: DetallePaquete[] = [];

  id_paquete: number = 0;
  mensajeError: string | null = null;

  obtenerDetallePaquete() {

    const paquete: Partial<DetallePaquete> = {
      id_paquete: this.id_paquete
    };

    this.paqueteService.obtenerDetallePaquete(paquete).subscribe(data => {

      this.detalle = data;

      console.log('Detalle del paquete:', this.detalle);

    });

  }

}
