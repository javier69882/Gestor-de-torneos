package Logico;

public class TorneoVideojuegos extends Torneo{
    private String videojuego;

    public TorneoVideojuegos(String nombre, String videojuego) {
        super(nombre, null); // Inicializa la lista de equipos como null
        this.videojuego = videojuego;
    }

    public String getVideojuego() {
        return videojuego;
    }

    public void setVideojuego(String videojuego) {
        this.videojuego = videojuego;
    }
}
