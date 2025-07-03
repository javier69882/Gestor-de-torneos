package Logico;

import java.util.List;

public class TorneoVideojuegos extends TorneoBase {
    private String videojuego;

    public TorneoVideojuegos(String nombre, List<Equipos> equipos, CantidadEquipos cantidadEquipos, String videojuego) {
        super(nombre, equipos, cantidadEquipos);
        this.videojuego = videojuego;
    }

    public String getVideojuego() {
        return videojuego;
    }
    public void setVideojuego(String videojuego) {
        this.videojuego = videojuego;
    }

    @Override
    public String getTipoTorneo() {
        return "Videojuego";
    }
}
