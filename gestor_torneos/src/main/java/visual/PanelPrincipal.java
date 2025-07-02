package visual;

import Logico.*;
import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {
    public static Deposito<Torneo> depositoTorneos = new Deposito<>();

    private String estadoUsuario; // null, "admin", "usuario"
    private Usuario panelUsuario;
    private JPanel panelCentral;

    public PanelPrincipal() {
        setLayout(null);
        setBackground(Color.BLACK);
        estadoUsuario = null;

        panelUsuario = new Usuario();
        add(panelUsuario);

        panelCentral = new Bienvenida();
        panelCentral.setBounds(0, 0, 1200, 1000);
        add(panelCentral);

        panelUsuario.getBotonIngresarAdmin().addActionListener(e -> mostrarAdmin());
        panelUsuario.getBotonIngresarUsuario().addActionListener(e -> mostrarUsuario());
        panelUsuario.getBotonCerrarSesion().addActionListener(e -> volverABienvenida());
    }

    private void mostrarAdmin() {
        estadoUsuario = "admin";
        remove(panelCentral);
        panelCentral = new PizarraAdmin();
        panelCentral.setBounds(0, 0, 1200, 1000);
        add(panelCentral);
        setComponentZOrder(panelUsuario, 0);

        ((PizarraAdmin) panelCentral).getBotonEquipos().addActionListener(e -> mostrarEquipos());

        repaint();
        revalidate();
    }

    private void mostrarUsuario() {
        estadoUsuario = "usuario";
        remove(panelCentral);
        panelCentral = new TabletUsuario();
        panelCentral.setBounds(0, 0, 1200, 1000);
        add(panelCentral);
        setComponentZOrder(panelUsuario, 0);

        ((TabletUsuario) panelCentral).getBotonVerEquipos().addActionListener(e -> mostrarEquipos());

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

    private void mostrarEquipos() {
        remove(panelCentral);
        panelCentral = new Equipo();
        panelCentral.setBounds(0, 0, 1200, 1000);
        add(panelCentral);
        setComponentZOrder(panelUsuario, 0);
        repaint();
        revalidate();
    }
}