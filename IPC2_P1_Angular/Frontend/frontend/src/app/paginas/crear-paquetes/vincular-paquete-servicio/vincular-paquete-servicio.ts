import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ServicioModel } from '../../../modelos/servicio-model';
import { PaqueteService } from '../../../servicios/paquete.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-vincular-paquete-servicio',
  imports: [CommonModule, FormsModule],
  templateUrl: './vincular-paquete-servicio.html',
  styleUrl: './vincular-paquete-servicio.css',
})
export class VincularPaqueteServicio {

  constructor(private paqueteService: PaqueteService, private router: Router) {}

servicios: ServicioModel[] = [];

id_paquete: number = 0;
id_servicio: number = 0;
costo: number = 0;
mensajeError: string | null = null;

minimo: boolean = false;//para validar que al menos se haya vinculado con un servicio

  ngOnInit() {
    this.obtenerServicios();

    this.id_paquete = this.paqueteService.obtenerPaquete()?.id_paquete || 0;
    //obtiene el id del paquete creado 

     console.log('ID del paquete obtenido:', this.id_paquete);
  }

  
terminar() {
  this.router.navigate(['/crear-paquetes']);
}


  obtenerServicios() {
    this.paqueteService.obtenerServicios().subscribe(data => {
      this.servicios = data;
    });
  }

  vincularServicio() {
    this.mensajeError = null; // limpiar errores anteriores

    if (this.id_servicio === 0 || this.costo <= 0) {
      this.mensajeError = 'Por favor, complete todos los campos con valores válidos.';
      return;
    }
    
    this.paqueteService.vincularServicio(this.id_paquete, this.id_servicio, this.costo).subscribe({
                next: (response: any) => {
          
                  // si recibe un error
                  if (response.status === 'error') {
                    this.mensajeError = response.mensaje;
                    return;
                  }

                  console.log('Registro exitoso, servicio creado:');
          
                  this.mensajeError = 'Registro de servicio exitoso.';
                  this.minimo = true; // habilitar botón de terminar
          
                }
          
          
              });

  }

}
