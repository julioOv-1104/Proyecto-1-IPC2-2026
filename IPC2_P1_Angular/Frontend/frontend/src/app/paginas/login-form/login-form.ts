import { Component } from '@angular/core';
import { AuthService } from '../../servicios/auth.service';
import { UsuarioService } from '../../servicios/usuario.service';
import { Usuario } from '../../modelos/usuario';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login-form',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login-form.html',
  styleUrl: './login-form.css',
})
export class LoginForm {

  nombre_usuario = '';
  password = '';
  mensajeError: String | null = null;

  constructor(private usuarioService: UsuarioService, private authService: AuthService,
    private router: Router) { }

  login() {

    console.log('Intentando iniciar sesión con:', this.nombre_usuario, this.password);

    this.mensajeError = null; // Limpiar mensaje de error

    const usuarioLogin = {
      nombre_usuario: this.nombre_usuario,
      password: this.password
    }

    this.usuarioService.login(usuarioLogin).subscribe({
      next: (response: any) => {

        // si recibe un error
        if (response.status === 'error') {
          this.mensajeError = response.mensaje;
          return;
        }

        // si recibe un usuario
        const usuario = response as Usuario;

        this.authService.guardarUsuario(usuario);

        sessionStorage.setItem('usuario', JSON.stringify(response));
        console.log('Login correcto, usuario guardado en sesion:', sessionStorage.getItem('usuario'));

        console.log('rol del usuario:', response.rol);

        if(response.activo === false){
          this.mensajeError = 'Usuario inactivo. No puede acceder.';
          return;
        }

        switch (response.rol) {//redirecciona segun el rol del usuario
          case 1 : this.router.navigate(['/atencion-cliente']); break;
          case 2 : this.router.navigate(['/operaciones']); break;
          default: this.router.navigate(['/admin']); break;
        }

      }
    });

  }

}
