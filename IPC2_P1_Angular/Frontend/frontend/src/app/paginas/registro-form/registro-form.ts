import { Component } from '@angular/core';
import { UsuarioService } from '../../servicios/usuario.service';
import { Usuario } from '../../modelos/usuario';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MenuAdmin } from '../../compartidos/menu-admin/menu-admin';

@Component({
  selector: 'app-registro-form',
  standalone: true,
  imports: [FormsModule, CommonModule, MenuAdmin],
  templateUrl: './registro-form.html',
  styleUrl: './registro-form.css',
})
export class RegistroForm {
  nombre_usuario = '';
  password = '';
  rol = 0;
  mensajeError: string | null = null;

  constructor(private usuarioService: UsuarioService) { }

  registrar() {

    this.mensajeError = null; // limpiar errores anteriores

    if (!this.nombre_usuario || !this.password || this.rol === 0) {
      this.mensajeError = 'Por favor, complete todos los campos.';
      return;
    }

    const nuevoUsuario = {
      nombre_usuario: this.nombre_usuario,
      password: this.password,
      rol: this.rol
    };

    this.usuarioService.registrar(nuevoUsuario).subscribe({
      next: (response: any) => {

        // si recibe un error
        if (response.status === 'error') {
          this.mensajeError = response.mensaje;
          return;
        }

        const usuario = response as Usuario;
        console.log('Registro exitoso, usuario creado:', usuario);

        // limpiar campos del formulario
        this.nombre_usuario = '';
        this.password = '';
        this.rol = 0;
        this.mensajeError = 'Registro exitoso, ahora puede iniciar sesión.';

      }


    });

  }

}