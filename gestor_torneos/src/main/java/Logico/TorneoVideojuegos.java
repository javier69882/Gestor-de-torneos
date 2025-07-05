package Logico;

import java.util.List;

/**
 * representa un torneo de videojuegos, extiende torneo base
 */
public class TorneoVideojuegos extends TorneoBase {
    private String videojuego;

    /**
     * crea un torneo de videojuegos con nombre, equipos, cantidad de equipos y nombre del videojuego
     * @param nombre nombre del torneo
     * @param equipos lista de equipos
     * @param cantidadEquipos cantidad de equipos permitidos
     * @param videojuego nombre del videojuego
     */
    public TorneoVideojuegos(String nombre, List<Equipos> equipos, CantidadEquipos cantidadEquipos, String videojuego) {
        super(nombre, equipos, cantidadEquipos);
        this.videojuego = videojuego;
    }

    /**
     * retorna el nombre del videojuego
     * @return nombre del videojuego
     */
    public String getVideojuego() {
        return videojuego;
    }

    /**
     * cambia el nombre del videojuego
     * @param videojuego nuevo nombre del videojuego
     */
    public void setVideojuego(String videojuego) {
        this.videojuego = videojuego;
    }

    /**
     * retorna el tipo de torneo (videojuego)
     * @return tipo de torneo
     */
    @Override
    public String getTipoTorneo() {
        return "Videojuego";
    }
}
