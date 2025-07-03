package Logico;

import java.util.ArrayList;
import java.util.List;

public class DobleEliminacionDecorator extends TorneoDecorator {

    private final List<List<Partido>> rondasIda;
    private final List<List<Partido>> rondasVuelta;
    private int rondaActual;
    private Equipos campeon;

    public DobleEliminacionDecorator(ITorneo torneo) {
        super(torneo);
        this.rondasIda = new ArrayList<>();
        this.rondasVuelta = new ArrayList<>();
        this.rondaActual = 0;
        this.campeon = null;
        generarPrimerasRondas();
    }

    // Genera la primera ronda
    private void generarPrimerasRondas() {
        List<Equipos> equipos = new ArrayList<>(torneoDecorado.getEquipos());
        int n = equipos.size();
        // Se asume que n es 4, 8 o 16 (controlado fuera)
        List<Partido> rondaIda = new ArrayList<>();
        List<Partido> rondaVuelta = new ArrayList<>();
        for (int i = 0; i < n; i += 2) {
            rondaIda.add(new Partido(equipos.get(i), equipos.get(i + 1)));      // ida: A vs B
            rondaVuelta.add(new Partido(equipos.get(i + 1), equipos.get(i)));  // vuelta: B vs A
        }
        rondasIda.add(rondaIda);
        rondasVuelta.add(rondaVuelta);
    }

    @Override
    public String getModalidad() {
        return "DOBLE_ELIMINACION";
    }

    @Override
    public void jugarTorneo() {
        System.out.println("Jugando torneo en modalidad Eliminación Directa Ida y Vuelta.");
    }

    @Override
    public List<Partido> getPartidos() {
        List<Partido> todos = new ArrayList<>();
        for (int r = 0; r < rondasIda.size(); r++) {
            todos.addAll(rondasIda.get(r));
            todos.addAll(rondasVuelta.get(r));
        }
        return todos;
    }

    // Devuelve partidos ida y vuelta de la ronda actual
    public List<Partido> getPartidosRondaActual() {
        List<Partido> ronda = new ArrayList<>();
        if (rondaActual < rondasIda.size()) {
            ronda.addAll(rondasIda.get(rondaActual));
            ronda.addAll(rondasVuelta.get(rondaActual));
        }
        return ronda;
    }

    @Override
    public void registrarResultado(Partido partido, int puntajeA, int puntajeB) {
        if (partido.isJugado()) return;
        partido.setPuntajeA(puntajeA);
        partido.setPuntajeB(puntajeB);
        partido.setJugado(true);

        // Cuando TODOS los partidos ida y vuelta de la ronda actual estén jugados, avanza ronda
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

    // Devuelve el campeón
    public Equipos getCampeon() {
        return campeon;
    }

    // No aplica tabla de posiciones
    @Override
    public List<PosicionLiga> getTablaPosiciones() {
        return new ArrayList<>();
    }
}
