import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-menu-operaciones',
  imports: [RouterLink],
  templateUrl: './menu-operaciones.html',
  styleUrl: './menu-operaciones.css',
})
export class MenuOperaciones {

    constructor(private router: Router) {} //inyuectar el router

  isOpen: boolean = false;

  toggleMenu() {
    this.isOpen = !this.isOpen;
  }

  logout() {
    sessionStorage.removeItem('usuario');
    this.router.navigate(['/login']);
  }

}
