package com.david.reto;

import com.david.reto.Entities.Proyecto;
import com.david.reto.Entities.Tarea;
import com.david.reto.Repositories.ProyectoRepository;
import com.david.reto.Repositories.TareaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


import java.time.LocalDate;
import java.util.List;
@SpringBootApplication

public class Main {
	public static void main(String[] args) {

		// Inicializar spring
		ApplicationContext spring = SpringApplication.run(Main.class, args);

		// Obtener los repositorios
		ProyectoRepository proyectoRepository = spring.getBean(ProyectoRepository.class);
		TareaRepository tareaRepository = spring.getBean(TareaRepository.class);

		// Crear proyectos
		Proyecto proyecto1 = new Proyecto("CashApp", "Aplicación de gestión de dinero", LocalDate.of(2025, 5, 16), true);
		Proyecto proyecto2 = new Proyecto("MyDiary", "Aplicación de diario personal", LocalDate.of(2025, 5, 30), false);

		// Guardar proyectos
		proyectoRepository.saveAll(List.of(proyecto1, proyecto2));



		// Crear tareas
		Tarea tarea1 = new Tarea("Crear interfaz de usuario", "Diseñar la interfaz de usuario de la aplicación", false, proyecto1);
		Tarea tarea2 = new Tarea("Implementar lógica de negocio", "Desarrollar la lógica de negocio de la aplicación", false, proyecto1);
		Tarea tarea3 = new Tarea("Realizar pruebas", "Probar la aplicación para detectar errores", false, proyecto1);
		Tarea tarea4 = new Tarea("Escribir documentación", "Documentar el código y la aplicación", false, proyecto2);
		Tarea tarea5 = new Tarea("Crear base de datos", "Diseñar y crear la base de datos de la aplicación", false, proyecto2);



		// Guardar tareas
		tareaRepository.saveAll(List.of(tarea1, tarea2, tarea3, tarea4, tarea5));

		// Probar métodos y consultas

		// ProyectoRepository

		// Probar buscar proyecto por nombre
		String ProyectoBuscado = "CashApp";
		Proyecto proyectoEncontrado = proyectoRepository.findByNombre(ProyectoBuscado);
		if (proyectoEncontrado != null) {
			System.out.println("Proyecto encontrado: " + ProyectoBuscado +
					"': " + proyectoEncontrado.getNombre());
		} else {
			System.out.println("No se ha encontrado el proyecto con el nombre: " + ProyectoBuscado);
		}

		// Probar buscar proyecto por fecha de inicio
		LocalDate fechaInicioBuscada = LocalDate.of(2025, 5, 16);
		List<Proyecto> proyectosEncontradosPorFecha = proyectoRepository.findByFechaInicio(fechaInicioBuscada);
		System.out.println("Proyectos encontrados por fecha de inicio " + fechaInicioBuscada);
		if (proyectosEncontradosPorFecha.isEmpty()) {
			System.out.println("No se han encontrado proyectos con la fecha de inicio: " + fechaInicioBuscada);
		} else {
			for (Proyecto proyecto : proyectosEncontradosPorFecha) {
				System.out.println(proyecto.getNombre() + ": " + proyecto.getDescripcion());
			}
		}

		// Pqrobar buscar proyectos activos
		List<Proyecto> proyectosActivos = proyectoRepository.findByActivoTrue();
		if (proyectosActivos.isEmpty()) {
			System.out.println("No hay proyectos activos.");
		} else {
			for (Proyecto proyecto : proyectosActivos) {
				System.out.println(proyecto.getNombre() + ": " + proyecto.getDescripcion());
			}
		}

		// TareaRepository

		// Probar buscar tareas por título
		String tituloBuscado = "Crear interfaz de usuario";
		Tarea tareaEncontrada = tareaRepository.findByTitulo(tituloBuscado);
		if (tareaEncontrada != null) {
			System.out.println("Tarea encontrada: " + tituloBuscado +
					"': " + tareaEncontrada.getTitulo());
		}

		// Probar contar tareas no completadas
		long tareasNoCompletadas = tareaRepository.countByCompletadaFalse();
		System.out.println("Número de tareas no completadas: " + tareasNoCompletadas);

		// Probar buscar tareas por proyecto
		Long idProyectoBuscado = proyecto1.getId();
		List<Tarea> tareasPorProyecto = tareaRepository.findByProyecto_Id(idProyectoBuscado);
		if (tareasPorProyecto.isEmpty()) {
			System.out.println("No se han encontrado tareas para el proyecto con ID: " + idProyectoBuscado);
		} else {
			System.out.println("Tareas encontradas para el proyecto con ID " + idProyectoBuscado + ":");
			for (Tarea tarea : tareasPorProyecto) {
				System.out.println(tarea.getTitulo() +
						(tarea.getCompletada() ? "completada" : "no completada"));
			}
		}




	}



}

