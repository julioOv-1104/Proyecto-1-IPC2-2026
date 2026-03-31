import { ClienteModel } from "./cliente-model";

export interface ReservaModel {

    numero_reserva: string;
    fecha_creacion: Date | null;
    fecha_viaje: Date | null;
    id_paquete: number;
    id_usuario: number;
    cantidad_personas: number;
    costo_total: number;
    estado: string;

    clientes: ClienteModel[];
}
