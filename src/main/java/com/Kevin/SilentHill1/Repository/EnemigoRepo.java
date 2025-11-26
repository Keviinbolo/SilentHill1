package com.Kevin.SilentHill1.Repository;

import com.silenthill.model.Enemigo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnemigoRepository extends JpaRepository<Enemigo, Long> {

    // Buscar por nombre exacto
    Optional<Enemigo> findByNombre(String nombre);

    // Buscar por tipo de enemigo
    List<Enemigo> findByTipo(String tipo);

    // Buscar por debilidad
    List<Enemigo> findByDebilidad(String debilidad);

    // Buscar enemigos por nombre que contenga texto
    List<Enemigo> findByNombreContainingIgnoreCase(String nombre);

    // Enemigos en una ubicación específica
    @Query("SELECT e FROM Enemigo e WHERE e.ubicacion.nombre = :nombreUbicacion")
    List<Enemigo> findEnemigosEnUbicacion(@Param("nombreUbicacion") String nombreUbicacion);

    // Enemigos por tipo y ubicación
    @Query("SELECT e FROM Enemigo e WHERE e.tipo = :tipo AND e.ubicacion.id = :ubicacionId")
    List<Enemigo> findByTipoAndUbicacionId(@Param("tipo") String tipo, @Param("ubicacionId") Long ubicacionId);
}