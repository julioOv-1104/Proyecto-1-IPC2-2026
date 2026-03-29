import { Component } from '@angular/core';
import { MenuOperaciones } from '../../compartidos/menu-operaciones/menu-operaciones';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { DestinoModel } from '../../modelos/destino-model';
import { DestinoService } from '../../servicios/destino.service';

@Component({
  selector: 'app-vista-operaciones',
  imports: [MenuOperaciones, FormsModule, CommonModule],
  templateUrl: './vista-operaciones.html',
  styleUrl: './vista-operaciones.css',
})
export class VistaOperaciones {

  constructor(private destinoService: DestinoService) { }

   mensajeError: string | null = null;

  destinos: DestinoModel[] = [];

  nombre_destino: string = '';
  pais: string = ''
  descripcion: string = '';
  id_destino: number = 0;
  mejor_epoca: string = '';

  ngOnInit() {

    this.obtenerDestinos();
  }

  
  obtenerDestinos() {
    this.destinoService.obtenerDestinos().subscribe(data => {

      this.destinos = data;

    });
  }

  editarDestino(){

    if (!this.nombre_destino || !this.pais || !this.descripcion || !this.mejor_epoca) {
      this.mensajeError = 'Por favor, complete todos los campos.';
      return;
    }

    if (this.id_destino <= 0) {
      this.mensajeError = 'ID de destino no válido. Por favor, ingrese un ID válido.';
      return;
    }

    const destinoEditado: Partial<DestinoModel> = {
      id_destino: this.id_destino,
      nombre_destino: this.nombre_destino,
      pais: this.pais,
      descripcion: this.descripcion,
      mejor_epoca: this.mejor_epoca
    }

    this.destinoService.editarDestino(destinoEditado).subscribe({
      next: (response) => {
        console.log('Destino editado exitosamente', response);
        this.obtenerDestinos();
        return;
      },
      error: (error) => {
        console.error('Error al editar el destino', error);
        this.mensajeError = 'Error al editar el destino. Por favor, intenta nuevamente.';
      }
    });

  }


}
