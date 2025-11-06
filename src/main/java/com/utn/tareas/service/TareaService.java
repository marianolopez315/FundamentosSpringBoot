package com.utn.tareas.service;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service


public class TareaService {
    @Value("${app.nombre}")
    private String nombreApp;

    @Value("${app.max-tareas:100}")
    private int maxTareas;

    @Value("${app.mostrar-estadisticas: true}")
    private boolean mostrarEstadisticas;

    private final TareaRepository tareaRepository;

    public TareaService (TareaRepository tareaRepository){
        this.tareaRepository = tareaRepository;
    }

    public void agregarTarea (String descripcion, Prioridad prioridad){
        if (tareaRepository.findAll().size() < maxTareas) {
            com.utn.tareas.model.Tarea nuevaTarea = new Tarea(0L, descripcion, false, prioridad);

            tareaRepository.guardar(nuevaTarea);
        } else {
            System.out.println("Error: límite máximo de tareas: "+maxTareas);
        }

    }

    public void imprimirConfiguracion(){
        System.out.println("Nombre de la aplicación: "+nombreApp);
        System.out.println("Máximo de tareas en lista: "+maxTareas);
        System.out.println("¿Se muestran las estadisticas?: "+mostrarEstadisticas);
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
