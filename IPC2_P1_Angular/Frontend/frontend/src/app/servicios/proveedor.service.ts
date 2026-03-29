import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProveedorModel } from '../modelos/proveedor-model';

@Injectable({
  providedIn: 'root',
})
export class ProveedorService {
  constructor(private http: HttpClient) { }

  url = 'http://localhost:8080/Proyecto1_IPC2_2026/ProveedoresServlet';

  registrarProveedor(proveedor: Partial<ProveedorModel>):Observable<ProveedorModel> {
      return this.http.post<ProveedorModel>(this.url, proveedor);
    }

    obtenerProveedores(): Observable<ProveedorModel[]> {
        return this.http.get<ProveedorModel[]>(this.url);
      }

  
}
