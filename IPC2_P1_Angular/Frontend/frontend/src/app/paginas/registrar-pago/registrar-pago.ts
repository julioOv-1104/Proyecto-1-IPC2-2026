import { Component } from '@angular/core';
import { MenuAtencion } from '../../compartidos/menu-atencion/menu-atencion';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReservaModel } from '../../modelos/reserva-model';
import { PagoService } from '../../servicios/pago.service';
import { PagoModel } from '../../modelos/pago-model';
import { ReservaService } from '../../servicios/reserva.service';

@Component({
  selector: 'app-registrar-pago',
  imports: [MenuAtencion, FormsModule, CommonModule],
  templateUrl: './registrar-pago.html',
  styleUrl: './registrar-pago.css',
})
export class RegistrarPago {

  constructor(private pagoService: PagoService, private reservaService: ReservaService) { }

  reservas: ReservaModel[] = [];

  mensajeError: string | null = null;
  mensajeError2: string | null = null;

  numero_reserva: string = '';
  reservaAcancelar: string = '';
  monto: number = 0
  fecha: Date | null = null;
  metodo: string = '';
  metodo_pago: number = 0;


  ngOnInit() {
    this.obtenerReservas();
  }

  obtenerReservas() {

    this.reservaService.obtenerReservas().subscribe(data => {

      this.reservas = data;

    });

  }

  registrarPago() {

    if (this, this.metodo === 'Efectivo') {
      this.metodo_pago = 1;
    }

    if (this, this.metodo === 'Tarjeta') {
      this.metodo_pago = 2;
    }

    if (this, this.metodo === 'Transferencia') {
      this.metodo_pago = 3;
    }


    if (this.numero_reserva.trim() === '' || !this.fecha || this.metodo.trim() === '') {
      this.mensajeError = 'Por favor, completa todos los campos correctamente.';
      return;
    }

    if (this.monto <= 0) {
      this.mensajeError = 'El monto debe ser un número positivo.';
      return;
    }

    const pagoData: Partial<PagoModel> = {
      numero_reserva: this.numero_reserva,
      monto: this.monto,
      fecha: this.fecha,
      metodo_pago: this.metodo_pago
    };

    this.pagoService.registrarPago(pagoData).subscribe({
      next: (response: any) => {

        // si recibe un error
        if (response.status === 'error') {
          this.mensajeError = response.mensaje;
          return;
        }

        const pago = response as PagoModel;
        console.log('Se realizó el pago:', pago);

        this.mensajeError = 'Pago registrado exitosamente.';

      }


    });

  }

  cancelarReserva() {
    const reservaData: Partial<ReservaModel> = {
      numero_reserva: this.reservaAcancelar
    };

    this.reservaService.cancelarReserva(reservaData).subscribe({
      next: (response: any) => {

        // si recibe un error
        if (response.status === 'error') {
          this.mensajeError2 = response.mensaje;
          return;
        }

        const reserva = response as ReservaModel;
        console.log('Reserva cancelada:', reserva);
        this.mensajeError2 = 'Reserva cancelada exitosamente.';

      }


    });
  }

}
