package Logico;

import java.util.ArrayList;
import java.util.List;

/**
 * clase singleton para crear y almacenar datos de prueba iniciales
 * usa el patron singleton
 * @pattern singleton
 */
public class SingletonDatosPrueba {
    private static SingletonDatosPrueba instancia;

    private List<Equipos> equiposCreados;
    private List<Participantes> participantesCreados;
    private Deposito<ITorneo> depositoTorneos;

    /**
     * constructor privado, inicializa los datos de prueba
     */
    private SingletonDatosPrueba() {
        equiposCreados = new ArrayList<>();
        participantesCreados = new ArrayList<>();
        depositoTorneos = new Deposito<>();
        inicializarDatosPrueba();
    }

    /**
     * retorna la unica instancia de la clase
     * @return instancia unica de SingletonDatosPrueba
     */
    public static SingletonDatosPrueba getInstancia() {
        if (instancia == null) {
            instancia = new SingletonDatosPrueba();
        }
        return instancia;
    }

    // inicializa equipos, participantes y torneos de prueba
    private void inicializarDatosPrueba() {
        String[] nombresEquipos = {"Aguilas", "Lobos", "Tiburones", "Pumas"};
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

        ITorneo fisicoBase1 = new TorneoFisico("Torneo Fisico ED", equiposTorneo, CantidadEquipos.CUATRO, "Futbol");
        ITorneo fisicoBase2 = new TorneoFisico("Torneo Fisico DE", equiposTorneo, CantidadEquipos.CUATRO, "Baloncesto");
        ITorneo fisicoBase3 = new TorneoFisico("Torneo Fisico LS", equiposTorneo, CantidadEquipos.CUATRO, "Voleibol");

        ITorneo videojuegoBase1 = new TorneoVideojuegos("Torneo Videojuego ED", equiposTorneo, CantidadEquipos.CUATRO, "FIFA");
        ITorneo videojuegoBase2 = new TorneoVideojuegos("Torneo Videojuego DE", equiposTorneo, CantidadEquipos.CUATRO, "NBA 2K");
        ITorneo videojuegoBase3 = new TorneoVideojuegos("Torneo Videojuego LS", equiposTorneo, CantidadEquipos.CUATRO, "Rocket League");

        ITorneo torneoFisicoED = new EliminacionDirectaDecorator(fisicoBase1);
        ITorneo torneoFisicoDE = new DobleEliminacionDecorator(fisicoBase2);
        ITorneo torneoFisicoLS = new LigaSimple(fisicoBase3);

        ITorneo torneoVideojuegoED = new EliminacionDirectaDecorator(videojuegoBase1);
        ITorneo torneoVideojuegoDE = new DobleEliminacionDecorator(videojuegoBase2);
        ITorneo torneoVideojuegoLS = new LigaSimple(videojuegoBase3);

        depositoTorneos.addElemento(torneoFisicoED);
        depositoTorneos.addElemento(torneoFisicoDE);
        depositoTorneos.addElemento(torneoFisicoLS);
        depositoTorneos.addElemento(torneoVideojuegoED);
        depositoTorneos.addElemento(torneoVideojuegoDE);
        depositoTorneos.addElemento(torneoVideojuegoLS);
    }

    /**
     * retorna la lista de equipos creados
     * @return lista de equipos
     */
    public List<Equipos> getEquiposCreados() {
        return equiposCreados;
    }

    /**
     * retorna la lista de participantes creados
     * @return lista de participantes
     */
    public List<Participantes> getParticipantesCreados() {
        return participantesCreados;
    }

    /**
     * retorna el deposito de torneos creados
     * @return deposito de torneos
     */
    public Deposito<ITorneo> getDepositoTorneos() {
        return depositoTorneos;
    }
}
