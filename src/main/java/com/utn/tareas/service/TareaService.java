package com.utn.tareas.service;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class TareaService {

    private final TareaRepository tareaRepository;

    public TareaService (TareaRepository tareaRepository){
        this.tareaRepository = tareaRepository;
    }

    public void agregarTarea (String descripcion, Prioridad prioridad){
        com.utn.tareas.model.Tarea nuevaTarea = new Tarea(0L, descripcion, false, prioridad);

        tareaRepository.guardar(nuevaTarea);
    }

    public List<Tarea> listarTodas (){
        return tareaRepository.findAll();
    }

    public List<Tarea> listarPendientes(){
        List<Tarea> todasTareas = tareaRepository.findAll();
        return todasTareas.stream()
                .filter(t -> !t.isCompletada())
                .collect(Collectors.toList());
    }

    public List<Tarea> listarCompletadas(){
        List<Tarea> todasTareas = tareaRepository.findAll();
        return todasTareas.stream()
                .filter(t -> t.isCompletada())
                .collect(Collectors.toList());
    }

    public void marcarCompletada (Long id){
        Optional<Tarea>tareaOpcional = tareaRepository.buscarPorId(id);
        tareaOpcional.ifPresent(t->t.setCompletada(true));
    }

    public String  obtenerStats (){
        int tamanioTodas = tareaRepository.findAll().size();
        int tamanioCompletadas = listarCompletadas().size();
        int tamanioPendientes = tamanioTodas - tamanioCompletadas;
        return "Estadisticas Total de tareas: "+tamanioTodas +
                "Completadas: "+tamanioCompletadas +
                "Pendientes: "+tamanioPendientes;
    }

}
