import { Component } from '@angular/core';
import { MenuAtencion } from '../../compartidos/menu-atencion/menu-atencion';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ClienteModel } from '../../modelos/cliente-model';
import { ClienteService } from '../../servicios/cliente.service';

@Component({
  selector: 'app-vista-atencion-cliente',
  imports: [MenuAtencion, CommonModule, FormsModule],
  templateUrl: './vista-atencion-cliente.html',
  styleUrl: './vista-atencion-cliente.css',
})
export class VistaAtencionCliente {

  constructor(private clienteService: ClienteService) { }

  clientes: ClienteModel[] = [];

  mensajeError: string | null = null;

  dpi: string = '';
  nombre_cliente: string = '';
  fecha_nacimiento: Date | null = null;
  telefono: string = '';
  email: string = ''
  nacionalidad: string = '';


  ngOnInit() {

    this.obtenerClientes();

    console.log(this.clientes);
  }

  obtenerClientes() {

    this.clienteService.obtenerClientes().subscribe(data => {

      this.clientes = data;

    });

  }

  editarCliente() {

    const clienteAeditar: Partial<ClienteModel> = {
      dpi: this.dpi,
      nombre_cliente: this.nombre_cliente,
      fecha_nacimiento: this.fecha_nacimiento,
      telefono: this.telefono,
      email: this.email,
      nacionalidad: this.nacionalidad
    }

    this.clienteService.editarCliente(clienteAeditar as ClienteModel).subscribe({
            next: (response: any) => {
      
              // si recibe un error
              if (response.status === 'error') {
                this.mensajeError = response.mensaje;
                return;
              }
      
              const cliente = response as ClienteModel;
              console.log('Se edito, cliente:', cliente);
      
              // limpiar campos del formulario
              this.dpi = '';
              this.nombre_cliente = '';
              this.fecha_nacimiento = null;
              this.telefono = '';
              this.email = '';
              this.nacionalidad = '';
              this.mensajeError = 'Edición exitosa, cliente editado correctamente.';
      
            }
      
      
          });

  }


}
