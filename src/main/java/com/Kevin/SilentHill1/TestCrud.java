package com.Kevin.SilentHill1;

import com.Kevin.SilentHill1.Entities.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class TestCrud {

    private static final String BASE_URL = "http://localhost:4000/api";
    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        System.out.println("=== TESTING SILENT HILL CRUD API ===");

        try {
            // 1. TEST UBICACIONES
            testUbicaciones();

            // 2. TEST OBJETOS
            testObjetos();

            // 3. TEST PERSONAJES
            testPersonajes();

            // 4. TEST PROTAGONISTAS
            testProtagonistas();

            // 5. TEST ENEMIGOS
            testEnemigos();

            // 6. TEST CONSULTAS ESPECIALES
            testConsultasEspeciales();

        } catch (Exception e) {
            System.err.println("Error en el test: " + e.getMessage());
            System.out.println("Asegúrate de que:");
            System.out.println("1. La aplicación esté corriendo en puerto 4000");
            System.out.println("2. La base de datos esté conectada");
            System.out.println("3. Las tablas se hayan creado correctamente");
        }
    }

    private static void testUbicaciones() {
        System.out.println("\n--- TESTING UBICACIONES ---");

        try {
            // GET todas las ubicaciones
            Ubicacion[] ubicaciones = restTemplate.getForObject(BASE_URL + "/ubicaciones", Ubicacion[].class);
            System.out.println(" GET /ubicaciones - Encontradas: " + ubicaciones.length);

            // GET por ID
            if (ubicaciones.length > 0) {
                Ubicacion ubicacion = restTemplate.getForObject(BASE_URL + "/ubicaciones/" + ubicaciones[0].getId(), Ubicacion.class);
                System.out.println(" GET /ubicaciones/{id} - " + ubicacion.getNombre());
            }

            // GET por tipo
            Ubicacion[] ubicacionesTipo = restTemplate.getForObject(BASE_URL + "/ubicaciones/tipo/Hospital", Ubicacion[].class);
            System.out.println(" GET /ubicaciones/tipo/Hospital - Encontradas: " + ubicacionesTipo.length);

        } catch (Exception e) {
            System.err.println(" Error en testUbicaciones: " + e.getMessage());
        }
    }

    private static void testObjetos() {
        System.out.println("\n--- TESTING OBJETOS ---");

        try {
            // GET todos los objetos
            Objeto[] objetos = restTemplate.getForObject(BASE_URL + "/objetos", Objeto[].class);
            System.out.println("✓ GET /objetos - Encontrados: " + objetos.length);

            // GET por rareza
            Objeto[] objetosRaros = restTemplate.getForObject(BASE_URL + "/objetos/rareza/Raro", Objeto[].class);
            System.out.println(" GET /objetos/rareza/Raro - Encontrados: " + objetosRaros.length);

            // GET búsqueda por texto
            Objeto[] objetosBusqueda = restTemplate.getForObject(BASE_URL + "/objetos/buscar/pistola", Objeto[].class);
            System.out.println(" GET /objetos/buscar/pistola - Encontrados: " + objetosBusqueda.length);

        } catch (Exception e) {
            System.err.println(" Error en testObjetos: " + e.getMessage());
        }
    }

    private static void testPersonajes() {
        System.out.println("\n--- TESTING PERSONAJES ---");

        try {
            // GET todos los personajes
            Personaje[] personajes = restTemplate.getForObject(BASE_URL + "/personajes", Personaje[].class);
            System.out.println(" GET /personajes - Encontrados: " + personajes.length);

            // GET búsqueda por nombre
            Personaje[] personajesBusqueda = restTemplate.getForObject(BASE_URL + "/personajes/buscar/harry", Personaje[].class);
            System.out.println(" GET /personajes/buscar/harry - Encontrados: " + personajesBusqueda.length);

        } catch (Exception e) {
            System.err.println(" Error en testPersonajes: " + e.getMessage());
        }
    }

    private static void testProtagonistas() {
        System.out.println("\n--- TESTING PROTAGONISTAS ---");

        try {
            // GET todos los protagonistas
            Protagonista[] protagonistas = restTemplate.getForObject(BASE_URL + "/protagonistas", Protagonista[].class);
            System.out.println(" GET /protagonistas - Encontrados: " + protagonistas.length);

            // GET por dificultad
            Protagonista[] protNormal = restTemplate.getForObject(BASE_URL + "/protagonistas/dificultad/Normal", Protagonista[].class);
            System.out.println(" GET /protagonistas/dificultad/Normal - Encontrados: " + protNormal.length);

        } catch (Exception e) {
            System.err.println(" Error en testProtagonistas: " + e.getMessage());
        }
    }

    private static void testEnemigos() {
        System.out.println("\n--- TESTING ENEMIGOS ---");

        try {
            // GET todos los enemigos
            Enemigo[] enemigos = restTemplate.getForObject(BASE_URL + "/enemigos", Enemigo[].class);
            System.out.println(" GET /enemigos - Encontrados: " + enemigos.length);

            // GET por tipo
            Enemigo[] enemigosTipo = restTemplate.getForObject(BASE_URL + "/enemigos/tipo/Criatura", Enemigo[].class);
            System.out.println(" GET /enemigos/tipo/Criatura - Encontrados: " + enemigosTipo.length);

            // GET por debilidad
            Enemigo[] enemigosDebilidad = restTemplate.getForObject(BASE_URL + "/enemigos/debilidad/Fuego", Enemigo[].class);
            System.out.println(" GET /enemigos/debilidad/Fuego - Encontrados: " + enemigosDebilidad.length);

        } catch (Exception e) {
            System.err.println(" Error en testEnemigos: " + e.getMessage());
        }
    }

    private static void testConsultasEspeciales() {
        System.out.println("\n--- TESTING CONSULTAS ESPECIALES ---");

        try {
            // GET ubicaciones con objetos específicos
            Ubicacion[] ubicacionesConObjeto = restTemplate.getForObject(BASE_URL + "/ubicaciones/objeto/Pistola", Ubicacion[].class);
            System.out.println(" GET /ubicaciones/objeto/Pistola - Encontradas: " + ubicacionesConObjeto.length);

            // GET objetos en ubicación específica
            Objeto[] objetosEnUbicacion = restTemplate.getForObject(BASE_URL + "/objetos/ubicacion/Hospital", Objeto[].class);
            System.out.println(" GET /objetos/ubicacion/Hospital - Encontrados: " + objetosEnUbicacion.length);

            // GET enemigos en ubicación específica
            Enemigo[] enemigosEnUbicacion = restTemplate.getForObject(BASE_URL + "/enemigos/ubicacion/Hospital", Enemigo[].class);
            System.out.println(" GET /enemigos/ubicacion/Hospital - Encontrados: " + enemigosEnUbicacion.length);

        } catch (Exception e) {
            System.err.println(" Error en testConsultasEspeciales: " + e.getMessage());
        }
    }
}