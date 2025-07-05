package Logico;

import java.util.ArrayList;
import java.util.List;

/**
 * agrega la modalidad de liga simple a un torneo usando el patron decorator
 * @pattern decorator
 */
public class LigaSimple extends TorneoDecorator {
    private List<Partido> partidos;
    private List<PosicionLiga> tabla;

    /**
     * crea el decorador de liga simple para un torneo
     * @param torneo el torneo base a decorar
     */
    public LigaSimple(ITorneo torneo) {
        super(torneo);
        generarPartidos();
        inicializarTabla();
    }

    // genera todos los partidos entre equipos (ida)
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

    // inicializa la tabla de posiciones
    private void inicializarTabla() {
        tabla = new ArrayList<>();
        for (Equipos eq : torneoDecorado.getEquipos()) {
            tabla.add(new PosicionLiga(eq));
        }
    }

    /**
     * retorna la lista de partidos de la liga
     * @return lista de partidos
     */
    @Override
    public List<Partido> getPartidos() { return partidos; }

    /**
     * registra el resultado de un partido y actualiza la tabla de posiciones
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
        actualizarTabla(partido);
    }

    // actualiza la tabla de posiciones con los resultados del partido
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

    // busca la posicion de un equipo en la tabla
    private PosicionLiga buscarPosicion(Equipos equipo) {
        for (PosicionLiga pos : tabla)
            if (pos.equipo.equals(equipo)) return pos;
        return null;
    }

    /**
     * retorna la tabla de posiciones ordenada por puntos
     * @return tabla de posiciones
     */
    @Override
    public List<PosicionLiga> getTablaPosiciones() {
        tabla.sort((a, b) -> Integer.compare(b.puntos, a.puntos));
        return tabla;
    }

    /**
     * retorna el nombre de la modalidad
     * @return nombre de la modalidad
     */
    public String getModalidad() {
        return "LIGA_SIMPLE";
    }

    /**
     * metodo de jugarTorneo no implementado en esta clase
     */
    @Override
    public void jugarTorneo() {

    }
}
