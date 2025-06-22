package visual;

import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {
    private String estadoUsuario; // null, "admin", "usuario"
    private Usuario panelUsuario;
    private JPanel panelCentral; // Cambia entre bienvenida/admin/usuario

    public PanelPrincipal() {
        setLayout(null);
        setBackground(Color.BLACK);

        estadoUsuario = null;

        // Panel usuario flotante
        panelUsuario = new Usuario();
        add(panelUsuario);

        // Panel central


        panelCentral = new Bienvenida();
        panelCentral.setBounds(0, 0, 1200, 1000);
        add(panelCentral);

        // Listeners de los botones de ingreso
        panelUsuario.getBotonIngresarAdmin().addActionListener(e -> mostrarAdmin());
        panelUsuario.getBotonIngresarUsuario().addActionListener(e -> mostrarUsuario());
        // Listener para cerrar sesión
        panelUsuario.getBotonCerrarSesion().addActionListener(e -> volverABienvenida());
    }

    private void mostrarAdmin() {
        estadoUsuario = "admin";
        remove(panelCentral);
        panelCentral = new PizarraAdmin();
        panelCentral.setBounds(0, 0, 1200, 1000);
        add(panelCentral);
        setComponentZOrder(panelUsuario, 0); // Que quede encima
        repaint();
        revalidate();
    }

    private void mostrarUsuario() {
        estadoUsuario = "usuario";
        remove(panelCentral);
        panelCentral = new TabletUsuario();
        panelCentral.setBounds(0, 0, 1200, 1000);
        add(panelCentral);
        setComponentZOrder(panelUsuario, 0); // Que quede encima
        repaint();
        revalidate();
    }

    private void volverABienvenida() {
        estadoUsuario = null;
        remove(panelCentral);
        panelCentral = new Bienvenida();
        panelCentral.setBounds(0, 0, 1200, 1000);
        add(panelCentral);
        setComponentZOrder(panelUsuario, 0);
        repaint();
        revalidate();
    }
}
