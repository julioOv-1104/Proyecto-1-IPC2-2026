import { Component } from '@angular/core';
import { MenuAtencion } from '../../compartidos/menu-atencion/menu-atencion';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReservaService } from '../../servicios/reserva.service';
import { ReservaModel } from '../../modelos/reserva-model';
import { PaqueteModel } from '../../modelos/paquete-model';
import { PaqueteService } from '../../servicios/paquete.service';


@Component({
  selector: 'app-crear-reservacion',
  imports: [MenuAtencion, FormsModule, CommonModule],
  templateUrl: './crear-reservacion.html',
  styleUrl: './crear-reservacion.css',
})

export class CrearReservacion {

  constructor(private reservaService: ReservaService, private paqueteService: PaqueteService) {}

  paquetes: PaqueteModel[] = [];

  mensajeError: string | null = null;

  numero_reserva: string = '';
  fecha_creacion:Date | null = null;
  fecha_viaje: Date | null = null;
  id_paquete: number = 0;
  id_usuario: number = 0;
  cantidad_personas: number = 0;
  costo_total: number = 0;

 ngOnInit() {
   
    this.obtenerPaquetes();

    console.log(this.paquetes);
  }

  obtenerPaquetes() {

    this.paqueteService.obtenerPaquetes().subscribe(data => {

      this.paquetes = data;

    });

  }

  registrarReserva() {

    const nuevaReserva: Partial<ReservaModel> = {
      numero_reserva: this.numero_reserva,
      fecha_creacion: this.fecha_creacion,
      fecha_viaje: this.fecha_viaje,
      id_paquete: this.id_paquete,
      id_usuario: this.id_usuario,
      cantidad_personas: this.cantidad_personas,
      costo_total: this.costo_total
    };

    this.reservaService.registrarReserva(nuevaReserva).subscribe({
                next: (response: any) => {
          
                  // si recibe un error
                  if (response.status === 'error') {
                    this.mensajeError = response.mensaje;
                    return;
                  }
          
                  const reserva = response as ReservaModel;
                  console.log('Registro exitoso, reserva creada:', reserva);
          
                  // limpiar campos del formulario
                  this.numero_reserva = '';
                  this.fecha_creacion = new Date();
                  this.fecha_viaje = new Date();
                  this.id_paquete = 0;
                  this.id_usuario = 0;
                  this.cantidad_personas = 0;
                  this.costo_total = 0;
                  this.mensajeError = 'Registro de reserva exitoso.';
          
                }
          
          
              });

  }

}
