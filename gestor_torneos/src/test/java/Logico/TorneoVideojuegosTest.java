package Logico;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class TorneoVideojuegosTest {

    @Test
    public void testConstructorYGetters() {
        List<Equipos> equipos = Arrays.asList(
                new Equipos("Team1"),
                new Equipos("Team2")
        );
        TorneoVideojuegos torneo = new TorneoVideojuegos("Torneo Gamers", equipos, CantidadEquipos.OCHO, "FIFA");

        assertEquals("Torneo Gamers", torneo.getNombre());
        assertEquals(equipos, torneo.getEquipos());
        assertEquals(CantidadEquipos.OCHO, torneo.getCantidadEquipos());
        assertEquals("FIFA", torneo.getVideojuego());
        assertEquals("Videojuego", torneo.getTipoTorneo());
    }

    @Test
    public void testSetVideojuego() {
        TorneoVideojuegos torneo = new TorneoVideojuegos("CopaDigital", new ArrayList<>(), CantidadEquipos.DIESEIS, "Rocket League");
        torneo.setVideojuego("NBA 2K");
        assertEquals("NBA 2K", torneo.getVideojuego());
    }
}
