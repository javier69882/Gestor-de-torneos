package visual;

import Logico.*;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        inicializarDatosPrueba();

        JFrame frame = new JFrame("GESTOR DE TORNEOS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1000);

        PanelPrincipal panelPrincipal = new PanelPrincipal();
        frame.add(panelPrincipal);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void inicializarDatosPrueba() {
        String[] nombresEquipos = {"Águilas", "Lobos", "Tiburones", "Pumas"};
        for (String nombreEquipo : nombresEquipos) {
            Equipos equipo = new Equipos(nombreEquipo);
            Equipo.getEquiposCreados().add(equipo);
            for (int i = 1; i <= 7; i++) {
                String nombre = "Jugador" + i;
                String apellido = nombreEquipo + i;
                String correo = nombre.toLowerCase() + i + "@" + nombreEquipo.toLowerCase() + ".com";
                Participantes p = new Participantes(apellido, nombre, correo, equipo);
                Equipo.getParticipantesCreados().add(p);
            }
        }
    }
}