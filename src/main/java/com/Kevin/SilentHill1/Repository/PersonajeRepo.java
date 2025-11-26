package com.Kevin.SilentHill1.Repository;

import com.silenthill.model.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {

    // Buscar por nombre
    Optional<Personaje> findByNombre(String nombre);

    // Buscar por nombre que contenga un texto
    List<Personaje> findByNombreContainingIgnoreCase(String nombre);

    // Consulta JPQL personalizada
    @Query("SELECT p FROM Personaje p WHERE p.historia LIKE %:texto%")
    List<Personaje> buscarPorTextoEnHistoria(@Param("texto") String texto);
}
