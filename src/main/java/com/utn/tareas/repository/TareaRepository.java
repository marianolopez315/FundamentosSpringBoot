package com.utn.tareas.repository;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository

public class TareaRepository {

    List<Tarea> tareas = new ArrayList<>();

    private static AtomicLong nextID = new AtomicLong(4L);

    public TareaRepository (){
        tareas.add(new Tarea(1L,"Estudiar Spring Boot", false, Prioridad.ALTA));
        tareas.add(new Tarea(2L,"Hacer el TP", false, Prioridad.MEDIA));
        tareas.add(new Tarea(3L,"Comprar pan", true, Prioridad.BAJA));
    }

    public List<Tarea> findAll() {
        return tareas;
    }

    public void guardar (Tarea tarea){
        long nuevoId = nextID.getAndIncrement();
        tarea.setId(nuevoId);
        tareas.add(tarea);
    }

    public Optional<Tarea> buscarPorId (Long id){
        for (Tarea tarea : tareas){
            if (id == tarea.getId())
                return Optional.of(tarea);
        }
        return Optional.empty();
    }

    public void eliminarPorId (Long id){
        tareas.removeIf(tarea -> tarea.getId()==id);
    }
}