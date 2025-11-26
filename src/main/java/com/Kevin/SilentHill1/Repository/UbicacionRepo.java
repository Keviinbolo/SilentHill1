package com.Kevin.SilentHill1.Repository;

import com.Kevin.SilentHill1.Entities.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UbicacionRepo extends JpaRepository<Ubicacion, Long> {

    // Buscar por nombre exacto
    Optional<Ubicacion> findByNombre(String nombre);

    // Buscar por tipo de ubicación
    List<Ubicacion> findByTipo(String tipo);

    // Buscar ubicaciones con nivel de peligro mayor o igual
    List<Ubicacion> findByPeligroNivelGreaterThanEqual(Integer peligroNivel);

    // Buscar por nombre que contenga texto (case insensitive)
    List<Ubicacion> findByNombreContainingIgnoreCase(String nombre);

    // Ubicaciones con objetos específicos
    @Query("SELECT u FROM Ubicacion u JOIN u.objetosEncontrados o WHERE o.nombre = :nombreObjeto")
    List<Ubicacion> findUbicacionesConObjeto(@Param("nombreObjeto") String nombreObjeto);
}