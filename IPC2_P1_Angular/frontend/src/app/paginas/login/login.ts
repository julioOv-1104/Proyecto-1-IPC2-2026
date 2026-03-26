import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  loginForm: FormGroup;
  submit = false;

  constructor(private fb: FormBuilder) {
    this.loginForm = this.fb.group({
      usuario: ['', [Validators.required]],
      contrasena: ['', [Validators.required]],
    });
  }

  onSubmit() {
    this.submit = true;
    if (this.loginForm.valid) {
      console.log('Datos del login:', this.loginForm.value);
      // Aquí irá la lógica de autenticación
    }
  }

  get usuario() {
    return this.loginForm.get('usuario');
  }

  get contrasena() {
    return this.loginForm.get('contrasena');
  }
}
