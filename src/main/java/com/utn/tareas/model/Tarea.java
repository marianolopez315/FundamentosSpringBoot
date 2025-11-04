package com.utn.tareas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString

public class Tarea {
    private long id;
    private String descripcion;
    private boolean completada;
    private Prioridad prioridad;
}
