import { Component } from '@angular/core';
import { HistorialClienteService } from '../../servicios/historial-cliente.service';
import { ReservacionFechaDestino } from '../../modelos/reservacion-fecha-destino';
import { FormsModule } from '@angular/forms';
import { Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuAtencion } from '../../compartidos/menu-atencion/menu-atencion';

@Component({
  selector: 'app-reservaciones-disponibles',
  imports: [FormsModule, CommonModule, MenuAtencion],
  templateUrl: './reservaciones-disponibles.html',
  styleUrl: './reservaciones-disponibles.css',
})
export class ReservacionesDisponibles {

  constructor(private historialClienteService: HistorialClienteService) { }

  @Input() fecha_viaje: string = '';
  @Input() destino: string = '';
  activo: boolean = false;

  reservaciones: ReservacionFechaDestino[] = [];


  consultarHistorial() {

    if (!this.fecha_viaje || !this.destino) {
      console.error('La fecha de viaje y el destino no pueden estar vacíos');
      return;
    }

    this.historialClienteService.obtenerReservacionesDisponibles(this.fecha_viaje, this.destino).subscribe(data => {

      this.reservaciones = data;

    });
    console.log('reservaciones obtenidas:', this.reservaciones);

    this.activo = true;
  }


}
