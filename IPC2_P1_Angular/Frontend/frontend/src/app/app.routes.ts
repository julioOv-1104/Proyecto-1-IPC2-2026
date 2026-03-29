import { Routes } from '@angular/router';
import { LoginForm } from './paginas/login-form/login-form';
import { VistaAdmin } from './paginas/vista-admin/vista-admin';
import { VistaAtencionCliente } from './paginas/vista-atencion-cliente/vista-atencion-cliente';
import { VistaOperaciones } from './paginas/vista-operaciones/vista-operaciones';
import { ReportesAdmin } from './paginas/reportes-admin/reportes-admin';
import { EditarUsuarios } from './paginas/editarr-usuarios/editar-usuarios';
import { CrearPaquetes } from './paginas/crear-paquetes/crear-paquetes';
import { RegistroForm } from './paginas/registro-form/registro-form';
import { authGuard } from './guard/auth-guard';
import { CrearDestino } from './paginas/crear-destino/crear-destino';
import { EditarPaquete } from './paginas/editar-paquete/editar-paquete';
import { CrearProveedor } from './paginas/crear-proveedor/crear-proveedor';


export const routes: Routes = [
   {path: '', redirectTo: 'login', pathMatch: 'full'}, 
  { path: 'login', component: LoginForm },
  { path: 'admin', component: VistaAdmin, canActivate: [authGuard], data:{rol: 3} },
  {path: 'reportes-admin', component: ReportesAdmin},
  {path: 'editar-usuarios', component: EditarUsuarios},
  {path: 'crear-paquetes', component: CrearPaquetes},
  {path: 'registro', component: RegistroForm},
  {path: 'crear-proveedor', component: CrearProveedor},
  {path: 'editar-paquete', component: EditarPaquete},
  {path: 'crear-destino', component: CrearDestino},
  { path: 'atencion-cliente', component: VistaAtencionCliente, canActivate: [authGuard], data:{rol: 1} },
  { path: 'operaciones', component: VistaOperaciones }
];
