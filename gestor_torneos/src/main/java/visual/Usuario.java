package visual;

import javax.swing.*;
import java.awt.*;

public class Usuario extends JPanel {
    private JButton botonIngresarAdmin;
    private JButton botonIngresarUsuario;
    private JButton botonCerrarSesion;

    public Usuario() {
        setLayout(null);
        setOpaque(true);
        setBackground(new Color(40, 40, 40, 200));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        setBounds(20, 20, 180, 150);

        botonIngresarAdmin = new JButton("Admin");
        botonIngresarUsuario = new JButton("Usuario");
        botonCerrarSesion = new JButton("Inicio");

        botonIngresarAdmin.setBounds(15, 15, 150, 30);
        botonIngresarUsuario.setBounds(15, 60, 150, 30);
        botonCerrarSesion.setBounds(15, 105, 150, 30);

        add(botonIngresarAdmin);
        add(botonIngresarUsuario);
        add(botonCerrarSesion);
    }

    public JButton getBotonIngresarAdmin() {
        return botonIngresarAdmin;
    }
    public JButton getBotonIngresarUsuario() {
        return botonIngresarUsuario;
    }
    public JButton getBotonCerrarSesion() {
        return botonCerrarSesion;
    }
}
