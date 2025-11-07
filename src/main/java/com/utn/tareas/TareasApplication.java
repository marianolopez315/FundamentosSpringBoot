package com.utn.tareas;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.service.MensajeService;
import com.utn.tareas.service.TareaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TareasApplication implements CommandLineRunner {

    private final MensajeService mensajeService;
    private final TareaService tareaService;

    public TareasApplication (TareaService tareaService, MensajeService mensajeService){
        this.tareaService = tareaService;
        this.mensajeService = mensajeService;
    }

	public static void main(String[] args) {
		SpringApplication.run(TareasApplication.class, args);
	}

    @Override
    public void run(String...args)throws Exception{

        //Mensaje de Bienvenida
        mensajeService.mostrarBienvenida();

        //Configuracion Actual
        tareaService.imprimirConfiguracion();

        //Listar todas las Tareas
        System.out.println(tareaService.listarTodas());

        //Agregar Tarea
        System.out.println("Agreando Tarea...");
        tareaService.agregarTarea("Revisar Parte 6", Prioridad.MEDIA);
        System.out.println("Tarea Agregada!");

        //Listar Tareas Pendientes
        System.out.println(tareaService.listarPendientes());

        //Marcar como Completada
        long idParaCompletar = 1L;
        tareaService.marcarCompletada(idParaCompletar);
        System.out.println("Tarea con ID: "+idParaCompletar+", completada");

        //Mostrar Estadisticas
        System.out.println(tareaService.obtenerStats());

        //Listar Tareas Completadas
        System.out.println(tareaService.listarCompletadas());

        //Mensaje de Despedida
        mensajeService.mostrarDespedida();
    }


}
