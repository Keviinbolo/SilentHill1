package com.Kevin.SilentHill1;

import com.Kevin.SilentHill1.DTO.PersonajeDTO;
import com.Kevin.SilentHill1.Entities.Personaje;
import com.Kevin.SilentHill1.Repository.PersonajeRepo;
import com.Kevin.SilentHill1.Service.PersonajeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonajeServiceTest {

    @Mock
    private PersonajeRepo personajeRepo;

    @InjectMocks
    private PersonajeService personajeService;

    private Personaje personaje;
    private PersonajeDTO personajeDTO;

    @BeforeEach
    void setUp() {
        personaje = new Personaje();
        personaje.setId(1L);
        personaje.setNombre("Harry Mason");
        personaje.setHistoria("Busca a su hija");

        personajeDTO = new PersonajeDTO(1L, "Harry Mason", "Busca a su hija");
    }

    @Test
    void createPersonaje_ShouldReturnPersonajeDTO() {
        when(personajeRepo.save(any(Personaje.class))).thenReturn(personaje);

        PersonajeDTO result = personajeService.createPersonaje(personajeDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Harry Mason", result.getNombre());
        verify(personajeRepo, times(1)).save(any(Personaje.class));
    }

    @Test
    void getPersonajeById_ShouldReturnPersonajeDTO() {
        when(personajeRepo.findById(1L)).thenReturn(Optional.of(personaje));

        PersonajeDTO result = personajeService.getPersonajeById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Harry Mason", result.getNombre());
        verify(personajeRepo, times(1)).findById(1L);
    }

    @Test
    void getPersonajeById_NotFound_ShouldThrowException() {
        when(personajeRepo.findById(99L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () ->
                personajeService.getPersonajeById(99L)
        );

        assertTrue(exception.getMessage().contains("no encontrado"));
        verify(personajeRepo, times(1)).findById(99L);
    }

    @Test
    void getAllPersonajes_ShouldReturnList() {
        when(personajeRepo.findAll()).thenReturn(Arrays.asList(personaje));

        List<PersonajeDTO> result = personajeService.getAllPersonajes();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Harry Mason", result.get(0).getNombre());
        verify(personajeRepo, times(1)).findAll();
    }

    @Test
    void updatePersonaje_ShouldReturnUpdatedDTO() {
        PersonajeDTO updateDTO = new PersonajeDTO(1L, "Harry Updated", "Nueva historia");
        Personaje updatedPersonaje = new Personaje();
        updatedPersonaje.setId(1L);
        updatedPersonaje.setNombre("Harry Updated");
        updatedPersonaje.setHistoria("Nueva historia");

        when(personajeRepo.findById(1L)).thenReturn(Optional.of(personaje));
        when(personajeRepo.save(any(Personaje.class))).thenReturn(updatedPersonaje);

        PersonajeDTO result = personajeService.updatePersonaje(1L, updateDTO);

        assertNotNull(result);
        assertEquals("Harry Updated", result.getNombre());
        assertEquals("Nueva historia", result.getHistoria());
        verify(personajeRepo, times(1)).findById(1L);
        verify(personajeRepo, times(1)).save(any(Personaje.class));
    }

    @Test
    void deletePersonaje_ShouldCallDelete() {
        when(personajeRepo.existsById(1L)).thenReturn(true);
        doNothing().when(personajeRepo).deleteById(1L);

        personajeService.deletePersonaje(1L);

        verify(personajeRepo, times(1)).existsById(1L);
        verify(personajeRepo, times(1)).deleteById(1L);
    }
}