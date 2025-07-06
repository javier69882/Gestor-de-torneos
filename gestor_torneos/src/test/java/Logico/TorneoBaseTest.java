package Logico;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class TorneoBaseTest {

    @Test
    public void testConstructorYGetters() {
        List<Equipos> equipos = Arrays.asList(
                new Equipos("Equipo1"),
                new Equipos("Equipo2")
        );
        TorneoBase torneo = new TorneoBase("MiTorneo", equipos, CantidadEquipos.CUATRO);

        assertEquals("MiTorneo", torneo.getNombre());
        assertEquals(equipos, torneo.getEquipos());
        assertEquals(CantidadEquipos.CUATRO, torneo.getCantidadEquipos());
        assertEquals("Base", torneo.getTipoTorneo());
        assertEquals("Sin modalidad", torneo.getModalidad());
    }

    @Test
    public void testJugarTorneoSinError() {
        TorneoBase torneo = new TorneoBase("TorneoX", new ArrayList<>(), CantidadEquipos.OCHO);
        assertDoesNotThrow(() -> torneo.jugarTorneo());
    }

    @Test
    public void testGetPartidosDevuelveListaVacia() {
        TorneoBase torneo = new TorneoBase("TorneoSinPartidos", new ArrayList<>(), CantidadEquipos.CUATRO);
        List<Partido> partidos = torneo.getPartidos();
        assertNotNull(partidos);
        assertTrue(partidos.isEmpty());
    }

    @Test
    public void testRegistrarResultadoNoLanzaError() {
        TorneoBase torneo = new TorneoBase("Torneo", new ArrayList<>(), CantidadEquipos.CUATRO);
        Partido partido = new Partido(new Equipos("A"), new Equipos("B"));
        assertDoesNotThrow(() -> torneo.registrarResultado(partido, 1, 1));
    }

    @Test
    public void testGetTablaPosicionesDevuelveListaVacia() {
        TorneoBase torneo = new TorneoBase("TorneoTablas", new ArrayList<>(), CantidadEquipos.CUATRO);
        List<PosicionLiga> tabla = torneo.getTablaPosiciones();
        assertNotNull(tabla);
        assertTrue(tabla.isEmpty());
    }
}
