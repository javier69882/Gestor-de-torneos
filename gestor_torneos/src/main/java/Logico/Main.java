package Logico;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear 4 equipos
        Equipos equipo1 = new Equipos("Águilas");
        Equipos equipo2 = new Equipos("Lobos");
        Equipos equipo3 = new Equipos("Tiburones");
        Equipos equipo4 = new Equipos("Leones");

        // Crear lista de equipos
        List<Equipos> equiposParaTorneo = new ArrayList<>();
        equiposParaTorneo.add(equipo1);
        equiposParaTorneo.add(equipo2);
        equiposParaTorneo.add(equipo3);
        equiposParaTorneo.add(equipo4);

        //Crear torneo base y decorar con Doble Eliminación Ida y Vuelta
        ITorneo torneoBase = new TorneoFisico(
                "Copa Doble Eliminación Ida y Vuelta",
                equiposParaTorneo,
                CantidadEquipos.CUATRO,
                "Fútbol"
        );
        DobleEliminacionDecorator torneo = new DobleEliminacionDecorator(torneoBase);

        // Semifinales
        System.out.println("==== Semifinales (Ida y Vuelta) ====");
        List<Partido> ronda1 = torneo.getPartidosRondaActual();
        for (Partido p : ronda1) {
            System.out.println(
                    p.getNombreEquipoASeguro() + " vs " +
                            p.getNombreEquipoBSeguro() +
                            " (jugado: " + p.isJugado() + ") [" + p.getMarcadorSeguro() + "]"
            );
        }

        // Registrar resultados de ida
        torneo.registrarResultado(ronda1.get(0), 2, 1); // Águilas vs Lobos (ida)
        torneo.registrarResultado(ronda1.get(1), 3, 0); // Tiburones vs Leones (ida)
        // Registrar resultados de vuelta
        torneo.registrarResultado(ronda1.get(2), 0, 1); // Lobos vs Águilas (vuelta)
        torneo.registrarResultado(ronda1.get(3), 2, 2); // Leones vs Tiburones (vuelta)

        // Verificar partidos después de registrar resultados
        System.out.println("\n=== Partidos Semifinales después de registrar resultados ===");
        for (Partido p : ronda1) {
            System.out.println(
                    p.getNombreEquipoASeguro() + " vs " +
                            p.getNombreEquipoBSeguro() +
                            " (jugado: " + p.isJugado() + ") [" + p.getMarcadorSeguro() + "]"
            );
        }

        //Final
        System.out.println("\n==== Final (Ida y Vuelta) ====");
        List<Partido> ronda2 = torneo.getPartidosRondaActual();
        for (Partido p : ronda2) {
            System.out.println(
                    p.getNombreEquipoASeguro() + " vs " +
                            p.getNombreEquipoBSeguro() +
                            " (jugado: " + p.isJugado() + ") [" + p.getMarcadorSeguro() + "]"
            );
        }

        // Registrar resultados de la final
        torneo.registrarResultado(ronda2.get(0), 1, 2); // Tiburones vs Águilas (ida)
        torneo.registrarResultado(ronda2.get(1), 1, 1); // Águilas vs Tiburones (vuelta)

        // Verificar partidos de la final después de registrar resultados
        System.out.println("\n=== Partidos Final después de registrar resultados ===");
        for (Partido p : ronda2) {
            System.out.println(
                    p.getNombreEquipoASeguro() + " vs " +
                            p.getNombreEquipoBSeguro() +
                            " (jugado: " + p.isJugado() + ") [" + p.getMarcadorSeguro() + "]"
            );
        }

        //Mostrar campeón
        Equipos campeon = torneo.getCampeon();
        System.out.println("\n==== ¡Torneo finalizado! ====");
        System.out.println("El campeón es: " + (campeon != null ? campeon.getNombre() : "Sin definir"));
    }
}