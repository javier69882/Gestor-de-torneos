package visual;

import javax.swing.*;
import java.awt.*;
import Logico.*;

/**
 * panel de vista usuario, permite ver equipos y torneos disponibles
 */
public class TabletUsuario extends JPanel {
    private JButton botonVerEquipos;
    private JButton botonVerTorneos;

    /**
     * crea el panel de usuario con botones para ver equipos y torneos
     */
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

        // listener para abrir panel de torneos
        botonVerTorneos.addActionListener(e -> abrirPanelVerTorneos());
    }

    /**
     * abre la ventana para seleccionar y ver torneos disponibles
     */
    private void abrirPanelVerTorneos() {
        // referencia al PanelPrincipal
        Container parent = this.getParent();
        while (parent != null && !(parent instanceof PanelPrincipal)) {
            parent = parent.getParent();
        }
        if (!(parent instanceof PanelPrincipal)) {
            JOptionPane.showMessageDialog(this, "No se pudo encontrar el panel principal");
            return;
        }
        PanelPrincipal panelPrincipal = (PanelPrincipal) parent;

        JFrame ventana = new JFrame("Ver Torneos Disponibles");
        ventana.setSize(600, 400);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(220, 240, 250));

        JLabel lbl = new JLabel("Selecciona un torneo para ver detalles:");
        lbl.setFont(new Font("Arial", Font.BOLD, 16));
        lbl.setBounds(40, 25, 400, 30);
        panel.add(lbl);

        DefaultListModel<String> modelo = new DefaultListModel<>();
        java.util.List<ITorneo> torneos = visual.PanelPrincipal.depositoTorneos.getElementos();
        for (ITorneo t : torneos) {
            modelo.addElement(t.getNombre());
        }
        JList<String> listaTorneos = new JList<>(modelo);
        listaTorneos.setFont(new Font("Arial", Font.PLAIN, 15));
        JScrollPane scroll = new JScrollPane(listaTorneos);
        scroll.setBounds(40, 70, 500, 200);
        panel.add(scroll);

        JButton btnVer = new JButton("Ver Detalles");
        btnVer.setBounds(220, 290, 150, 35);
        panel.add(btnVer);

        btnVer.addActionListener(ev -> {
            int idx = listaTorneos.getSelectedIndex();
            if (idx < 0) {
                JOptionPane.showMessageDialog(ventana, "Selecciona un torneo");
                return;
            }
            ITorneo seleccionado = torneos.get(idx);
            ventana.dispose();

            // cambia el panel central en PanelPrincipal segun modalidad
            panelPrincipal.remove(panelPrincipal.panelCentral);

            if (seleccionado instanceof EliminacionDirectaDecorator) {
                panelPrincipal.panelCentral = new TorneoEliminacionDirectaUsuario((EliminacionDirectaDecorator)seleccionado);
            } else if (seleccionado instanceof DobleEliminacionDecorator) {
                panelPrincipal.panelCentral = new TorneoDobleEliminacionUsuario((DobleEliminacionDecorator)seleccionado);
            } else if ("LIGA_SIMPLE".equals(seleccionado.getModalidad())) {
                panelPrincipal.panelCentral = new TorneoLigaSimpleUsuario(seleccionado);
            } else {
                panelPrincipal.panelCentral = new TorneoActualUsuario(seleccionado);
            }
            panelPrincipal.panelCentral.setBounds(0, 0, 1200, 1000);
            panelPrincipal.add(panelPrincipal.panelCentral);
            panelPrincipal.setComponentZOrder(panelPrincipal.panelUsuario, 0);
            panelPrincipal.repaint();
            panelPrincipal.revalidate();
        });

        ventana.add(panel);
        ventana.setVisible(true);
    }

    /**
     * retorna el boton para ver equipos
     */
    public JButton getBotonVerEquipos() {
        return botonVerEquipos;
    }

    /**
     * retorna el boton para ver torneos
     */
    public JButton getBotonVerTorneos() {
        return botonVerTorneos;
    }
}
