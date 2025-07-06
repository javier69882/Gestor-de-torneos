package Logico;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PartidoTest {

    @Test
    public void testConstructorYGetters() {
        Equipos equipoA = new Equipos("A");
        Equipos equipoB = new Equipos("B");
        Partido partido = new Partido(equipoA, equipoB);

        assertEquals(equipoA, partido.getEquipoA());
        assertEquals(equipoB, partido.getEquipoB());
        assertNull(partido.getPuntajeA());
        assertNull(partido.getPuntajeB());
        assertFalse(partido.isJugado());
        assertNull(partido.getFechaHoraJugado());
    }

    @Test
    public void testSetPuntajesYGetMarcadorSeguro() {
        Equipos equipoA = new Equipos("A");
        Equipos equipoB = new Equipos("B");
        Partido partido = new Partido(equipoA, equipoB);

        partido.setPuntajeA(3);
        partido.setPuntajeB(2);

        assertEquals(3, partido.getPuntajeA());
        assertEquals(2, partido.getPuntajeB());
        assertEquals("3 - 2", partido.getMarcadorSeguro());
    }

    @Test
    public void testGetMarcadorSeguroSinPuntajes() {
        Equipos equipoA = new Equipos("A");
        Equipos equipoB = new Equipos("B");
        Partido partido = new Partido(equipoA, equipoB);
        assertEquals("-", partido.getMarcadorSeguro());
    }

    @Test
    public void testSetJugadoYFecha() throws InterruptedException {
        Equipos equipoA = new Equipos("A");
        Equipos equipoB = new Equipos("B");
        Partido partido = new Partido(equipoA, equipoB);

        assertFalse(partido.isJugado());
        assertNull(partido.getFechaHoraJugado());

        partido.setJugado(true);
        assertTrue(partido.isJugado());
        assertNotNull(partido.getFechaHoraJugado());

        // Llamar otra vez con true no debe cambiar la fecha
        var fechaAntes = partido.getFechaHoraJugado();
        Thread.sleep(10);
        partido.setJugado(true);
        assertEquals(fechaAntes, partido.getFechaHoraJugado());

        // Volver a false no debe borrar la fecha
        partido.setJugado(false);
        assertFalse(partido.isJugado());
        assertEquals(fechaAntes, partido.getFechaHoraJugado());
    }

    @Test
    public void testNombreEquipoSeguroConNull() {
        Partido partido = new Partido(null, null);
        assertEquals("Pendiente", partido.getNombreEquipoASeguro());
        assertEquals("Pendiente", partido.getNombreEquipoBSeguro());
    }

    @Test
    public void testToStringFormatos() {
        Equipos equipoA = new Equipos("Alpha");
        Equipos equipoB = new Equipos("Beta");
        Partido partido = new Partido(equipoA, equipoB);

        String s1 = partido.toString();
        assertTrue(s1.contains("Alpha"));
        assertTrue(s1.contains("Beta"));

        partido.setPuntajeA(1);
        partido.setPuntajeB(2);
        partido.setJugado(true);
        String s2 = partido.toString();
        assertTrue(s2.contains("1 - 2"));
        assertTrue(s2.contains("Alpha"));
        assertTrue(s2.contains("Beta"));
        assertTrue(s2.contains("["));
    }
}
