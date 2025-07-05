package visual;

import Logico.*;
import javax.swing.*;
import java.awt.*;

/**
 * panel principal de la aplicacion, gestiona el estado y la vista central segun el usuario
 * el deposito de torneos se inicializa usando singleton desde Main
 */
public class PanelPrincipal extends JPanel {
    /** deposito global de torneos, se usa singleton para inicializarlo desde Main */
    public static Deposito<ITorneo> depositoTorneos = new Deposito<>();

    private String estadoUsuario;
    public Usuario panelUsuario;
    public JPanel panelCentral;

    /**
     * crea el panel principal con el panel de usuario y la vista central
     */
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

    /**
     * muestra la vista de administrador
     */
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

    /**
     * muestra la vista de usuario
     */
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

    /**
     * vuelve a la pantalla de bienvenida
     */
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

    /**
     * muestra la vista de gestion de equipos
     */
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
