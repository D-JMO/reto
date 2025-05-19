package com.david.reto.Repositories;

import com.david.reto.Entities.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Long> {

    // Buscar tareas por título
    Tarea findByTitulo(String titulo);

    // Contar cuántas tareas no están completadas
    long countByCompletadaFalse();

    // Consulta Jpl para encontrar todas las tareas que pertenezcan a un mismo proyecto
    @Query("select t from Tarea t where t.proyecto.id = ?1")
    List<Tarea> findByProyecto_Id(Long id);

}