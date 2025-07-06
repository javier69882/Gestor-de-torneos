package Logico;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PosicionLigaTest {

    @Test
    public void testConstructor() {
        Equipos equipo = new Equipos("EquipoTablita");
        PosicionLiga pos = new PosicionLiga(equipo);

        assertEquals(equipo, pos.equipo);
        assertEquals(0, pos.puntos);
        assertEquals(0, pos.jugados);
        assertEquals(0, pos.golesFavor);
        assertEquals(0, pos.golesContra);
    }

    @Test
    public void testSetAndAddValues() {
        Equipos equipo = new Equipos("EquipoTablita2");
        PosicionLiga pos = new PosicionLiga(equipo);

        pos.puntos = 6;
        pos.jugados = 3;
        pos.golesFavor = 10;
        pos.golesContra = 4;

        assertEquals(6, pos.puntos);
        assertEquals(3, pos.jugados);
        assertEquals(10, pos.golesFavor);
        assertEquals(4, pos.golesContra);
    }
}
