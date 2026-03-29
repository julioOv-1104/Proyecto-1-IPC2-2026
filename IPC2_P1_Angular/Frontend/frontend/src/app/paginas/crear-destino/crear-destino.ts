import { Component } from '@angular/core';
import { MenuOperaciones } from '../../compartidos/menu-operaciones/menu-operaciones';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DestinoService } from '../../servicios/destino.service';

@Component({
  selector: 'app-crear-destino',
  imports: [MenuOperaciones, CommonModule, FormsModule],
  templateUrl: './crear-destino.html',
  styleUrl: './crear-destino.css',
})
export class CrearDestino {
  constructor(private destinoService: DestinoService) {}

  mensajeError: string | null = null;
  nombre_destino: string = '';
  pais: string = '';
  descripcion: string = '';
  mejor_epoca: string = '';

  registrarDestino(){

    if (!this.nombre_destino || !this.pais || !this.descripcion || !this.mejor_epoca) {
      this.mensajeError = 'Por favor, completa todos los campos.';
      return;
    }

    const nuevoDestino = {
      nombre_destino: this.nombre_destino,
      pais: this.pais,
      descripcion: this.descripcion,
      mejor_epoca: this.mejor_epoca,
    }

    this.destinoService.registrarDestino(nuevoDestino).subscribe({
      next: (destinoRegistrado) => {
        console.log('Destino registrado:', destinoRegistrado);
        this.mensajeError = "destino registrado exitosamente";
        return;
        
      },
      error: (error) => {
        console.error('Error al registrar destino:', error);
        this.mensajeError = 'Error al registrar el destino.';
      }
    });

  }

}
