import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ArchivoService {

  constructor(private http: HttpClient) { }

  url = 'http://localhost:8080/Proyecto1_IPC2_2026/CargaArchivoServlet';

  cargarArchivo(formData: FormData) {
    return this.http.post(this.url, formData);
  }

}
