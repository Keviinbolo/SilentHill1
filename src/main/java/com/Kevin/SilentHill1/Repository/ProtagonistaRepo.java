package com.Kevin.SilentHill1.Repository;

import com.Kevin.SilentHill1.Entities.Protagonista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProtagonistaRepo extends JpaRepository<Protagonista, Long> {

    // Buscar por nivel de dificultad
    List<Protagonista> findByNivelDificultad(String nivelDificultad);

    // Buscar protagonistas con salud mayor a un valor
    List<Protagonista> findBySaludGreaterThan(Integer salud);

    // Buscar por arma actual
    List<Protagonista> findByArmaActual(String armaActual);

    // Consulta personalizada con JOIN
    @Query("SELECT p FROM Protagonista p JOIN p.personaje per WHERE per.nombre = :nombrePersonaje")
    Optional<Protagonista> findByNombrePersonaje(@Param("nombrePersonaje") String nombrePersonaje);
}