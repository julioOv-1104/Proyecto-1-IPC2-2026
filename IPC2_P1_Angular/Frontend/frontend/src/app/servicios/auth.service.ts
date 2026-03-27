import { Injectable } from '@angular/core';
import { Usuario } from '../modelos/usuario';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private SESSION_KEY = 'usuario_Logueado';

  guardarUsuario(usuario:Usuario): void {
    const usuarioSinPassword = {
      nombre: usuario.nombre_usuario,
      id: usuario.id_usuario
    };

    sessionStorage.setItem(this.SESSION_KEY, JSON.stringify(usuarioSinPassword));

  }

  
  obtenerUsuario(): Usuario | null {
    const data = sessionStorage.getItem(this.SESSION_KEY);
    return data ? JSON.parse(data) : null;
  }

  estaLogueado(): boolean {
    return this.obtenerUsuario() !== null;
  }

  cerrarSesion(): void {
    sessionStorage.removeItem(this.SESSION_KEY);
  }
  
}
