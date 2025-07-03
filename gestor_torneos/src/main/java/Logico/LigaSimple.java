package Logico;

import java.util.ArrayList;
import java.util.List;

public class LigaSimple extends TorneoDecorator {
    private List<Partido> partidos;
    private List<PosicionLiga> tabla;

    public LigaSimple(ITorneo torneo) {
        super(torneo);
        generarPartidos();
        inicializarTabla();
    }

    private void generarPartidos() {
        List<Equipos> equipos = torneoDecorado.getEquipos();
        partidos = new ArrayList<>();
        int n = equipos.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                partidos.add(new Partido(equipos.get(i), equipos.get(j)));
            }
        }
    }

    private void inicializarTabla() {
        tabla = new ArrayList<>();
        for (Equipos eq : torneoDecorado.getEquipos()) {
            tabla.add(new PosicionLiga(eq));
        }
    }

    @Override
    public List<Partido> getPartidos() { return partidos; }

    @Override
    public void registrarResultado(Partido partido, int puntajeA, int puntajeB) {
        if (partido.isJugado()) return;
        partido.setPuntajeA(puntajeA);
        partido.setPuntajeB(puntajeB);
        partido.setJugado(true);
        actualizarTabla(partido);
    }

    private void actualizarTabla(Partido partido) {
        PosicionLiga posA = buscarPosicion(partido.getEquipoA());
        PosicionLiga posB = buscarPosicion(partido.getEquipoB());

        posA.jugados++;
        posB.jugados++;
        posA.golesFavor += partido.getPuntajeA();
        posA.golesContra += partido.getPuntajeB();
        posB.golesFavor += partido.getPuntajeB();
        posB.golesContra += partido.getPuntajeA();

        if (partido.getPuntajeA() > partido.getPuntajeB()) {
            posA.puntos += 3;
        } else if (partido.getPuntajeA() < partido.getPuntajeB()) {
            posB.puntos += 3;
        } else {
            posA.puntos++;
            posB.puntos++;
        }
    }

    private PosicionLiga buscarPosicion(Equipos equipo) {
        for (PosicionLiga pos : tabla)
            if (pos.equipo.equals(equipo)) return pos;
        return null;
    }

    @Override
    public List<PosicionLiga> getTablaPosiciones() {
        tabla.sort((a, b) -> Integer.compare(b.puntos, a.puntos)); // Descendente por puntos
        return tabla;
    }


    public String getModalidad() {
        return "LIGA_SIMPLE"; //
    }
    @Override
    public void jugarTorneo() {

    }

}
