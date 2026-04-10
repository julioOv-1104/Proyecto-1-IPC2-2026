import { CanActivate, ActivatedRouteSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../servicios/auth.service';

@Injectable({
  providedIn: 'root'
})
export class authGuard implements CanActivate {

  constructor(private router: Router, private authService: AuthService) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {

    const usuario = JSON.parse(sessionStorage.getItem('usuario') || '{}');

    console.log('Usuario en authGuard:', usuario);

    // Verificar si está logueado
    if (!usuario) {
      this.router.navigate(['/login']);
      return false;
    }

    const rol = usuario.rol;

    const rolesPermitidos = route.data['roles'];

    if (rolesPermitidos.includes(rol)) {
      return true;
    }

    this.authService.cerrarSesion();
    this.router.navigate(['/login']);
    return false;
  }
}
