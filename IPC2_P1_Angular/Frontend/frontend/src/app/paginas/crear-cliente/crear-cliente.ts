import { Component } from '@angular/core';
import { MenuAtencion } from '../../compartidos/menu-atencion/menu-atencion';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReservaService } from '../../servicios/reserva.service';
import { ClienteModel } from '../../modelos/cliente-model';
import { ClienteService } from '../../servicios/cliente.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-crear-cliente',
  imports: [MenuAtencion, CommonModule, FormsModule],
  templateUrl: './crear-cliente.html',
  styleUrl: './crear-cliente.css',
})
export class CrearCliente {

  constructor(private clienteService: ClienteService, private reservaService: ReservaService,
      private router: Router
  ) { }

  mensajeError: string | null = null;
  mensajeError2: string | null = null;

  dpi: string = '';
  nombre_cliente: string = '';
  fecha_nacimiento: Date | null = null;
  telefono: string = '';
  email: string = ''
  nacionalidad: string = '';
  dpiVincular: string = '';
  numeroReserva: string = '';
  cantidad_personas: number = 0;


  ngOnInit() {
    this.obtenerReserva();
    console.log('Número de reserva obtenido:', this.numeroReserva);
  }

  obtenerReserva() {
    const reservaGuardada = sessionStorage.getItem('reserva_creada');
    console.log(reservaGuardada);
    this.numeroReserva = reservaGuardada ? JSON.parse(reservaGuardada) : '';

    const personasGuardadas = sessionStorage.getItem('personas_reserva');
    console.log(personasGuardadas);
    this.cantidad_personas = personasGuardadas ? JSON.parse(personasGuardadas) : 0;

  }

  vincularCliente() {

    if (!this.dpiVincular) {
      this.mensajeError2 = 'Por favor, ingresa el DPI del cliente a vincular.';
      return;
    }

    this.reservaService.vincularCliente(this.numeroReserva, this.dpiVincular).subscribe({
      next: (response: any) => {

        // si recibe un error
        if (response.status === 'error') {
          this.mensajeError2 = response.mensaje;
          return;
        }


        console.log('cliente y reserva vinculados exitosamente');
        this.mensajeError2 = 'Cliente vinculado a reserva exitosamente.';

        this.cantidad_personas--;

        if (this.cantidad_personas <= 0) {
          this.reservaService.olvidarReserva();
          this.router.navigate(['/crear-reservacion']);
          
        }

      }


    });
  }

  registrarCliente() {

    if (!this.dpi || !this.nombre_cliente || !this.fecha_nacimiento || !this.telefono || !this.email || !this.nacionalidad) {
      this.mensajeError = 'Por favor, completa todos los campos.';
      return;
    }

    const nuevoCliente: ClienteModel = {
      dpi: this.dpi,
      nombre_cliente: this.nombre_cliente,
      fecha_nacimiento: this.fecha_nacimiento,
      telefono: this.telefono,
      email: this.email,
      nacionalidad: this.nacionalidad
    }

    this.clienteService.registrarCliente(nuevoCliente).subscribe({
      next: (response: any) => {

        // si recibe un error
        if (response.status === 'error') {
          this.mensajeError = response.mensaje;
          return;
        }

        const cliente = response as ClienteModel;
        console.log('Registro exitoso, cliente creado:', cliente);

        // limpiar campos del formulario
        this.dpi = '';
        this.nombre_cliente = '';
        this.fecha_nacimiento = null;
        this.telefono = '';
        this.email = '';
        this.nacionalidad = '';
        this.mensajeError = 'Registro exitoso, cliente creado correctamente.';

      }


    });

  }

}
