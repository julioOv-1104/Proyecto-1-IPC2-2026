import { Component } from '@angular/core';
import { HistorialClienteService } from '../../servicios/historial-cliente.service';
import { ReservaModel } from '../../modelos/reserva-model';
import { ReservacionFechaDestino } from '../../modelos/reservacion-fecha-destino';
import { FormsModule } from '@angular/forms';
import { Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuAtencion } from '../../compartidos/menu-atencion/menu-atencion';

@Component({
  selector: 'app-reservaciones-hoy',
  imports: [CommonModule,FormsModule, MenuAtencion],
  templateUrl: './reservaciones-hoy.html',
  styleUrl: './reservaciones-hoy.css',
})
export class ReservacionesHoy {

  constructor(private historialClienteService: HistorialClienteService) { }

  reservaciones: ReservaModel[] = [];

  ngOnInit(){

 this.historialClienteService.obtenerReservasHoy().subscribe(data => {

      this.reservaciones = data;

    });
    console.log('reservaciones de hoy obtenidas:', this.reservaciones);


  }

}
