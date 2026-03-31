import { Component } from '@angular/core';
import { MenuOperaciones } from '../../compartidos/menu-operaciones/menu-operaciones';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { PaqueteModel } from '../../modelos/paquete-model';
import { PaqueteService } from '../../servicios/paquete.service';

@Component({
  selector: 'app-consultar-paquete',
  imports: [MenuOperaciones, FormsModule, CommonModule],
  templateUrl: './consultar-paquete.html',
  styleUrl: './consultar-paquete.css',
})

export class ConsultarPaquete {

  constructor(private paqueteService: PaqueteService) { }

  paquetes: PaqueteModel[] = [];

  destino: string = '';
  mensajeError: string | null = null;


  obtenerPaquetesDisponibles() {

    const paquete: Partial<PaqueteModel> = {
      destino: this.destino
    };

    this.paqueteService.obtenerPaquetesDisponibles(paquete).subscribe(data => {

      this.paquetes = data;

      console.log('Paquetes disponibles:', this.paquetes);

    });

  }

}
