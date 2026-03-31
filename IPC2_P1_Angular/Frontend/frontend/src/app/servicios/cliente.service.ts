import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ClienteModel } from '../modelos/cliente-model';

@Injectable({
  providedIn: 'root',
})
export class ClienteService {
  
constructor(private http: HttpClient) {}

crearClienteUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/AtencionClienteServlet?accion=crear';
editarClienteUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/AtencionClienteServlet?accion=editar';
obtenerUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/AtencionClienteServlet';

obtenerClientes(): Observable<ClienteModel[]> {
  return this.http.get<ClienteModel[]>(this.obtenerUrl);
}

registrarCliente(cliente: Partial<ClienteModel>): Observable<ClienteModel> {
  return this.http.put<ClienteModel>(this.crearClienteUrl, cliente);
}

editarCliente(cliente: ClienteModel): Observable<ClienteModel> {
  return this.http.put<ClienteModel>(this.editarClienteUrl, cliente);  
}

}
