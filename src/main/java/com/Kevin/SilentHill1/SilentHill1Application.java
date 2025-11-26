package com.Kevin.SilentHill1;

import com.Kevin.SilentHill1.Entities.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SilentHill1Application {

    private static final String BASE_URL = "http://localhost:4000/api"; // Puerto 4000 según tu config

    public static void main(String[] args) {
        SpringApplication.run(SilentHill1Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner demo(RestTemplate restTemplate) {
        return (args) -> {
            System.out.println("=== SILENT HILL CRUD DEMO - PUERTO 4000 ===");
            System.out.println("Base URL: " + BASE_URL);

            // Pequeña pausa para asegurar que la aplicación esté completamente iniciada
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            try {
                // 1. CREAR UBICACIONES
                System.out.println("\n--- CREANDO UBICACIONES ---");
                Ubicacion hospital = crearUbicacion(restTemplate,
                        "Alchemilla Hospital", "Hospital", "Hospital abandonado con pasillos oscuros", 8);
                Ubicacion escuela = crearUbicacion(restTemplate,
                        "Midwich Elementary School", "Escuela", "Escuela primaria embrujada", 7);
                Ubicacion calle = crearUbicacion(restTemplate,
                        "Nathan Avenue", "Calle", "Calle principal de Silent Hill", 5);
                Ubicacion lago = crearUbicacion(restTemplate,
                        "Toluca Lake", "Exterior", "Lago tranquilo pero misterioso", 3);

                // 2. CREAR OBJETOS
                System.out.println("\n--- CREANDO OBJETOS ---");
                Objeto pistola = crearObjeto(restTemplate,
                        "Pistola 9mm", "Arma", "Pistola semiautomática", "Daño moderado", "Común");
                Objeto botiquin = crearObjeto(restTemplate,
                        "Botiquín", "Curación", "Kit de primeros auxilios", "Restaura 50% de salud", "Común");
                Objeto mapa = crearObjeto(restTemplate,
                        "Mapa de Silent Hill", "Utilidad", "Mapa detallado del pueblo", "Revela ubicaciones", "Raro");
                Objeto radio = crearObjeto(restTemplate,
                        "Radio", "Utilidad", "Radio que emite estática cerca de enemigos", "Alerta de peligro", "Común");
                Objeto flaurosa = crearObjeto(restTemplate,
                        "Flaurosa", "Arma", "Arma sagrada poderosa", "Daño alto a criaturas", "Épico");

                // 3. CREAR PERSONAJES
                System.out.println("\n--- CREANDO PERSONAJES ---");
                Personaje harry = crearPersonaje(restTemplate,
                        "Harry Mason", "Escritor que busca a su hija adoptiva Cheryl en Silent Hill");
                Personaje cybil = crearPersonaje(restTemplate,
                        "Cybil Bennett", "Oficial de policía que investiga los extraños sucesos en el pueblo");
                Personaje alessa = crearPersonaje(restTemplate,
                        "Alessa Gillespie", "Niña con poderes psíquicos que está en el centro del misterio");

                // 4. CREAR PROTAGONISTAS
                System.out.println("\n--- CREANDO PROTAGONISTAS ---");
                Protagonista protHarry = crearProtagonista(restTemplate,
                        100, "Pistola, radio, linterna, botiquín", "Pistola 9mm", "Normal", harry.getId());
                Protagonista protCybil = crearProtagonista(restTemplate,
                        120, "Revólver, esposas, radio, gas pimienta", "Revólver .38", "Difícil", cybil.getId());

                // 5. CREAR ENEMIGOS
                System.out.println("\n--- CREANDO ENEMIGOS ---");
                Enemigo nurse = crearEnemigo(restTemplate,
                        "Nurse", "Criatura", "Luz", hospital.getId());
                Enemigo greyChild = crearEnemigo(restTemplate,
                        "Grey Child", "Criatura", "Fuego", escuela.getId());
                Enemigo airScreamer = crearEnemigo(restTemplate,
                        "Air Screamer", "Bestia alada", "Armas de fuego", calle.getId());
                Enemigo romper = crearEnemigo(restTemplate,
                        "Romper", "Criatura", "Explosivos", hospital.getId());

                // 6. ASIGNAR OBJETOS A UBICACIONES (actualizando las ubicaciones)
                System.out.println("\n--- ASIGNANDO OBJETOS A UBICACIONES ---");
                asignarObjetosAUbicacion(restTemplate, hospital.getId(), Arrays.asList(pistola.getId(), botiquin.getId()));
                asignarObjetosAUbicacion(restTemplate, escuela.getId(), Arrays.asList(mapa.getId(), radio.getId()));
                asignarObjetosAUbicacion(restTemplate, calle.getId(), Arrays.asList(flaurosa.getId()));

                // 7. PRUEBAS DE CONSULTAS
                System.out.println("\n--- REALIZANDO CONSULTAS ---");

                // Consultar todos los elementos
                System.out.println("\nTodas las ubicaciones:");
                consultarTodasLasUbicaciones(restTemplate);

                System.out.println("\nTodos los enemigos:");
                consultarTodosLosEnemigos(restTemplate);

                System.out.println("\nTodos los objetos:");
                consultarTodosLosObjetos(restTemplate);

                // Consultas específicas
                System.out.println("\nEnemigos tipo 'Criatura':");
                consultarEnemigosPorTipo(restTemplate, "Criatura");

                System.out.println("\nObjetos raros o épicos:");
                consultarObjetosPorRareza(restTemplate, "Raro");
                consultarObjetosPorRareza(restTemplate, "Épico");

                System.out.println("\nProtagonistas en dificultad 'Normal':");
                consultarProtagonistasPorDificultad(restTemplate, "Normal");

                System.out.println("\nUbicaciones con peligro >= 7:");
                consultarUbicacionesPeligrosas(restTemplate, 7);

                // 8. PRUEBAS DE BÚSQUEDA
                System.out.println("\n--- BÚSQUEDAS POR TEXTO ---");
                System.out.println("\nBuscando personajes con 'Cheryl':");
                buscarPersonajesPorTexto(restTemplate, "Cheryl");

                System.out.println("\nBuscando objetos con 'pistola':");
                buscarObjetosPorTexto(restTemplate, "pistola");

                System.out.println("\nBuscando enemigos con 'air':");
                buscarEnemigosPorTexto(restTemplate, "air");

                // 9. PRUEBAS DE CONSULTAS RELACIONALES
                System.out.println("\n--- CONSULTAS RELACIONALES ---");
                System.out.println("\nObjetos en el hospital:");
                consultarObjetosEnUbicacion(restTemplate, "Alchemilla Hospital");

                System.out.println("\nEnemigos en el hospital:");
                consultarEnemigosEnUbicacion(restTemplate, "Alchemilla Hospital");

                // 10. PRUEBAS DE ACTUALIZACIÓN
                System.out.println("\n--- ACTUALIZANDO REGISTROS ---");
                if (protHarry != null) {
                    actualizarSaludProtagonista(restTemplate, protHarry.getId(), 80);
                }
                if (nurse != null) {
                    actualizarDebilidadEnemigo(restTemplate, nurse.getId(), "Luz intensa");
                }

                System.out.println("\n=== DEMO COMPLETADO EXITOSAMENTE ===");
                System.out.println("La base de datos ha sido poblada con datos de Silent Hill");
                System.out.println("Puedes probar manualmente los endpoints en: http://localhost:4000/api");

            } catch (Exception e) {
                System.err.println("Error durante la demo: " + e.getMessage());
                e.printStackTrace();
                System.out.println("Asegúrate de que:");
                System.out.println("1. MySQL/MariaDB esté ejecutándose en localhost:3306");
                System.out.println("2. La base de datos 'silent hill 1' exista");
                System.out.println("3. El usuario root no tenga contraseña (como está configurado)");
            }
        };
    }

    // MÉTODOS AUXILIARES PARA LAS OPERACIONES CRUD

    private static Ubicacion crearUbicacion(RestTemplate restTemplate, String nombre, String tipo, String descripcion, Integer peligro) {
        try {
            Ubicacion ubicacion = new Ubicacion(nombre, tipo, descripcion, peligro);
            Ubicacion resultado = restTemplate.postForObject(BASE_URL + "/ubicaciones", ubicacion, Ubicacion.class);
            System.out.println("✓ Ubicación creada: " + resultado.getNombre() + " (ID: " + resultado.getId() + ")");
            return resultado;
        } catch (Exception e) {
            System.err.println("✗ Error creando ubicación: " + nombre);
            return null;
        }
    }

    private static Objeto crearObjeto(RestTemplate restTemplate, String nombre, String tipo, String descripcion, String efecto, String rareza) {
        try {
            Objeto objeto = new Objeto(nombre, tipo, descripcion, efecto, rareza);
            Objeto resultado = restTemplate.postForObject(BASE_URL + "/objetos", objeto, Objeto.class);
            System.out.println("✓ Objeto creado: " + resultado.getNombre() + " (ID: " + resultado.getId() + ")");
            return resultado;
        } catch (Exception e) {
            System.err.println("✗ Error creando objeto: " + nombre);
            return null;
        }
    }

    private static void asignarObjetosAUbicacion(RestTemplate restTemplate, Long ubicacionId, java.util.List<Long> objetoIds) {
        try {
            // Obtener la ubicación actual
            Ubicacion ubicacion = restTemplate.getForObject(BASE_URL + "/ubicaciones/" + ubicacionId, Ubicacion.class);
            if (ubicacion != null) {
                // Crear un set de objetos (en una implementación real necesitarías obtener los objetos completos)
                Set<Objeto> objetosSet = new HashSet<>();
                for (Long objetoId : objetoIds) {
                    Objeto objeto = new Objeto();
                    objeto.setId(objetoId);
                    objetosSet.add(objeto);
                }
                ubicacion.setObjetosEncontrados(objetosSet);

                restTemplate.put(BASE_URL + "/ubicaciones/" + ubicacionId, ubicacion);
                System.out.println("✓ Objetos asignados a ubicación ID: " + ubicacionId);
            }
        } catch (Exception e) {
            System.err.println("✗ Error asignando objetos a ubicación: " + e.getMessage());
        }
    }

    private static Personaje crearPersonaje(RestTemplate restTemplate, String nombre, String historia) {
        try {
            Personaje personaje = new Personaje(nombre, historia);
            Personaje resultado = restTemplate.postForObject(BASE_URL + "/personajes", personaje, Personaje.class);
            System.out.println("✓ Personaje creado: " + resultado.getNombre() + " (ID: " + resultado.getId() + ")");
            return resultado;
        } catch (Exception e) {
            System.err.println("✗ Error creando personaje: " + nombre);
            return null;
        }
    }

    private static Protagonista crearProtagonista(RestTemplate restTemplate, Integer salud, String inventario, String arma, String dificultad, Long personajeId) {
        try {
            Protagonista protagonista = new Protagonista(salud, inventario, arma, dificultad);

            // Crear objeto Personaje con solo el ID para la relación
            Personaje personaje = new Personaje();
            personaje.setId(personajeId);
            protagonista.setPersonaje(personaje);

            Protagonista resultado = restTemplate.postForObject(BASE_URL + "/protagonistas", protagonista, Protagonista.class);
            System.out.println("✓ Protagonista creado (ID: " + resultado.getId() + ")");
            return resultado;
        } catch (Exception e) {
            System.err.println("✗ Error creando protagonista: " + e.getMessage());
            return null;
        }
    }

    private static Enemigo crearEnemigo(RestTemplate restTemplate, String nombre, String tipo, String debilidad, Long ubicacionId) {
        try {
            Enemigo enemigo = new Enemigo(nombre, tipo, debilidad);

            // Crear objeto Ubicacion con solo el ID para la relación
            Ubicacion ubicacion = new Ubicacion();
            ubicacion.setId(ubicacionId);
            enemigo.setUbicacion(ubicacion);

            Enemigo resultado = restTemplate.postForObject(BASE_URL + "/enemigos", enemigo, Enemigo.class);
            System.out.println("✓ Enemigo creado: " + resultado.getNombre() + " (ID: " + resultado.getId() + ")");
            return resultado;
        } catch (Exception e) {
            System.err.println("✗ Error creando enemigo: " + nombre);
            return null;
        }
    }

    // MÉTODOS DE CONSULTA
    private static void consultarTodasLasUbicaciones(RestTemplate restTemplate) {
        try {
            Ubicacion[] ubicaciones = restTemplate.getForObject(BASE_URL + "/ubicaciones", Ubicacion[].class);
            for (Ubicacion ubicacion : ubicaciones) {
                System.out.println(" - " + ubicacion.getNombre() + " | " + ubicacion.getTipo() + " | Peligro: " + ubicacion.getPeligroNivel());
            }
        } catch (Exception e) {
            System.err.println("✗ Error consultando ubicaciones: " + e.getMessage());
        }
    }

    private static void consultarTodosLosEnemigos(RestTemplate restTemplate) {
        try {
            Enemigo[] enemigos = restTemplate.getForObject(BASE_URL + "/enemigos", Enemigo[].class);
            for (Enemigo enemigo : enemigos) {
                System.out.println(" - " + enemigo.getNombre() + " | " + enemigo.getTipo() + " | Debilidad: " + enemigo.getDebilidad());
            }
        } catch (Exception e) {
            System.err.println("✗ Error consultando enemigos: " + e.getMessage());
        }
    }

    private static void consultarTodosLosObjetos(RestTemplate restTemplate) {
        try {
            Objeto[] objetos = restTemplate.getForObject(BASE_URL + "/objetos", Objeto[].class);
            for (Objeto objeto : objetos) {
                System.out.println(" - " + objeto.getNombre() + " | " + objeto.getTipo() + " | Rareza: " + objeto.getRareza());
            }
        } catch (Exception e) {
            System.err.println("✗ Error consultando objetos: " + e.getMessage());
        }
    }

    private static void consultarEnemigosPorTipo(RestTemplate restTemplate, String tipo) {
        try {
            Enemigo[] enemigos = restTemplate.getForObject(BASE_URL + "/enemigos/tipo/" + tipo, Enemigo[].class);
            for (Enemigo enemigo : enemigos) {
                System.out.println(" - " + enemigo.getNombre() + " - Debilidad: " + enemigo.getDebilidad());
            }
        } catch (Exception e) {
            System.err.println("✗ Error consultando enemigos por tipo: " + e.getMessage());
        }
    }

    private static void consultarObjetosPorRareza(RestTemplate restTemplate, String rareza) {
        try {
            Objeto[] objetos = restTemplate.getForObject(BASE_URL + "/objetos/rareza/" + rareza, Objeto[].class);
            for (Objeto objeto : objetos) {
                System.out.println(" - " + objeto.getNombre() + ": " + objeto.getDescripcion());
            }
        } catch (Exception e) {
            System.err.println("✗ Error consultando objetos por rareza: " + e.getMessage());
        }
    }

    private static void consultarProtagonistasPorDificultad(RestTemplate restTemplate, String dificultad) {
        try {
            Protagonista[] protagonistas = restTemplate.getForObject(BASE_URL + "/protagonistas/dificultad/" + dificultad, Protagonista[].class);
            for (Protagonista prot : protagonistas) {
                System.out.println(" - ID: " + prot.getId() + " - Salud: " + prot.getSalud() + " - Arma: " + prot.getArmaActual());
            }
        } catch (Exception e) {
            System.err.println("✗ Error consultando protagonistas por dificultad: " + e.getMessage());
        }
    }

    private static void consultarUbicacionesPeligrosas(RestTemplate restTemplate, Integer peligroMinimo) {
        try {
            Ubicacion[] ubicaciones = restTemplate.getForObject(BASE_URL + "/ubicaciones/peligro/" + peligroMinimo, Ubicacion[].class);
            for (Ubicacion ubicacion : ubicaciones) {
                System.out.println(" - " + ubicacion.getNombre() + " - Nivel peligro: " + ubicacion.getPeligroNivel());
            }
        } catch (Exception e) {
            System.err.println("✗ Error consultando ubicaciones peligrosas: " + e.getMessage());
        }
    }

    private static void consultarObjetosEnUbicacion(RestTemplate restTemplate, String nombreUbicacion) {
        try {
            Objeto[] objetos = restTemplate.getForObject(BASE_URL + "/objetos/ubicacion/" + nombreUbicacion, Objeto[].class);
            for (Objeto objeto : objetos) {
                System.out.println(" - " + objeto.getNombre() + " (" + objeto.getTipo() + ")");
            }
        } catch (Exception e) {
            System.err.println("✗ Error consultando objetos en ubicación: " + e.getMessage());
        }
    }

    private static void consultarEnemigosEnUbicacion(RestTemplate restTemplate, String nombreUbicacion) {
        try {
            Enemigo[] enemigos = restTemplate.getForObject(BASE_URL + "/enemigos/ubicacion/" + nombreUbicacion, Enemigo[].class);
            for (Enemigo enemigo : enemigos) {
                System.out.println(" - " + enemigo.getNombre() + " - " + enemigo.getTipo());
            }
        } catch (Exception e) {
            System.err.println("✗ Error consultando enemigos en ubicación: " + e.getMessage());
        }
    }

    // MÉTODOS DE ACTUALIZACIÓN
    private static void actualizarSaludProtagonista(RestTemplate restTemplate, Long id, Integer nuevaSalud) {
        try {
            Protagonista prot = restTemplate.getForObject(BASE_URL + "/protagonistas/" + id, Protagonista.class);
            if (prot != null) {
                prot.setSalud(nuevaSalud);
                restTemplate.put(BASE_URL + "/protagonistas/" + id, prot);
                System.out.println("✓ Salud actualizada para protagonista ID " + id + ": " + nuevaSalud);
            }
        } catch (Exception e) {
            System.err.println("✗ Error actualizando salud: " + e.getMessage());
        }
    }

    private static void actualizarDebilidadEnemigo(RestTemplate restTemplate, Long id, String nuevaDebilidad) {
        try {
            Enemigo enemigo = restTemplate.getForObject(BASE_URL + "/enemigos/" + id, Enemigo.class);
            if (enemigo != null) {
                enemigo.setDebilidad(nuevaDebilidad);
                restTemplate.put(BASE_URL + "/enemigos/" + id, enemigo);
                System.out.println("✓ Debilidad actualizada para enemigo ID " + id + ": " + nuevaDebilidad);
            }
        } catch (Exception e) {
            System.err.println("✗ Error actualizando debilidad: " + e.getMessage());
        }
    }

    // MÉTODOS DE BÚSQUEDA
    private static void buscarPersonajesPorTexto(RestTemplate restTemplate, String texto) {
        try {
            Personaje[] personajes = restTemplate.getForObject(BASE_URL + "/personajes/buscar/" + texto, Personaje[].class);
            for (Personaje personaje : personajes) {
                System.out.println(" - " + personaje.getNombre() + ": " + personaje.getHistoria());
            }
        } catch (Exception e) {
            System.err.println("✗ Error buscando personajes: " + e.getMessage());
        }
    }

    private static void buscarObjetosPorTexto(RestTemplate restTemplate, String texto) {
        try {
            Objeto[] objetos = restTemplate.getForObject(BASE_URL + "/objetos/buscar/" + texto, Objeto[].class);
            for (Objeto objeto : objetos) {
                System.out.println(" - " + objeto.getNombre() + " (" + objeto.getTipo() + ") - " + objeto.getEfecto());
            }
        } catch (Exception e) {
            System.err.println("✗ Error buscando objetos: " + e.getMessage());
        }
    }

    private static void buscarEnemigosPorTexto(RestTemplate restTemplate, String texto) {
        try {
            Enemigo[] enemigos = restTemplate.getForObject(BASE_URL + "/enemigos/buscar/" + texto, Enemigo[].class);
            for (Enemigo enemigo : enemigos) {
                System.out.println(" - " + enemigo.getNombre() + " | " + enemigo.getTipo() + " | " + enemigo.getDebilidad());
            }
        } catch (Exception e) {
            System.err.println("✗ Error buscando enemigos: " + e.getMessage());
        }
    }
}