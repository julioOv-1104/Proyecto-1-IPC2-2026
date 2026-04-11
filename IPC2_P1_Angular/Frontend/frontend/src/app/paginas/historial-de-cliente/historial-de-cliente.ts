import { Component } from '@angular/core';
import { HistorialClienteService } from '../../servicios/historial-cliente.service';
import { HistorialReservaciones } from '../../modelos/historial-reservaciones';
import { ClienteModel } from '../../modelos/cliente-model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MenuAtencion } from '../../compartidos/menu-atencion/menu-atencion';

@Component({
  selector: 'app-historial-de-cliente',
  imports: [FormsModule, CommonModule, MenuAtencion],
  templateUrl: './historial-de-cliente.html',
  styleUrl: './historial-de-cliente.css',
})
export class HistorialDeCliente {

  constructor(private historialClienteService: HistorialClienteService) { }

  reservaciones: HistorialReservaciones[] = [];

  dpi: string = '';
  activo: boolean = false;

  consultarHistorial() {

if (!this.dpi) {
      console.error('El DPI no puede estar vacío');
      return;
    }

    const cliente: Partial<ClienteModel> = { dpi: this.dpi };

    this.historialClienteService.obtenerHistorialReservaciones(cliente).subscribe(data => {

      this.reservaciones = data;

    });
console.log('reservaciones obtenidas:', this.reservaciones);

    this.activo = true;
  }

}
