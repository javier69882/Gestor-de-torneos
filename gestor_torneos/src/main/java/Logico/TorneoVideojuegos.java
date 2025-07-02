package Logico;

import java.util.List;

public class TorneoVideojuegos extends Torneo {
    private String videojuego;

    public TorneoVideojuegos(String nombre, List<Equipos> equipos, Modalidad modalidad, CantidadEquipos cantidadEquipos, String videojuego) {
        super(nombre, equipos, modalidad, cantidadEquipos);
        this.videojuego = videojuego;
    }

    public String getVideojuego() { return videojuego; }
    public void setVideojuego(String videojuego) { this.videojuego = videojuego; }
}