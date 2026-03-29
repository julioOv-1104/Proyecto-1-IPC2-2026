import { CanActivateFn } from '@angular/router';
import { Inject } from '@angular/core';
import { Router } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  const router = Inject(Router);

  //lee el usuario en la sesion
  const usuario = sessionStorage.getItem('usuario');

  //  Si NO hay usuario
  if (!usuario) {
    router.navigate(['/login']);
    return false;
  }

  //si hay usuario, entra
  return true;
};
