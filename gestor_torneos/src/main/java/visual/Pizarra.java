package visual;

import javax.swing.*;
import java.awt.*;

public class Pizarra extends JPanel {
    private Image fondo;

    public Pizarra() {
        // Establecer un tamaño preferido para la pizarra
        setPreferredSize(new Dimension(1000, 1000));


        setLayout(null);


        //fotos
        ImageIcon fondoIcon = new ImageIcon(getClass().getResource("/Fondos/pizarra.png"));
        fondo = fondoIcon.getImage();



        //botones
        JButton botonCrearTorneo = new JButton("Crear Torneo");
        JButton botonModificarTorneo = new JButton("Actualizar Torneo");
        JButton botonEquipos = new JButton("Gestionar Equipos");
        JButton botonInformacion = new JButton("Ver Información");



        // Establecer posiciones y tamaños de los botones
        botonCrearTorneo.setBounds(80, 300, 150, 30);
        botonModificarTorneo.setBounds(80, 300+50, 150, 30);
        botonEquipos.setBounds((800 - 150) / 2, 700, 150, 30);
        botonInformacion.setBounds(800-80-150, 340, 150, 30);




        //añadir botones
        add(botonCrearTorneo);
        add(botonModificarTorneo);
        add(botonEquipos);
        add(botonInformacion);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar el fondo de la pizarra
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }


        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24)); // Establecer fuente y tamaño
        FontMetrics metrics = g.getFontMetrics();

        g.drawString("Gestor de Torneos", (getWidth() - metrics.stringWidth("Gestor de Torneos")) / 2, metrics.getHeight()); // Dibujar el texto en la posición deseada
    }
}