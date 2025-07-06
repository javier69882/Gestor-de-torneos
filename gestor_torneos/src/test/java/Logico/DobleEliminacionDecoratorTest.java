package Logico;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class DobleEliminacionDecoratorTest {

    @Test
    public void testDobleEliminacion4Equipos() {
        probarDobleEliminacionConEquipos(4);
    }

    @Test
    public void testDobleEliminacion8Equipos() {
        probarDobleEliminacionConEquipos(8);
    }

    @Test
    public void testDobleEliminacion16Equipos() {
        probarDobleEliminacionConEquipos(16);
    }


    private void probarDobleEliminacionConEquipos(int cantidadEquipos) {
        SingletonDatosPrueba singleton = SingletonDatosPrueba.getInstancia();
        ITorneo torneoDobleEliminacion = null;

        for (ITorneo torneo : singleton.getDepositoTorneos().getElementos()) {
            if ("DOBLE_ELIMINACION".equals(torneo.getModalidad()) &&
                    torneo.getEquipos().size() == cantidadEquipos) {
                torneoDobleEliminacion = torneo;
                break;
            }
        }

        assertNotNull(torneoDobleEliminacion, "No se encontró un torneo de doble eliminación con " + cantidadEquipos + " equipos");
        assertTrue(torneoDobleEliminacion instanceof DobleEliminacionDecorator);

        DobleEliminacionDecorator deco = (DobleEliminacionDecorator) torneoDobleEliminacion;

        int ronda = 1;
        while (deco.getCampeon() == null) {
            List<Partido> rondaActual = deco.getPartidosRondaActual();
            assertFalse(rondaActual.isEmpty(), "La ronda actual no debe estar vacía");

            for (Partido p : rondaActual) {
                deco.registrarResultado(p, 2 + ronda, 1);
            }
            ronda++;
        }

        // Verificar que se haya determinado un campeón
        assertNotNull(deco.getCampeon(), "Debe haber un campeón en doble eliminación");
        System.out.println("Campeón en doble eliminación (" + cantidadEquipos + " equipos): " + deco.getCampeon().getNombre());
    }
}
