package visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Equipo extends JPanel {
    private JButton botonCrearEquipo;
    private JButton botonBorrarEquipo;
    private JButton botonCrearParticipante;

    public Equipo() {
        setLayout(null);
        setBackground(new Color(255, 255, 200));

        // Botón: Crear Equipo
        botonCrearEquipo = new JButton("Crear Equipo");
        botonCrearEquipo.setFont(new Font("Arial", Font.BOLD, 22));
        botonCrearEquipo.setBounds(425, 300, 350, 70);
        add(botonCrearEquipo);
        botonCrearEquipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentana("Crear Equipo", "Formulario para crear un equipo");
            }
        });

        // Botón: Borrar Equipo
        botonBorrarEquipo = new JButton("Borrar Equipo");
        botonBorrarEquipo.setFont(new Font("Arial", Font.BOLD, 22));
        botonBorrarEquipo.setBounds(425, 400, 350, 70);
        add(botonBorrarEquipo);
        botonBorrarEquipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentana("Borrar Equipo", "Formulario para borrar un equipo");
            }
        });

        // Botón: Crear Participante
        botonCrearParticipante = new JButton("Crear Participante");
        botonCrearParticipante.setFont(new Font("Arial", Font.BOLD, 22));
        botonCrearParticipante.setBounds(425, 500, 350, 70);
        add(botonCrearParticipante);
        botonCrearParticipante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentana("Crear Participante", "Formulario para crear un participante");
            }
        });
    }


    private void abrirVentana(String titulo, String mensaje) {
        JFrame ventana = new JFrame(titulo);
        ventana.setSize(400, 300);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel(mensaje);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        ventana.add(label);

        ventana.setVisible(true);
    }

    public JButton getBotonCrearEquipo() { return botonCrearEquipo; }
    public JButton getBotonBorrarEquipo() { return botonBorrarEquipo; }
    public JButton getBotonCrearParticipante() { return botonCrearParticipante; }
}
