import { Component } from '@angular/core';
import { MenuAdmin } from '../../compartidos/menu-admin/menu-admin';
import { DestinoModel } from '../../modelos/destino-model';
import { DestinoService } from '../../servicios/destino.service';
import { PaqueteService } from '../../servicios/paquete.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PaqueteModel } from '../../modelos/paquete-model';

@Component({
  selector: 'app-crear-paquetes',
  imports: [MenuAdmin, CommonModule, FormsModule],
  templateUrl: './crear-paquetes.html',
  styleUrl: './crear-paquetes.css',
})
export class CrearPaquetes {

  constructor(private destinoService: DestinoService, private paqueteService: PaqueteService) { }

  destinos: DestinoModel[] = [];
  mensajeError: string | null = null;

  nombre_paquete: string = '';
  id_destino: number = 0;
  duracion: number = 0;
  precio_publico: number = 0;
  descripcion: string = '';
  capacidad: number = 0;

  ngOnInit() {

    this.obtenerDestinos();

  }

  obtenerDestinos() {
    this.destinoService.obtenerDestinos().subscribe(data => {

      this.destinos = data;

    });
  }

  registrarPaquete(){


    if (!this.nombre_paquete || !this.id_destino || !this.duracion || !this.precio_publico || !this.descripcion || !this.capacidad) {
      this.mensajeError = 'Por favor, completa todos los campos.';
      return;
    }

    if (this.id_destino <=0|| this.precio_publico <=0 || this.capacidad <=0 || this.duracion <=0){
      this.mensajeError = 'No puede ingresar valores negativos o cero';
    }


    const nuevoPaquete: Partial<PaqueteModel>={
      nombre_paquete: this.nombre_paquete,
      id_destino: this.id_destino,
      duracion: this.duracion,
      precio_publico: this.precio_publico,
      descripcion: this.descripcion,
      capacidad: this.capacidad  
    }

    this.paqueteService.registrarPaquete(nuevoPaquete).subscribe({
          next: (response: any) => {
    
            // si recibe un error
            if (response.status === 'error') {
              this.mensajeError = response.mensaje;
              return;
            }
    
            
            console.log('Registro exitoso, Paquete creado:', response);
    
            // limpiar campos del formulario
            this.nombre_paquete = '';
            this.id_destino = 0;
            this.duracion = 0;
            this.precio_publico = 0;
            this.descripcion = '';
            this.capacidad = 0;
            this.mensajeError = 'Registro de Paquete exitoso.';
    
          }


    });

  }

}
