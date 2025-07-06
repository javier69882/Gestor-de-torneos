package Logico;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParticipantesTest {

    @Test
    public void testConstructorValido() {
        Equipos equipo = new Equipos("EquipoTest");
        Participantes p = new Participantes("Apellido", "Nombre", "mail@dominio.com", equipo);
        assertEquals("Nombre", p.getNombre());
        assertEquals("Apellido", p.getApellidos());
        assertEquals("mail@dominio.com", p.getCorreo());
        assertEquals(equipo, p.getEquipo());
        assertTrue(equipo.getPaticipante().contains(p), "El participante debe estar en la lista del equipo");
    }

    @Test
    public void testConstructorEquipoNull() {
        assertThrows(ValorNullException.class, () ->
                new Participantes("Apellido", "Nombre", "mail@dominio.com", null)
        );
    }

    @Test
    public void testSetEquipo() {
        Equipos equipoA = new Equipos("A");
        Equipos equipoB = new Equipos("B");
        Participantes p = new Participantes("Apellido", "Nombre", "mail@dominio.com", equipoA);
        p.setEquipo(equipoB);
        assertEquals(equipoB, p.getEquipo());
        // (opcional) no se quita del equipo anterior automáticamente
        assertTrue(equipoA.getPaticipante().contains(p), "El participante sigue en equipoA");
    }
}
