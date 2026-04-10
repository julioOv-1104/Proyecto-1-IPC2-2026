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
import { ConsultarPaquete } from './paginas/consultar-paquete/consultar-paquete';
import { ConsultarDetallePaquete } from './paginas/consultar-detalle-paquete/consultar-detalle-paquete';
import { VincularPaqueteServicio } from './paginas/crear-paquetes/vincular-paquete-servicio/vincular-paquete-servicio';
import { CrearCliente } from './paginas/crear-cliente/crear-cliente';
import { CrearReservacion } from './paginas/crear-reservacion/crear-reservacion';


export const routes: Routes = [
   {path: '', redirectTo: 'login', pathMatch: 'full'}, 
  { path: 'login', component: LoginForm },
  { path: 'admin', component: VistaAdmin, canActivate: [authGuard], data:{roles: [3]} },
  {path: 'reportes-admin', component: ReportesAdmin, canActivate: [authGuard], data:{roles: [3]}},
  {path: 'editar-usuarios', component: EditarUsuarios, canActivate: [authGuard], data:{roles: [3]}},
  {path: 'crear-paquetes', component: CrearPaquetes, canActivate: [authGuard], data:{roles: [3, 2]}},
  {path: 'registro', component: RegistroForm, canActivate: [authGuard], data:{roles: [3]}},
  {path: 'consultar-detalle-paquete', component: ConsultarDetallePaquete, canActivate: [authGuard], data:{roles: [2]}},
  {path: 'crear-proveedor', component: CrearProveedor, canActivate: [authGuard], data:{roles: [2]}},
  {path: 'editar-paquete', component: EditarPaquete, canActivate: [authGuard], data:{roles: [2]}},
  {path: 'crear-reservacion', component: CrearReservacion, canActivate: [authGuard], data:{roles: [1]}},
  {path: 'crear-cliente', component: CrearCliente, canActivate: [authGuard], data:{roles: [1, 2, 3]}},
  {path: 'vincular-paquete-servicio', component: VincularPaqueteServicio, canActivate: [authGuard], data:{roles: [3, 2]}},
  {path: 'crear-destino', component: CrearDestino, canActivate: [authGuard], data:{roles: [2]}},
  {path: 'consultar-paquetes', component: ConsultarPaquete, canActivate: [authGuard], data:{roles: [2]}},
  { path: 'atencion-cliente', component: VistaAtencionCliente, canActivate: [authGuard], data:{roles: [1]} },
  { path: 'operaciones', component: VistaOperaciones, canActivate: [authGuard], data:{roles: [2]}}
];
