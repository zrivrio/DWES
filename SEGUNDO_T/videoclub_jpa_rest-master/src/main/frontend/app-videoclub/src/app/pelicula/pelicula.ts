import {Idioma} from "./idoma";

export interface Pelicula {

  idPelicula: number;

  titulo: string;

  descripcion: string;

  anyoLanzamiento: Date;

  idioma: Idioma;

  duracion: number;

  categoria: string;

}
