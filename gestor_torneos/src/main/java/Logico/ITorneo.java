package Logico;

import java.util.List;

public interface ITorneo {
    String getNombre();
    List<Equipos> getEquipos();
    CantidadEquipos getCantidadEquipos();
    String getTipoTorneo(); // "Físico" o "Videojuego"
    String getModalidad();  // Nombre modalidad decorada
    void jugarTorneo();     // comportamiento


    List<Partido> getPartidos();
    void registrarResultado(Partido partido, int puntajeA, int puntajeB);
    List<PosicionLiga> getTablaPosiciones();
}
