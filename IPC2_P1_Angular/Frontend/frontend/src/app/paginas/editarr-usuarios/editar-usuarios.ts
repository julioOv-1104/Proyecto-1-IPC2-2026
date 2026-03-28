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

  usuarios: Usuario[] = [];


  prueba() {
    console.log(this.nombre_usuario);
    console.log(this.rol);
    console.log(this.estado);
  }


  ngOnInit() {

    this.ObtenerUsuarios();
  }

  ObtenerUsuarios() {
    this.usuarioService.obtenerUsuarios().subscribe(data => {

      this.usuarios = data;

    });
  }

  editarUsuario() {
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
