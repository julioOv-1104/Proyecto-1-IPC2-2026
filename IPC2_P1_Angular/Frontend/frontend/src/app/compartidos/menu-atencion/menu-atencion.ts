import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-menu-atencion',
  imports: [RouterLink],
  templateUrl: './menu-atencion.html',
  styleUrl: './menu-atencion.css',
})
export class MenuAtencion {

  constructor(private router: Router) { } //inyuectar el router

  isOpen: boolean = false;

  toggleMenu() {
    this.isOpen = !this.isOpen;
  }

  logout() {
    sessionStorage.removeItem('usuario');
    this.router.navigate(['/login']);
  }

}
