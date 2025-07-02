package Logico;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear equipos
        Equipos equipo1 = new Equipos("Águilas");
        Equipos equipo2 = new Equipos("Lobos");

        // Crear participantes y asignarlos a equipos
        Participantes p1 = new Participantes("García", "Ana", "ana.garcia@email.com", equipo1);
        Participantes p2 = new Participantes("Pérez", "Luis", "luis.perez@email.com", equipo1);
        Participantes p3 = new Participantes("Santos", "María", "maria.santos@email.com", equipo1);

        Participantes p4 = new Participantes("Ramírez", "Carlos", "carlos.ramirez@email.com", equipo2);
        Participantes p5 = new Participantes("López", "Sofía", "sofia.lopez@email.com", equipo2);
        Participantes p6 = new Participantes("Fernández", "Pedro", "pedro.fernandez@email.com", equipo2);

        // Depósito de participantes
        Deposito<Participantes> depositoParticipantes = new Deposito<>();
        depositoParticipantes.addElemento(p1);
        depositoParticipantes.addElemento(p2);
        depositoParticipantes.addElemento(p3);
        depositoParticipantes.addElemento(p4);
        depositoParticipantes.addElemento(p5);
        depositoParticipantes.addElemento(p6);

        // Depósito de equipos
        Deposito<Equipos> depositoEquipos = new Deposito<>();
        depositoEquipos.addElemento(equipo1);
        depositoEquipos.addElemento(equipo2);

        // Crear lista de equipos a usar en torneos
        List<Equipos> equiposParaTorneo = new ArrayList<>(depositoEquipos.getElementos());

        // Crear torneo físico y añadir equipos, modalidad y cantidad equipos
        TorneoFisico torneoFisico = new TorneoFisico(
                "Copa Invierno",
                equiposParaTorneo,
                Modalidad.ELIMINACIONDIRECTA,
                CantidadEquipos.CUATRO,
                "Fútbol"
        );

        // Crear torneo videojuegos
        TorneoVideojuegos torneoVideojuegos = new TorneoVideojuegos(
                "eSports Master",
                equiposParaTorneo,
                Modalidad.LIGASIMPLE,
                CantidadEquipos.OCHO,
                "Rocket League"
        );

        // Imprimir información de participantes
        System.out.println("Participantes:");
        for (Participantes p : depositoParticipantes.getElementos()) {
            System.out.println("- " + p.getNombre() + " " + p.getApellidos() + ", Correo: " + p.getCorreo() + ", Equipo: " + p.getEquipo().getNombre());
        }

        // Imprimir información de equipos y sus participantes
        System.out.println("\nEquipos:");
        for (Equipos eq : depositoEquipos.getElementos()) {
            System.out.println("- " + eq.getNombre());
            for (Participantes part : eq.getPaticipante()) {
                System.out.println("  * " + part.getNombre() + " " + part.getApellidos());
            }
        }

        // Imprimir información del torneo físico
        System.out.println("\nTorneo Físico:");
        System.out.println("- Nombre: " + torneoFisico.getNombre());
        System.out.println("- Deporte: " + torneoFisico.getDeporte());
        System.out.println("- Modalidad: " + torneoFisico.getModalidad());
        System.out.println("- Cantidad de equipos: " + torneoFisico.getCantidadEquipos());
        System.out.println("- Equipos participantes:");
        for (Equipos eq : torneoFisico.getEquipos()) {
            System.out.println("  * " + eq.getNombre());
        }

        // Imprimir información del torneo de videojuegos
        System.out.println("\nTorneo Videojuegos:");
        System.out.println("- Nombre: " + torneoVideojuegos.getNombre());
        System.out.println("- Videojuego: " + torneoVideojuegos.getVideojuego());
        System.out.println("- Modalidad: " + torneoVideojuegos.getModalidad());
        System.out.println("- Cantidad de equipos: " + torneoVideojuegos.getCantidadEquipos());
        System.out.println("- Equipos participantes:");
        for (Equipos eq : torneoVideojuegos.getEquipos()){
            System.out.println("  * " + eq.getNombre());
        }
    }
}