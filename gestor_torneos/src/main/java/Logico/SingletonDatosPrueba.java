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
        // Equipos para cada caso
        List<Equipos> equipos4 = crearEquipos("E4_", 4);
        List<Equipos> equipos8 = crearEquipos("E8_", 8);
        List<Equipos> equipos16 = crearEquipos("E16_", 16);

        // Añadir todos los equipos a equiposCreados/participantesCreados para acceso global
        equiposCreados.addAll(equipos4);
        equiposCreados.addAll(equipos8);
        equiposCreados.addAll(equipos16);

        // Torneos de 4 equipos
        ITorneo fisicoBase4 = new TorneoFisico("Fisico ED 4", equipos4, CantidadEquipos.CUATRO, "Futbol");
        ITorneo fisicoBase4_2 = new TorneoFisico("Fisico DE 4", equipos4, CantidadEquipos.CUATRO, "Basquet");
        ITorneo fisicoBase4_3 = new TorneoFisico("Fisico LS 4", equipos4, CantidadEquipos.CUATRO, "Voleibol");
        ITorneo videojuegoBase4 = new TorneoVideojuegos("Videojuego ED 4", equipos4, CantidadEquipos.CUATRO, "FIFA");
        ITorneo videojuegoBase4_2 = new TorneoVideojuegos("Videojuego DE 4", equipos4, CantidadEquipos.CUATRO, "NBA 2K");
        ITorneo videojuegoBase4_3 = new TorneoVideojuegos("Videojuego LS 4", equipos4, CantidadEquipos.CUATRO, "Rocket League");

        // Torneos de 8 equipos
        ITorneo fisicoBase8 = new TorneoFisico("Fisico ED 8", equipos8, CantidadEquipos.OCHO, "Futbol");
        ITorneo fisicoBase8_2 = new TorneoFisico("Fisico DE 8", equipos8, CantidadEquipos.OCHO, "Basquet");
        ITorneo fisicoBase8_3 = new TorneoFisico("Fisico LS 8", equipos8, CantidadEquipos.OCHO, "Voleibol");
        ITorneo videojuegoBase8 = new TorneoVideojuegos("Videojuego ED 8", equipos8, CantidadEquipos.OCHO, "FIFA");
        ITorneo videojuegoBase8_2 = new TorneoVideojuegos("Videojuego DE 8", equipos8, CantidadEquipos.OCHO, "NBA 2K");
        ITorneo videojuegoBase8_3 = new TorneoVideojuegos("Videojuego LS 8", equipos8, CantidadEquipos.OCHO, "Rocket League");

        //  Torneos de 16 equipos
        ITorneo fisicoBase16 = new TorneoFisico("Fisico ED 16", equipos16, CantidadEquipos.DIESEIS, "Futbol");
        ITorneo fisicoBase16_2 = new TorneoFisico("Fisico DE 16", equipos16, CantidadEquipos.DIESEIS, "Basquet");
        ITorneo fisicoBase16_3 = new TorneoFisico("Fisico LS 16", equipos16, CantidadEquipos.DIESEIS, "Voleibol");
        ITorneo videojuegoBase16 = new TorneoVideojuegos("Videojuego ED 16", equipos16, CantidadEquipos.DIESEIS, "FIFA");
        ITorneo videojuegoBase16_2 = new TorneoVideojuegos("Videojuego DE 16", equipos16, CantidadEquipos.DIESEIS, "NBA 2K");
        ITorneo videojuegoBase16_3 = new TorneoVideojuegos("Videojuego LS 16", equipos16, CantidadEquipos.DIESEIS, "Rocket League");

        // Decoradores: para cada base crea los 3 tipos de torneo
        agregarTorneosDecorados(fisicoBase4, fisicoBase4_2, fisicoBase4_3);
        agregarTorneosDecorados(videojuegoBase4, videojuegoBase4_2, videojuegoBase4_3);

        agregarTorneosDecorados(fisicoBase8, fisicoBase8_2, fisicoBase8_3);
        agregarTorneosDecorados(videojuegoBase8, videojuegoBase8_2, videojuegoBase8_3);

        agregarTorneosDecorados(fisicoBase16, fisicoBase16_2, fisicoBase16_3);
        agregarTorneosDecorados(videojuegoBase16, videojuegoBase16_2, videojuegoBase16_3);
    }

    // Crea equipos con participantes únicos para un torneo
    private List<Equipos> crearEquipos(String prefijo, int cantidad) {
        List<Equipos> equipos = new ArrayList<>();
        for (int i = 1; i <= cantidad; i++) {
            String nombreEquipo = prefijo + "Equipo" + i;
            Equipos equipo = new Equipos(nombreEquipo);
            equipos.add(equipo);
            // Participantes (puedes cambiar la cantidad si quieres)
            for (int j = 1; j <= 7; j++) {
                String nombre = "Jugador" + j;
                String apellido = nombreEquipo + j;
                String correo = nombre.toLowerCase() + j + "@" + nombreEquipo.toLowerCase() + ".com";
                Participantes p = new Participantes(apellido, nombre, correo, equipo);
                participantesCreados.add(p);
            }
        }
        return equipos;
    }

    // Agrega los tres tipos de torneos decorados por cada modalidad
    private void agregarTorneosDecorados(ITorneo baseED, ITorneo baseDE, ITorneo baseLS) {
        depositoTorneos.addElemento(new EliminacionDirectaDecorator(baseED));
        depositoTorneos.addElemento(new DobleEliminacionDecorator(baseDE));
        depositoTorneos.addElemento(new LigaSimple(baseLS));
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
