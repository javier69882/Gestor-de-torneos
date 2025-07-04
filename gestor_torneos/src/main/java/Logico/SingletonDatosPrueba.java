package Logico;

import java.util.ArrayList;
import java.util.List;

public class SingletonDatosPrueba {
    private static SingletonDatosPrueba instancia;

    private List<Equipos> equiposCreados;
    private List<Participantes> participantesCreados;
    private Deposito<ITorneo> depositoTorneos;

    private SingletonDatosPrueba() {
        equiposCreados = new ArrayList<>();
        participantesCreados = new ArrayList<>();
        depositoTorneos = new Deposito<>();
        inicializarDatosPrueba();
    }

    public static SingletonDatosPrueba getInstancia() {
        if (instancia == null) {
            instancia = new SingletonDatosPrueba();
        }
        return instancia;
    }

    private void inicializarDatosPrueba() {
        String[] nombresEquipos = {"Águilas", "Lobos", "Tiburones", "Pumas"};
        for (String nombreEquipo : nombresEquipos) {
            Equipos equipo = new Equipos(nombreEquipo);
            equiposCreados.add(equipo);
            for (int i = 1; i <= 7; i++) {
                String nombre = "Jugador" + i;
                String apellido = nombreEquipo + i;
                String correo = nombre.toLowerCase() + i + "@" + nombreEquipo.toLowerCase() + ".com";
                Participantes p = new Participantes(apellido, nombre, correo, equipo);
                participantesCreados.add(p);
            }
        }

        List<Equipos> equiposTorneo = new ArrayList<>(equiposCreados);

        // Tipos base de torneos físicos
        ITorneo fisicoBase1 = new TorneoFisico("Torneo Físico ED", equiposTorneo, CantidadEquipos.CUATRO, "Fútbol");
        ITorneo fisicoBase2 = new TorneoFisico("Torneo Físico DE", equiposTorneo, CantidadEquipos.CUATRO, "Baloncesto");
        ITorneo fisicoBase3 = new TorneoFisico("Torneo Físico LS", equiposTorneo, CantidadEquipos.CUATRO, "Vóleibol");

        // Tipos base de torneos videojuego
        ITorneo videojuegoBase1 = new TorneoVideojuegos("Torneo Videojuego ED", equiposTorneo, CantidadEquipos.CUATRO, "FIFA");
        ITorneo videojuegoBase2 = new TorneoVideojuegos("Torneo Videojuego DE", equiposTorneo, CantidadEquipos.CUATRO, "NBA 2K");
        ITorneo videojuegoBase3 = new TorneoVideojuegos("Torneo Videojuego LS", equiposTorneo, CantidadEquipos.CUATRO, "Rocket League");

        // Decorar torneos físicos
        ITorneo torneoFisicoED = new EliminacionDirectaDecorator(fisicoBase1);
        ITorneo torneoFisicoDE = new DobleEliminacionDecorator(fisicoBase2);
        ITorneo torneoFisicoLS = new LigaSimple(fisicoBase3);

        // Decorar torneos videojuego
        ITorneo torneoVideojuegoED = new EliminacionDirectaDecorator(videojuegoBase1);
        ITorneo torneoVideojuegoDE = new DobleEliminacionDecorator(videojuegoBase2);
        ITorneo torneoVideojuegoLS = new LigaSimple(videojuegoBase3);

        // Agregar todos al depósito
        depositoTorneos.addElemento(torneoFisicoED);
        depositoTorneos.addElemento(torneoFisicoDE);
        depositoTorneos.addElemento(torneoFisicoLS);
        depositoTorneos.addElemento(torneoVideojuegoED);
        depositoTorneos.addElemento(torneoVideojuegoDE);
        depositoTorneos.addElemento(torneoVideojuegoLS);
    }




    // Getters para acceder a las listas y depósito

    public List<Equipos> getEquiposCreados() {
        return equiposCreados;
    }

    public List<Participantes> getParticipantesCreados() {
        return participantesCreados;
    }

    public Deposito<ITorneo> getDepositoTorneos() {
        return depositoTorneos;
    }
}