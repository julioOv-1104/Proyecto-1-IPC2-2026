import { Component } from '@angular/core';
import { MenuAtencion } from '../../compartidos/menu-atencion/menu-atencion';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ClienteModel } from '../../modelos/cliente-model';
import { ClienteService } from '../../servicios/cliente.service';

@Component({
  selector: 'app-crear-cliente',
  imports: [MenuAtencion, CommonModule, FormsModule],
  templateUrl: './crear-cliente.html',
  styleUrl: './crear-cliente.css',
})
export class CrearCliente {

  constructor(private clienteService: ClienteService) {}

    mensajeError: string | null = null;

    dpi: string = '';
    nombre_cliente: string = '';
    fecha_nacimiento: Date | null = null;
    telefono: string = '';
    email: string = ''
    nacionalidad: string = '';

    registrarCliente(){

      if (!this.dpi || !this.nombre_cliente || !this.fecha_nacimiento || !this.telefono || !this.email || !this.nacionalidad) {
        this.mensajeError = 'Por favor, completa todos los campos.';
        return;
      }

      const nuevoCliente: ClienteModel={
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
