package com.Kevin.SilentHill1.Repository;

import com.Kevin.SilentHill1.Entities.Objeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ObjetoRepo extends JpaRepository<Objeto, Long> {

    // Buscar por nombre exacto
    Optional<Objeto> findByNombre(String nombre);

    // Buscar por tipo de objeto
    List<Objeto> findByTipo(String tipo);

    // Buscar por rareza
    List<Objeto> findByRareza(String rareza);

    // Buscar objetos por nombre que contenga texto
    List<Objeto> findByNombreContainingIgnoreCase(String nombre);

    // Objetos en una ubicación específica
    @Query("SELECT o FROM Objeto o JOIN o.ubicaciones u WHERE u.nombre = :nombreUbicacion")
    List<Objeto> findObjetosEnUbicacion(@Param("nombreUbicacion") String nombreUbicacion);

    // Objetos por tipo y rareza
    List<Objeto> findByTipoAndRareza(String tipo, String rareza);
}