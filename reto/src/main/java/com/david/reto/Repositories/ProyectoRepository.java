package com.david.reto.Repositories;

import com.david.reto.Entities.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

    // Buscar proyectos por su nombre
    Proyecto findByNombre(String nombre);

    //Buscar proyectos por fecha de inicio
    List<Proyecto> findByFechaInicio(LocalDate fechaInicio);

    // Consulta JPQL para encontrar proyectos activos
    @Query("select p from Proyecto p where p.activo = true")
    List<Proyecto> findByActivoTrue();

}
