package com.Kevin.SilentHill1;

import com.Kevin.SilentHill1.Entities.*;
import com.Kevin.SilentHill1.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
public class SilentHill1Application {

    public static void main(String[] args) {
        SpringApplication.run(SilentHill1Application.class, args);
    }

    @Bean
    public CommandLineRunner init(
            UbicacionRepo ubicacionRepository,
            ObjetoRepo objetoRepository,
            PersonajeRepo personajeRepository,
            ProtagonistaRepo protagonistaRepository,
            EnemigoRepo enemigoRepository) {

        return args -> {
            System.out.println(" === INICIALIZANDO BASE DE DATOS SILENT HILL === ");

            // Limpiar datos existentes primero
            System.out.println(" Limpiando datos existentes...");
            enemigoRepository.deleteAll();
            protagonistaRepository.deleteAll();
            personajeRepository.deleteAll();
            objetoRepository.deleteAll();
            ubicacionRepository.deleteAll();

            // 1. CREAR UBICACIONES
            System.out.println(" Creando ubicaciones...");
            Ubicacion hospital = ubicacionRepository.save(new Ubicacion(
                    "Alchemilla Hospital", "Hospital", "Hospital abandonado con pasillos oscuros", 8));
            Ubicacion escuela = ubicacionRepository.save(new Ubicacion(
                    "Midwich Elementary School", "Escuela", "Escuela primaria embrujada", 7));
            Ubicacion calle = ubicacionRepository.save(new Ubicacion(
                    "Nathan Avenue", "Calle", "Calle principal de Silent Hill", 5));
            Ubicacion lago = ubicacionRepository.save(new Ubicacion(
                    "Toluca Lake", "Exterior", "Lago tranquilo pero misterioso", 3));
            Ubicacion motel = ubicacionRepository.save(new Ubicacion(
                    "Lakeview Hotel", "Motel", "Motel abandonado con habitaciones siniestras", 6));

            System.out.println(" Ubicaciones creadas: " + ubicacionRepository.count());

            // 2. CREAR OBJETOS
            System.out.println(" Creando objetos...");
            Objeto pistola = objetoRepository.save(new Objeto(
                    "Pistola 9mm", "Arma", "Pistola semiautomática", "Daño moderado", "Común"));
            Objeto botiquin = objetoRepository.save(new Objeto(
                    "Botiquín", "Curación", "Kit de primeros auxilios", "Restaura 50% de salud", "Común"));
            Objeto mapa = objetoRepository.save(new Objeto(
                    "Mapa de Silent Hill", "Utilidad", "Mapa detallado del pueblo", "Revela ubicaciones", "Raro"));
            Objeto radio = objetoRepository.save(new Objeto(
                    "Radio", "Utilidad", "Radio que emite estática cerca de enemigos", "Alerta de peligro", "Común"));
            Objeto flaurosa = objetoRepository.save(new Objeto(
                    "Flaurosa", "Arma", "Arma sagrada poderosa", "Daño alto a criaturas", "Épico"));
            Objeto linterna = objetoRepository.save(new Objeto(
                    "Linterna", "Utilidad", "Fuente de luz en la oscuridad", "Ilumina áreas oscuras", "Común"));
            Objeto cuchillo = objetoRepository.save(new Objeto(
                    "Cuchillo de cocina", "Arma", "Cuchillo afilado", "Daño bajo pero rápido", "Común"));

            System.out.println(" Objetos creados: " + objetoRepository.count());

            // 3. ASIGNAR OBJETOS A UBICACIONES
            System.out.println(" Asignando objetos a ubicaciones...");
            hospital.setObjetosEncontrados(new HashSet<>(Arrays.asList(pistola, botiquin, radio, cuchillo)));
            escuela.setObjetosEncontrados(new HashSet<>(Arrays.asList(mapa, linterna)));
            calle.setObjetosEncontrados(new HashSet<>(Arrays.asList(flaurosa)));
            motel.setObjetosEncontrados(new HashSet<>(Arrays.asList(botiquin, linterna)));

            ubicacionRepository.save(hospital);
            ubicacionRepository.save(escuela);
            ubicacionRepository.save(calle);
            ubicacionRepository.save(motel);

            System.out.println(" Objetos asignados a ubicaciones");

            // 4. CREAR PERSONAJES
            System.out.println(" Creando personajes...");
            Personaje harry = personajeRepository.save(new Personaje(
                    "Harry Mason", "Escritor que busca a su hija adoptiva Cheryl en Silent Hill. Hombre común atrapado en una pesadilla sobrenatural."));
            Personaje cybil = personajeRepository.save(new Personaje(
                    "Cybil Bennett", "Oficial de policía que investiga los extraños sucesos en el pueblo. Escéptica pero determinada."));
            Personaje alessa = personajeRepository.save(new Personaje(
                    "Alessa Gillespie", "Niña con poderes psíquicos que está en el centro del misterio de Silent Hill. Víctima de un ritual de culto."));
            Personaje dahlia = personajeRepository.save(new Personaje(
                    "Dahlia Gillespie", "Líder de un culto religioso. Madre de Alessa y principal antagonista."));
            Personaje kaufmann = personajeRepository.save(new Personaje(
                    "Dr. Michael Kaufmann", "Médico corrupto del hospital Alchemilla. Involucrado en el tráfico de una droga llamada PTV."));

            System.out.println(" Personajes creados: " + personajeRepository.count());

            // 5. CREAR PROTAGONISTAS
            System.out.println(" Creando protagonistas...");
            Protagonista protHarry = new Protagonista(100, "Pistola, radio, linterna, botiquín, mapa", "Pistola 9mm", "Normal");
            protHarry.setPersonaje(harry);
            protagonistaRepository.save(protHarry);

            Protagonista protCybil = new Protagonista(120, "Revólver, esposas, radio, gas pimienta, botiquín", "Revólver .38", "Difícil");
            protCybil.setPersonaje(cybil);
            protagonistaRepository.save(protCybil);

            System.out.println(" Protagonistas creados: " + protagonistaRepository.count());

            // 6. CREAR ENEMIGOS
            System.out.println(" Creando enemigos...");
            Enemigo nurse = new Enemigo("Nurse", "Criatura", "Luz");
            nurse.setUbicacion(hospital);
            enemigoRepository.save(nurse);

            Enemigo greyChild = new Enemigo("Grey Child", "Criatura", "Fuego");
            greyChild.setUbicacion(escuela);
            enemigoRepository.save(greyChild);

            Enemigo airScreamer = new Enemigo("Air Screamer", "Bestia alada", "Armas de fuego");
            airScreamer.setUbicacion(calle);
            enemigoRepository.save(airScreamer);

            Enemigo romper = new Enemigo("Romper", "Criatura", "Explosivos");
            romper.setUbicacion(hospital);
            enemigoRepository.save(romper);

            Enemigo mumblers = new Enemigo("Mumblers", "Humanoide", "Cabeza");
            mumblers.setUbicacion(motel);
            enemigoRepository.save(mumblers);

            Enemigo giantWorm = new Enemigo("Giant Worm", "Bestia", "Fuego");
            giantWorm.setUbicacion(lago);
            enemigoRepository.save(giantWorm);

            System.out.println(" Enemigos creados: " + enemigoRepository.count());

            // MOSTRAR RESUMEN
            System.out.println("\n === BASE DE DATOS INICIALIZADA EXITOSAMENTE === ");
            System.out.println(" RESUMEN DE DATOS CREADOS:");
            System.out.println("    Ubicaciones: " + ubicacionRepository.count());
            System.out.println("    Objetos: " + objetoRepository.count());
            System.out.println("    Personajes: " + personajeRepository.count());
            System.out.println("    Protagonistas: " + protagonistaRepository.count());
            System.out.println("    Enemigos: " + enemigoRepository.count());

            System.out.println("\n ENDPOINTS DISPONIBLES:");
            System.out.println("   http://localhost:4000/api/ubicaciones");
            System.out.println("   http://localhost:4000/api/objetos");
            System.out.println("   http://localhost:4000/api/personajes");
            System.out.println("   http://localhost:4000/api/protagonistas");
            System.out.println("   http://localhost:4000/api/enemigos");

            System.out.println("\n La aplicación está lista para usar!");
            System.out.println(" Puedes probar los endpoints en tu navegador o con Postman");

            // Mantener la aplicación corriendo
            System.out.println("\n La aplicación seguirá corriendo. Presiona Ctrl+C para detenerla.");
        };
    }
}