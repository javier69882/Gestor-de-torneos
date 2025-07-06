package Logico;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class LigaSimpleTest {

    @Test
    public void testLigaSimple4Equipos() {
        probarLigaSimpleConEquipos(4);
    }

    @Test
    public void testLigaSimple8Equipos() {
        probarLigaSimpleConEquipos(8);
    }

    @Test
    public void testLigaSimple16Equipos() {
        probarLigaSimpleConEquipos(16);
    }

    @Test
    private void probarLigaSimpleConEquipos(int cantidadEquipos) {
        SingletonDatosPrueba singleton = SingletonDatosPrueba.getInstancia();
        ITorneo torneoLS = null;

        for (ITorneo torneo : singleton.getDepositoTorneos().getElementos()) {
            if ("LIGA_SIMPLE".equals(torneo.getModalidad()) &&
                    torneo.getEquipos().size() == cantidadEquipos) {
                torneoLS = torneo;
                break;
            }
        }

        assertNotNull(torneoLS, "No se encontró un torneo de liga simple con " + cantidadEquipos + " equipos");
        assertTrue(torneoLS instanceof LigaSimple);

        LigaSimple liga = (LigaSimple) torneoLS;

        // Jugar todos los partidos con resultados distintos
        int marcadorA = 2;
        int marcadorB = 1;

        for (Partido partido : liga.getPartidos()) {
            // Alterna ganadores para darle variedad a la tabla
            if ((marcadorA + marcadorB) % 2 == 0) {
                liga.registrarResultado(partido, marcadorA, marcadorB);
            } else {
                liga.registrarResultado(partido, marcadorB, marcadorA);
            }
            marcadorA++;
            marcadorB++;
        }

        // La tabla debe estar ordenada por puntos y tener el mismo número de equipos
        List<PosicionLiga> tabla = liga.getTablaPosiciones();
        assertNotNull(tabla, "La tabla de posiciones no debe ser null");
        assertEquals(cantidadEquipos, tabla.size(), "La tabla debe tener " + cantidadEquipos + " posiciones");

        // Deben estar ordenados por puntos descendente
        int puntosPrevio = Integer.MAX_VALUE;
        for (PosicionLiga pos : tabla) {
            assertTrue(pos.puntos <= puntosPrevio, "La tabla debe estar ordenada por puntos descendente");
            puntosPrevio = pos.puntos;
        }

        // Los partidos deben estar marcados como jugados
        for (Partido p : liga.getPartidos()) {
            assertTrue(p.isJugado(), "Todos los partidos deben estar marcados como jugados");
        }

        System.out.println("Liga simple (" + cantidadEquipos + " equipos): Primer lugar = " + tabla.get(0).equipo.getNombre() + " (" + tabla.get(0).puntos + " puntos)");
    }
}
