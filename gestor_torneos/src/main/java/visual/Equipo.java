package visual;

import javax.swing.*;
import java.awt.*;

public class Equipo extends JPanel {
    private JButton botonCrearEquipo;
    private JButton botonBorrarEquipo;

    public Equipo() {
        setLayout(null);
        setBackground(new Color(255, 255, 200));

        botonCrearEquipo = new JButton("Crear Equipo");
        botonCrearEquipo.setFont(new Font("Arial", Font.BOLD, 22));
        botonCrearEquipo.setBounds(425, 300, 350, 70);
        add(botonCrearEquipo);

        botonBorrarEquipo = new JButton("Borrar Equipo");
        botonBorrarEquipo.setFont(new Font("Arial", Font.BOLD, 22));
        botonBorrarEquipo.setBounds(425, 400, 350, 70);
        add(botonBorrarEquipo);
    }

    public JButton getBotonCrearEquipo() { return botonCrearEquipo; }
    public JButton getBotonBorrarEquipo() { return botonBorrarEquipo; }
}
