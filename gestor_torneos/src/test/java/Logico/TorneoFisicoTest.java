package Logico;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class TorneoFisicoTest {

    @Test
    public void testConstructorYGetters() {
        List<Equipos> equipos = Arrays.asList(
                new Equipos("EquipoF1"),
                new Equipos("EquipoF2")
        );
        TorneoFisico torneo = new TorneoFisico("Torneo Físico", equipos, CantidadEquipos.CUATRO, "Futbol");

        assertEquals("Torneo Físico", torneo.getNombre());
        assertEquals(equipos, torneo.getEquipos());
        assertEquals(CantidadEquipos.CUATRO, torneo.getCantidadEquipos());
        assertEquals("Futbol", torneo.getDeporte());
        assertEquals("Fisico", torneo.getTipoTorneo());
    }

    @Test
    public void testSetDeporte() {
        TorneoFisico torneo = new TorneoFisico("Torneo", new ArrayList<>(), CantidadEquipos.OCHO, "Voley");
        torneo.setDeporte("Basketball");
        assertEquals("Basketball", torneo.getDeporte());
    }
}
