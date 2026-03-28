import { Component } from '@angular/core';
import { MenuAdmin } from '../../compartidos/menu-admin/menu-admin';
import { UsuarioService } from '../../servicios/usuario.service';
import { Usuario } from '../../modelos/usuario';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-editar-usuarios',
  imports: [MenuAdmin, FormsModule, CommonModule],
  templateUrl: './editar-usuarios.html',
  styleUrl: './editar-usuarios.css',
})
export class EditarUsuarios {

  constructor(private usuarioService: UsuarioService) { }

  nombre_usuario: string = '';
  rol: number = 0
  estado: boolean = false;
  mensajeError: String | null = null;

  usuarios: Usuario[] = [];


  ngOnInit() {

    this.ObtenerUsuarios();
  }

  ObtenerUsuarios() {
    this.usuarioService.obtenerUsuarios().subscribe(data => {

      this.usuarios = data;

    });
  }

  editarUsuario() {

    this.mensajeError = null; // Limpiar mensaje de error

if(this.rol<=0 || this.rol>3){
  this.mensajeError = "El rol debe ser un numero entre 1 y 3";
  return;
}

if(this.nombre_usuario.trim() === ''){
  this.mensajeError = "El nombre de usuario no puede estar vacio";
  return;
}

    const usuarioEditado: Partial<Usuario> = {
      nombre_usuario: this.nombre_usuario,
      rol: this.rol,
      activo: this.estado
      
    }
    this.usuarioService.editarUsuario(usuarioEditado).subscribe(() => {
      this.ObtenerUsuarios();
    });
  }

}
