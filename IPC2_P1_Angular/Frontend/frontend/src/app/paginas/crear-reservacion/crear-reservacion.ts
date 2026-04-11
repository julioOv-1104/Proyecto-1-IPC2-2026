import { Component } from '@angular/core';
import { MenuAtencion } from '../../compartidos/menu-atencion/menu-atencion';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReservaService } from '../../servicios/reserva.service';
import { ReservaModel } from '../../modelos/reserva-model';
import { UsuarioService } from '../../servicios/usuario.service';
import { PaqueteModel } from '../../modelos/paquete-model';
import { PaqueteService } from '../../servicios/paquete.service';
import { Usuario } from '../../modelos/usuario';
import { Router } from '@angular/router';


@Component({
  selector: 'app-crear-reservacion',
  imports: [MenuAtencion, FormsModule, CommonModule],
  templateUrl: './crear-reservacion.html',
  styleUrl: './crear-reservacion.css',
})

export class CrearReservacion {

  constructor(private reservaService: ReservaService, private paqueteService: PaqueteService,
    private router: Router, private usuarioService: UsuarioService) { }

  paquetes: PaqueteModel[] = [];

  mensajeError: string | null = null;

  numero_reserva: string = '';
  fecha_creacion: Date | null = null;
  fecha_viaje: Date | null = null;
  id_paquete: number = 0;
  id_usuario: number = 0;
  cantidad_personas: number = 0;
  costo_total: number = 0;

  ngOnInit() {

    this.obtenerPaquetes();
    this.obtenerUsuario();
  }

  obtenerPaquetes() {

    this.paqueteService.obtenerPaquetes().subscribe(data => {

      this.paquetes = data;

    });

  }

  obtenerUsuario() {

    console.log('Obteniendo usuario logueado...');

    this.usuarioService.obtenerUsuarios().subscribe(data => {
      const usuarioActual = JSON.parse(sessionStorage.getItem('usuario') || '{}');
      this.id_usuario = usuarioActual.id_usuario;
    });

  }

  registrarReserva() {

    console.log('id usuario:', this.id_usuario);

    const nuevaReserva: Partial<ReservaModel> = {
      numero_reserva: this.numero_reserva,
      fecha_creacion: this.fecha_creacion,
      fecha_viaje: this.fecha_viaje,
      id_paquete: this.id_paquete,
      id_usuario: this.id_usuario,
      cantidad_personas: this.cantidad_personas,
      costo_total: this.costo_total
    };


    if (!nuevaReserva.numero_reserva || !nuevaReserva.fecha_creacion || !nuevaReserva.fecha_viaje
      || !nuevaReserva.id_paquete || !nuevaReserva.cantidad_personas || !nuevaReserva.costo_total) {
      this.mensajeError = 'Por favor, complete todos los campos obligatorios.';
      return;
    }

    this.reservaService.registrarReserva(nuevaReserva).subscribe({
      next: (response: any) => {

        // si recibe un error
        if (response.status === 'error') {
          this.mensajeError = response.mensaje;
          return;
        }

        const reserva = response as ReservaModel;
        console.log('Registro exitoso, reserva creada:', reserva);

        this.mensajeError = 'Registro de reserva exitoso.';

        this.reservaService.guardarNumeroReserva(reserva);//guardar el numero de la reserva para vincular clientes despues
        this.reservaService.guardarPersonasReserva(reserva);//guardar la cantidad de personas para mostrarla despues

        this.router.navigate(['/crear-cliente']);

      }


    });

  }

}
