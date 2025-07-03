package Logico;

import java.util.ArrayList;
import java.util.List;

public class EliminacionDirectaDecorator extends TorneoDecorator {

    public final List<List<Partido>> rondas;
    private int rondaActual;
    private Equipos campeon;

    public EliminacionDirectaDecorator(ITorneo torneo) {
        super(torneo);
        this.rondas = new ArrayList<>();
        this.rondaActual = 0;
        this.campeon = null;
        generarBracket();
    }

    private void generarBracket() {
        List<Equipos> equipos = new ArrayList<>(torneoDecorado.getEquipos());
        int n = equipos.size();
        List<Partido> primeraRonda = new ArrayList<>();
        for (int i = 0; i < n; i += 2) {
            primeraRonda.add(new Partido(equipos.get(i), equipos.get(i + 1)));
        }
        rondas.add(primeraRonda);
    }

    @Override
    public String getModalidad() {
        return "ELIMINACION_DIRECTA";
    }

    @Override
    public void jugarTorneo() {
        System.out.println("Jugando torneo en modalidad Eliminación Directa.");
    }


    @Override
    public List<Partido> getPartidos() {
        List<Partido> todos = new ArrayList<>();
        for (List<Partido> ronda : rondas) {
            todos.addAll(ronda);
        }
        return todos;
    }


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

    @Override
    public void registrarResultado(Partido partido, int puntajeA, int puntajeB) {
        if (partido.isJugado()) return;
        if (puntajeA == puntajeB) {
            throw new ValorNullException("No puede haber empate en eliminación directa. Reingrese los datos.");
        }
        partido.setPuntajeA(puntajeA);
        partido.setPuntajeB(puntajeB);
        partido.setJugado(true);

        // Si todos los partidos de la ronda actual han sido jugados, avanza ronda
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


    private void avanzarRonda() {
        List<Partido> rondaActualPartidos = rondas.get(rondaActual);
        List<Equipos> ganadores = new ArrayList<>();

        for (Partido p : rondaActualPartidos) {
            if (p.getPuntajeA() == p.getPuntajeB()) {
                throw new ValorNullException("No puede haber empate en eliminación directa. Define un desempate.");
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


    public Equipos getCampeon() {
        return campeon;
    }


    @Override
    public List<PosicionLiga> getTablaPosiciones() {
        return new ArrayList<>();
    }
}
