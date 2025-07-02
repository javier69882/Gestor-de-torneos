package visual;

import javax.swing.*;
import java.awt.*;

public class PizarraAdmin extends JPanel {
    private Image fondo;
    private JButton botonCrearTorneo;
    private JButton botonAccederTorneo;
    private JButton botonEquipos;
    private JButton botonInformacion;

    public PizarraAdmin() {
        setPreferredSize(new Dimension(1000, 1000));
        setLayout(null);

        ImageIcon fondoIcon = new ImageIcon(getClass().getResource("/Fondos/pizarra.png"));
        fondo = fondoIcon.getImage();

        botonCrearTorneo = new JButton("Crear Torneo");
        botonAccederTorneo = new JButton("Acceder Torneo");
        botonEquipos = new JButton("Gestionar Equipos");
        botonInformacion = new JButton("Ver Información");

        botonCrearTorneo.setBounds(80, 300, 150, 30);
        botonAccederTorneo.setBounds(80, 350, 150, 30);
        botonEquipos.setBounds((800 - 150) / 2, 700, 150, 30);
        botonInformacion.setBounds(570, 340, 150, 30);

        add(botonCrearTorneo);
        add(botonAccederTorneo);
        add(botonEquipos);
        add(botonInformacion);

        // Listener para abrir un nuevo JFrame al crear torneo
        botonCrearTorneo.addActionListener(e -> abrirVentanaCrearTorneo());

        // Listener para abrir un nuevo JFrame al acceder torneo
        botonAccederTorneo.addActionListener(e -> abrirVentanaAccederTorneo());
    }

    public JButton getBotonEquipos() {
        return botonEquipos;
    }

    private void abrirVentanaCrearTorneo() {
        JFrame ventana = new JFrame("Crear Nuevo Torneo");
        ventana.setSize(500, 400);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(220, 220, 250));

        JLabel etiqueta = new JLabel("Aquí puedes crear un torneo nuevo");
        etiqueta.setFont(new Font("Arial", Font.BOLD, 16));
        etiqueta.setBounds(100, 30, 300, 30);
        panel.add(etiqueta);

        ventana.add(panel);
        ventana.setVisible(true);
    }

    private void abrirVentanaAccederTorneo() {
        JFrame ventana = new JFrame("Acceder a Torneo");
        ventana.setSize(500, 400);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(230, 255, 230));

        JLabel etiqueta = new JLabel("Aquí puedes acceder a un torneo existente");
        etiqueta.setFont(new Font("Arial", Font.BOLD, 16));
        etiqueta.setBounds(70, 30, 400, 30);
        panel.add(etiqueta);

        ventana.add(panel);
        ventana.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        FontMetrics metrics = g.getFontMetrics();
        g.drawString("Gestor de Torneos", (getWidth() - metrics.stringWidth("Gestor de Torneos")) / 2, metrics.getHeight());
    }
}
