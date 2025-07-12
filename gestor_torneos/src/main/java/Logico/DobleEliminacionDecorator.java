package Logico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;


/**
 * agrega la modalidad de doble eliminacion a un torneo usando el patron decorator
 * @pattern decorator
 */
public class DobleEliminacionDecorator extends TorneoDecorator {

    /** listas de rondas ida y vuelta */
    public final List<List<Partido>> rondasIda;
    public final List<List<Partido>> rondasVuelta;
    private int rondaActual;
    private Equipos campeon;


    /**
     * crea el decorador de doble eliminacion para un torneo
     * @param torneo el torneo base que se va a decorar
     */
    public DobleEliminacionDecorator(ITorneo torneo) {
        super(torneo);
        this.rondasIda = new ArrayList<>();
        this.rondasVuelta = new ArrayList<>();
        this.rondaActual = 0;
        this.campeon = null;

        generarPrimerasRondas();
    }

    // genera la primera ronda ida y vuelta
    private void generarPrimerasRondas() {
        List<Equipos> equipos = new ArrayList<>(torneoDecorado.getEquipos());
        int n = equipos.size();
        List<Partido> rondaIda = new ArrayList<>();
        List<Partido> rondaVuelta = new ArrayList<>();
        for (int i = 0; i < n; i += 2) {
            rondaIda.add(new Partido(equipos.get(i), equipos.get(i + 1)));
            rondaVuelta.add(new Partido(equipos.get(i + 1), equipos.get(i)));
        }
        rondasIda.add(rondaIda);
        rondasVuelta.add(rondaVuelta);
    }

    /**
     * retorna el nombre de la modalidad
     * @return nombre de la modalidad
     */
    @Override
    public String getModalidad() {
        return "DOBLE_ELIMINACION";
    }

    /**
     * ejecuta la logica principal del torneo (solo muestra un mensaje aqui)
     */
    @Override
    public void jugarTorneo() {
        System.out.println("Jugando torneo en modalidad Eliminacion Directa Ida y Vuelta.");
    }

    /**
     * retorna la lista de todos los partidos ida y vuelta jugados en el torneo
     * @return lista de partidos
     */
    @Override
    public List<Partido> getPartidos() {
        List<Partido> todos = new ArrayList<>();
        for (int r = 0; r < rondasIda.size(); r++) {
            todos.addAll(rondasIda.get(r));
            todos.addAll(rondasVuelta.get(r));
        }
        return todos;
    }

    /**
     * retorna la lista de partidos de la ronda actual
     * @return lista de partidos de la ronda actual
     */
    public List<Partido> getPartidosRondaActual() {
        List<Partido> ronda = new ArrayList<>();
        if (rondaActual < rondasIda.size()) {
            ronda.addAll(rondasIda.get(rondaActual));
            ronda.addAll(rondasVuelta.get(rondaActual));
        }
        return ronda;
    }

    /**
     * registra el resultado de un partido y avanza de ronda si corresponde
     * @param partido partido a actualizar
     * @param puntajeA puntaje del equipo A
     * @param puntajeB puntaje del equipo B
     */
    @Override
    public void registrarResultado(Partido partido, int puntajeA, int puntajeB) {
        if (partido.isJugado()) return;
        partido.setPuntajeA(puntajeA);
        partido.setPuntajeB(puntajeB);
        partido.setJugado(true);

        boolean todosJugados = true;
        for (Partido p : rondasIda.get(rondaActual)) {
            if (!p.isJugado()) todosJugados = false;
        }
        for (Partido p : rondasVuelta.get(rondaActual)) {
            if (!p.isJugado()) todosJugados = false;
        }
        if (todosJugados) {
            avanzarRonda();
        }
    }

    // calcula clasificados y arma nueva ronda o define campeon
    private void avanzarRonda() {
        List<Equipos> clasificados = new ArrayList<>();
        List<Partido> rondaIda = rondasIda.get(rondaActual);
        List<Partido> rondaVuelta = rondasVuelta.get(rondaActual);

        for (int i = 0; i < rondaIda.size(); i++) {
            Partido ida = rondaIda.get(i);
            Partido vuelta = rondaVuelta.get(i);

            int globalA = ida.getPuntajeA() + vuelta.getPuntajeB();
            int globalB = ida.getPuntajeB() + vuelta.getPuntajeA();

            Equipos equipoA = ida.getEquipoA();
            Equipos equipoB = ida.getEquipoB();

            if (globalA > globalB) {
                clasificados.add(equipoA);
            } else if (globalB > globalA) {
                clasificados.add(equipoB);
            } else {
                clasificados.add(equipoA);
            }
        }

        if (clasificados.size() == 1) {
            campeon = clasificados.get(0);
        } else {
            List<Partido> nuevaIda = new ArrayList<>();
            List<Partido> nuevaVuelta = new ArrayList<>();
            for (int i = 0; i < clasificados.size(); i += 2) {
                nuevaIda.add(new Partido(clasificados.get(i), clasificados.get(i + 1)));
                nuevaVuelta.add(new Partido(clasificados.get(i + 1), clasificados.get(i)));
            }
            rondasIda.add(nuevaIda);
            rondasVuelta.add(nuevaVuelta);
            rondaActual++;
        }
    }

    /**
     * retorna el campeon del torneo si ya termino, si no retorna null
     * @return equipo campeon o null
     */
    public Equipos getCampeon() {
        return campeon;
    }

    /**
     * retorna la fecha de creacion del torneo
     * @return fecha de creacion
     */

    public LocalDateTime getFechaCreacion() {
        return torneoDecorado.getFechaCreacion();//
    }

    /**
     * no aplica tabla de posiciones en esta modalidad
     * @return lista vacia
     */
    @Override
    public List<PosicionLiga> getTablaPosiciones() {
        return new ArrayList<>();
    }
}
