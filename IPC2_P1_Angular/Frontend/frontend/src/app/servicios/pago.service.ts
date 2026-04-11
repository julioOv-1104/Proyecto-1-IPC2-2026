import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PagoModel } from '../modelos/pago-model';

@Injectable({
  providedIn: 'root',
})
export class PagoService {

    constructor(private http: HttpClient) { }

  url = 'http://localhost:8080/Proyecto1_IPC2_2026/AtencionClienteServlet';

   registrarPago(pago: Partial<PagoModel>):Observable<PagoModel> {
        return this.http.post<PagoModel>(this.url, pago);
      }
  
}
