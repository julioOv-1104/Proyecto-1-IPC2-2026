import { Component } from '@angular/core';
import { MenuOperaciones } from '../../compartidos/menu-operaciones/menu-operaciones';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProveedorService } from '../../servicios/proveedor.service';
import { ProveedorModel } from '../../modelos/proveedor-model';

@Component({
  selector: 'app-crear-proveedor',
  imports: [MenuOperaciones, CommonModule, FormsModule],
  templateUrl: './crear-proveedor.html',
  styleUrl: './crear-proveedor.css',
})
export class CrearProveedor {

  constructor(private proveedorService: ProveedorService) {}

  proveedores: ProveedorModel[] = [];

  mensajeError: string | null = null;

  //id_proveedor: number = 0;
  nombre_proveedor: string = ''
  id_tipo_servicio: number = 0;
  pais: string = '';
  contacto: string = '';


  ngOnInit() {
    this.obtenerProveedores();
    
  }

  obtenerProveedores() {
    this.proveedorService.obtenerProveedores().subscribe(data => {
      this.proveedores = data;
    });
  }

  registrarProveedor() {

    this.mensajeError = null; // limpiar errores anteriores

if (!this.nombre_proveedor || this.id_tipo_servicio === 0 || !this.pais || !this.contacto) {
  this.mensajeError = 'Por favor, complete todos los campos.';
  return;
}

if (this.id_tipo_servicio <=0) {
  this.mensajeError = 'El ID del tipo de servicio debe ser un número valido';
  return;//por si elige un id fuera del rango permitido.
}

    const nuevoProveedor: Partial<ProveedorModel> = {
      nombre_proveedor:this.nombre_proveedor,
      id_tipo_servicio: this.id_tipo_servicio,
      pais: this.pais,
      contacto: this.contacto
    };

      this.proveedorService.registrarProveedor(nuevoProveedor).subscribe({
            next: (response: any) => {
      
              // si recibe un error
              if (response.status === 'error') {
                this.mensajeError = response.mensaje;
                return;
              }
      
              const proveedor = response as ProveedorModel;
              console.log('Registro exitoso, proveedor creado:', proveedor);
      
              // limpiar campos del formulario
              this.nombre_proveedor = '';
              this.id_tipo_servicio = 0;
              this.pais = '';
              this.contacto = '';
              this.mensajeError = 'Registro de proveedor exitoso.';
      
            }
      
      
          });
    }
  }


