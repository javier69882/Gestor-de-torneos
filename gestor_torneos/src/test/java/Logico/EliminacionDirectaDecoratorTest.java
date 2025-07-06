package Logico;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class EliminacionDirectaDecoratorTest {

    @Test
    public void testEliminacionDirecta4Equipos() {
        probarEliminacionDirectaConEquipos(4);
    }

    @Test
    public void testEliminacionDirecta8Equipos() {
        probarEliminacionDirectaConEquipos(8);
    }

    @Test
    public void testEliminacionDirecta16Equipos() {
        probarEliminacionDirectaConEquipos(16);
    }

    @Test
    private void probarEliminacionDirectaConEquipos(int cantidadEquipos) {
        SingletonDatosPrueba singleton = SingletonDatosPrueba.getInstancia();
        ITorneo torneoED = null;

        for (ITorneo torneo : singleton.getDepositoTorneos().getElementos()) {
            if ("ELIMINACION_DIRECTA".equals(torneo.getModalidad()) &&
                    torneo.getEquipos().size() == cantidadEquipos) {
                torneoED = torneo;
                break;
            }
        }

        assertNotNull(torneoED, "No se encontró un torneo de eliminación directa con " + cantidadEquipos + " equipos");
        assertTrue(torneoED instanceof EliminacionDirectaDecorator);

        EliminacionDirectaDecorator deco = (EliminacionDirectaDecorator) torneoED;

        int ronda = 1;
        while (deco.getCampeon() == null) {
            List<Partido> rondaActual = deco.getPartidosRondaActual();
            assertFalse(rondaActual.isEmpty(), "La ronda actual no debe estar vacía");

            for (Partido p : rondaActual) {
                // Siempre hay ganador
                deco.registrarResultado(p, 1 + ronda, 0);
            }
            ronda++;
        }

        // Al final debe haber campeón
        assertNotNull(deco.getCampeon(), "Debe haber un campeón en eliminación directa");
        System.out.println("Campeón en eliminación directa (" + cantidadEquipos + " equipos): " + deco.getCampeon().getNombre());
    }
}
