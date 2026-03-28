import { Component } from '@angular/core';
import { MenuAdmin } from '../../compartidos/menu-admin/menu-admin';
import { ArchivoService } from '../../servicios/archivo.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-vista-admin',
  imports: [MenuAdmin, FormsModule, CommonModule],
  templateUrl: './vista-admin.html',
  styleUrl: './vista-admin.css',
})
export class VistaAdmin {

constructor(private archivoService: ArchivoService) {}

archivo!: File;

onFileSelected(event: any) {
  this.archivo = event.target.files[0];
}

prueba(){
  console.log('Archivo cargado = '+this.archivo);
}

cargarArchivo(){

  const formData = new FormData();
  formData.append('archivo', this.archivo);
  this.archivoService.cargarArchivo(formData).subscribe(
    response => {
      console.log('Archivo cargado exitosamente', response);
    },
    error => {
      console.error('Error al cargar el archivo', error);
    }
  );
}

}
