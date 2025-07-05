package Logico;

import java.util.ArrayList;
import java.util.List;

/**
 * agrega la modalidad de eliminacion directa a un torneo usando el patron decorator
 * @pattern decorator
 */
public class EliminacionDirectaDecorator extends TorneoDecorator {

    /** lista de rondas del torneo */
    public final List<List<Partido>> rondas;
    private int rondaActual;
    private Equipos campeon;

    /**
     * crea el decorador de eliminacion directa para un torneo
     * @param torneo el torneo base a decorar
     */
    public EliminacionDirectaDecorator(ITorneo torneo) {
        super(torneo);
        this.rondas = new ArrayList<>();
        this.rondaActual = 0;
        this.campeon = null;
        generarBracket();
    }

    // genera la primera ronda de partidos
    private void generarBracket() {
        List<Equipos> equipos = new ArrayList<>(torneoDecorado.getEquipos());
        int n = equipos.size();
        List<Partido> primeraRonda = new ArrayList<>();
        for (int i = 0; i < n; i += 2) {
            primeraRonda.add(new Partido(equipos.get(i), equipos.get(i + 1)));
        }
        rondas.add(primeraRonda);
    }

    /**
     * retorna el nombre de la modalidad
     * @return nombre de la modalidad
     */
    @Override
    public String getModalidad() {
        return "ELIMINACION_DIRECTA";
    }

    /**
     * ejecuta la logica del torneo
     */
    @Override
    public void jugarTorneo() {
        System.out.println("Jugando torneo en modalidad Eliminacion Directa.");
    }

    /**
     * retorna la lista de todos los partidos jugados en el torneo
     * @return lista de partidos
     */
    @Override
    public List<Partido> getPartidos() {
        List<Partido> todos = new ArrayList<>();
        for (List<Partido> ronda : rondas) {
            todos.addAll(ronda);
        }
        return todos;
    }

    /**
     * retorna la lista de partidos pendientes en la ronda actual
     * @return lista de partidos pendientes
     */
    public List<Partido> getPartidosRondaActual() {
        if (rondaActual < rondas.size()) {
            List<Partido> ronda = rondas.get(rondaActual);
            List<Partido> pendientes = new ArrayList<>();
            for (Partido p : ronda) {
                if (!p.isJugado()) pendientes.add(p);
            }
            return pendientes;
        }
        return new ArrayList<>();
    }

    /**
     * registra el resultado de un partido y avanza ronda si corresponde
     * @param partido partido a actualizar
     * @param puntajeA puntaje del equipo A
     * @param puntajeB puntaje del equipo B
     * @throws ValorNullException si hay empate
     */
    @Override
    public void registrarResultado(Partido partido, int puntajeA, int puntajeB) {
        if (partido.isJugado()) return;
        if (puntajeA == puntajeB) {
            throw new ValorNullException("No se puede haber empate en eliminacion directa. Reingrese los datos.");
        }
        partido.setPuntajeA(puntajeA);
        partido.setPuntajeB(puntajeB);
        partido.setJugado(true);

        boolean todosJugados = true;
        for (Partido p : rondas.get(rondaActual)) {
            if (!p.isJugado()) {
                todosJugados = false;
                break;
            }
        }
        if (todosJugados) {
            avanzarRonda();
        }
    }

    // calcula ganadores y arma la nueva ronda, o define campeon
    private void avanzarRonda() {
        List<Partido> rondaActualPartidos = rondas.get(rondaActual);
        List<Equipos> ganadores = new ArrayList<>();

        for (Partido p : rondaActualPartidos) {
            if (p.getPuntajeA() == p.getPuntajeB()) {
                throw new ValorNullException("No puede haber empate en eliminacion directa. Define un desempate.");
            }
            ganadores.add(p.getPuntajeA() > p.getPuntajeB() ? p.getEquipoA() : p.getEquipoB());
        }

        if (ganadores.size() == 1) {
            campeon = ganadores.get(0);
        } else {
            List<Partido> nuevaRonda = new ArrayList<>();
            for (int i = 0; i < ganadores.size(); i += 2) {
                nuevaRonda.add(new Partido(ganadores.get(i), ganadores.get(i + 1)));
            }
            rondas.add(nuevaRonda);
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
     * no aplica tabla de posiciones en esta modalidad
     * @return lista vacia
     */
    @Override
    public List<PosicionLiga> getTablaPosiciones() {
        return new ArrayList<>();
    }
}
