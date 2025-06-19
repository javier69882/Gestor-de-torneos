package visual;


import javax.swing.*;
import java.awt.*;

public class Usuario extends JPanel {

    public Usuario(){
        setPreferredSize(new Dimension(200, 1000));


        setLayout(null);



        // Crear botones
        JButton botonCrearUsuario = new JButton("Crear Usuario");
        JButton botonIngresarAdmin = new JButton("Ingresar Admin");
        JButton botonIngresarUsuario = new JButton("Ingresar Usuario");

        // Establecer posiciones y tamaños de los botones
        botonCrearUsuario.setBounds(25, 150+50, 150, 30*2);
        botonIngresarAdmin.setBounds(25, 200+100, 150, 30);
        botonIngresarUsuario.setBounds(25, 250+100, 150, 30);

        // Añadir botones al panel
        add(botonCrearUsuario);
        add(botonIngresarAdmin);
        add(botonIngresarUsuario);
    }

}
