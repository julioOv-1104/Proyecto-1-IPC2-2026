import { Injectable } from '@angular/core';
import { DestinoModel } from '../modelos/destino-model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DestinoService {

  constructor(private http: HttpClient) { }

  ObtenerDestinosUrl = 'http://localhost:8080/Proyecto1_IPC2_2026/DestinoServlet';


  obtenerDestinos(): Observable<DestinoModel[]> {
    return this.http.get<DestinoModel[]>(this.ObtenerDestinosUrl);
  }

}
