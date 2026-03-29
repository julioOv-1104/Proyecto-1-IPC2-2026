import { Component } from '@angular/core';
import { MenuOperaciones } from '../../compartidos/menu-operaciones/menu-operaciones';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PaqueteModel } from '../../modelos/paquete-model';
import { PaqueteService } from '../../servicios/paquete.service';

@Component({
  selector: 'app-editar-paquete',
  imports: [MenuOperaciones, CommonModule, FormsModule],
  templateUrl: './editar-paquete.html',
  styleUrl: './editar-paquete.css',
})
export class EditarPaquete {
  constructor(private paqueteService: PaqueteService) {}

  paquetes: PaqueteModel[] = [];

  mensajeError: string | null = null;

  id_paquete: number = 0;
  id_destino: number = 0;
  nombre_paquete: string = '';
  precio_publico: number = 0;
  capacidad: number = 0;
  duracion: number = 0;
  descripcion: string = '';


  ngOnInit() {
   
    this.obtenerPaquetes();

    console.log(this.paquetes);
  }

  obtenerPaquetes() {

    this.paqueteService.obtenerPaquetes().subscribe(data => {

      this.paquetes = data;

    });

  }

  editarPaquete(){

    if(!this.id_paquete || !this.id_destino || !this.nombre_paquete || !this.precio_publico || !this.capacidad || !this.duracion || !this.descripcion){
      this.mensajeError = 'Todos los campos son obligatorios.';
      return;
    }

    if(this.precio_publico <= 0 || this.capacidad <= 0 || this.duracion <= 0 || this.id_destino <= 0 || this.id_paquete <= 0){
      this.mensajeError = 'Los campos numéricos deben ser mayores a cero.';
      return;
    }

    const paqueteAeditar: Partial<PaqueteModel> = {
      id_paquete: this.id_paquete,
      id_destino: this.id_destino,
      nombre_paquete: this.nombre_paquete,
      precio_publico: this.precio_publico,
      capacidad: this.capacidad,
      duracion: this.duracion,
      descripcion: this.descripcion  
    }

    this.paqueteService.editarPaquete(paqueteAeditar).subscribe({
      next: (response) => {

        console.log('Paquete editado:', response);
        this.mensajeError = null;
      },
      error: (error) => {
        console.error('Error al editar paquete:', error);
        this.mensajeError = 'Error al editar el paquete.';
      }
    });

  }

}
