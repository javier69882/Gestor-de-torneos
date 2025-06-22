package visual;

import javax.swing.*;
import java.awt.*;

public class TabletUsuario extends JPanel {
    private JButton botonVerEquipos;
    private JButton botonVerTorneos;

    public TabletUsuario() {
        setBackground(new Color(180, 240, 200));
        setLayout(null);

        JLabel label = new JLabel("Vista de Usuario");
        label.setFont(new Font("Arial", Font.BOLD, 28));
        label.setForeground(Color.BLACK);
        label.setBounds(350, 60, 400, 50);
        add(label);

        botonVerEquipos = new JButton("Ver Equipos");
        botonVerEquipos.setBounds(350, 180, 200, 40);
        add(botonVerEquipos);

        botonVerTorneos = new JButton("Ver Torneos");
        botonVerTorneos.setBounds(350, 240, 200, 40);
        add(botonVerTorneos);
    }

    public JButton getBotonVerEquipos() {
        return botonVerEquipos;
    }
    public JButton getBotonVerTorneos() {
        return botonVerTorneos;
    }
}
