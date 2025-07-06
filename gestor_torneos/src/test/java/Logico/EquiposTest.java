package Logico;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class EquiposTest {

    @Test
    public void testConstructorConNombreValido() {
        Equipos equipo = new Equipos("EquipoPrueba");
        assertEquals("EquipoPrueba", equipo.getNombre());
        assertNotNull(equipo.getPaticipante());
        assertEquals(0, equipo.obtenerCantidadparticipantes());
    }

    @Test
    public void testConstructorConNombreInvalido() {
        assertThrows(ValorNullException.class, () -> new Equipos(null));
        assertThrows(ValorNullException.class, () -> new Equipos(""));
        assertThrows(ValorNullException.class, () -> new Equipos("   "));
    }

    @Test
    public void testAgregarParticipanteValido() {
        Equipos equipo = new Equipos("EquipoUno");
        // Solo usar el constructor de Participantes, que ya agrega al equipo automáticamente
        Participantes participante = new Participantes("Apellido", "Nombre", "correo@mail.com", equipo);
        assertEquals(1, equipo.obtenerCantidadparticipantes());
        assertTrue(equipo.getPaticipante().contains(participante));
    }

    @Test
    public void testAgregarParticipanteNull() {
        Equipos equipo = new Equipos("EquipoDos");
        assertThrows(ValorNullException.class, () -> equipo.agregarParticipante(null));
    }

    @Test
    public void testSetNombreValido() {
        Equipos equipo = new Equipos("ViejoNombre");
        equipo.setNombre("NuevoNombre");
        assertEquals("NuevoNombre", equipo.getNombre());
    }

    @Test
    public void testSetNombreInvalido() {
        Equipos equipo = new Equipos("NombreOk");
        assertThrows(ValorNullException.class, () -> equipo.setNombre(null));
        assertThrows(ValorNullException.class, () -> equipo.setNombre(""));
        assertThrows(ValorNullException.class, () -> equipo.setNombre("   "));
    }

    @Test
    public void testSetParticipantesValido() {
        Equipos equipo = new Equipos("EquipoListas");
        List<Participantes> lista = new ArrayList<>();
        lista.add(new Participantes("Apellido1", "Nombre1", "correo1@mail.com", equipo));
        equipo.setParticipantes(lista);
        assertEquals(1, equipo.obtenerCantidadparticipantes());
    }

    @Test
    public void testSetParticipantesNull() {
        Equipos equipo = new Equipos("EquipoError");
        assertThrows(ValorNullException.class, () -> equipo.setParticipantes(null));
    }

    @Test
    public void testObtenerCantidadparticipantes() {
        Equipos equipo = new Equipos("EquipoVacio");
        assertEquals(0, equipo.obtenerCantidadparticipantes());

        // El participante se agrega automáticamente en el constructor
        new Participantes("Apellido", "Nombre", "mail@mail.com", equipo);
        assertEquals(1, equipo.obtenerCantidadparticipantes());
    }
}
