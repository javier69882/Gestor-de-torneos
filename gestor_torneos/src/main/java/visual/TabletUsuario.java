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

        // Listener para abrir nuevo JFrame al presionar "Ver Torneos"
        botonVerTorneos.addActionListener(e -> abrirVentanaVerTorneos());
    }

    private void abrirVentanaVerTorneos() {
        JFrame ventana = new JFrame("Lista de Torneos");
        ventana.setSize(500, 400);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(220, 240, 250));

        JLabel etiqueta = new JLabel("Aquí puedes ver los torneos disponibles");
        etiqueta.setFont(new Font("Arial", Font.BOLD, 16));
        etiqueta.setBounds(80, 30, 400, 30);
        panel.add(etiqueta);

        ventana.add(panel);
        ventana.setVisible(true);
    }

    public JButton getBotonVerEquipos() {
        return botonVerEquipos;
    }

    public JButton getBotonVerTorneos() {
        return botonVerTorneos;
    }
}
