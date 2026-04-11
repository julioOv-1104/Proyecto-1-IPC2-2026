import { Component } from '@angular/core';
import { HistorialClienteService } from '../../servicios/historial-cliente.service';
import { HistorialPagos } from '../../modelos/historial-pagos';
import { ReservaModel } from '../../modelos/reserva-model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MenuAtencion } from '../../compartidos/menu-atencion/menu-atencion';

@Component({
  selector: 'app-historial-de-pago',
  imports: [FormsModule, CommonModule, MenuAtencion],
  templateUrl: './historial-de-pago.html',
  styleUrl: './historial-de-pago.css',
})
export class HistorialDePago {

constructor(private historialClienteService: HistorialClienteService) { }

reservaciones: HistorialPagos[] = [];
numero_reserva: string = '';
activo: boolean = false;

consultarHistorial() {

if (!this.numero_reserva) {
      console.error('El número de reserva no puede estar vacío');
      return;
    }

    const cliente: Partial<ReservaModel> = { numero_reserva: this.numero_reserva };

    this.historialClienteService.obtenerHistorialPagos(cliente).subscribe(data => {

      this.reservaciones = data;

    });
console.log('reservaciones obtenidas:', this.reservaciones);

    this.activo = true;
  }

}
