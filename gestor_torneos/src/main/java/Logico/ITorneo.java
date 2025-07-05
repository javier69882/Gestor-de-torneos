package Logico;

import java.util.List;

/**
 * interfaz base para definir torneos con distintos comportamientos y modalidades
 * permite usar el patron decorator para agregar modalidades a un torneo
 * @pattern decorator
 */
public interface ITorneo {
    /**
     * retorna el nombre del torneo
     * @return nombre del torneo
     */
    String getNombre();

    /**
     * retorna la lista de equipos del torneo
     * @return lista de equipos
     */
    List<Equipos> getEquipos();

    /**
     * retorna la cantidad de equipos permitidos
     * @return cantidad de equipos
     */
    CantidadEquipos getCantidadEquipos();

    /**
     * retorna el tipo de torneo (fisico o videojuego)
     * @return tipo de torneo
     */
    String getTipoTorneo();

    /**
     * retorna la modalidad del torneo
     * @return modalidad del torneo
     */
    String getModalidad();

    /**
     * ejecuta el torneo
     */
    void jugarTorneo();

    /**
     * retorna la lista de partidos del torneo
     * @return lista de partidos
     */
    List<Partido> getPartidos();

    /**
     * registra el resultado de un partido
     * @param partido partido a actualizar
     * @param puntajeA puntaje del equipo A
     * @param puntajeB puntaje del equipo B
     */
    void registrarResultado(Partido partido, int puntajeA, int puntajeB);

    /**
     * retorna la tabla de posiciones si aplica
     * @return lista de posiciones de liga
     */
    List<PosicionLiga> getTablaPosiciones();
}
