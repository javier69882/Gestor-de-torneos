package visual;

import Logico.*;
import javax.swing.*;
import java.awt.*;

/**
 * panel de vista admin para mostrar informacion de un torneo seleccionado
 * soporta torneos decorados usando patron decorator
 */
public class TorneoActualAdmin extends JPanel {
    private ITorneo torneo;

    /**
     * crea el panel mostrando informacion basica del torneo seleccionado
     * @param torneo el torneo a mostrar
     */
    public TorneoActualAdmin(ITorneo torneo) {
        this.torneo = torneo;
        setLayout(null);
        setBackground(new Color(235, 245, 255));

        JLabel lblTitulo = new JLabel("Torneo Seleccionado (Modo Admin)");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setBounds(350, 30, 700, 40);
        add(lblTitulo);

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 16));

        StringBuilder info = new StringBuilder();
        info.append("Nombre: ").append(torneo.getNombre()).append("\n");
        info.append("Modalidad: ").append(torneo.getModalidad()).append("\n");
        info.append("Cantidad equipos: ").append(torneo.getCantidadEquipos()).append("\n");

        // muestra datos base segun tipo usando patron decorator
        ITorneo base = torneo;
        while (base instanceof TorneoDecorator) {
            base = ((TorneoDecorator) base).getBase();
        }

        if (base instanceof TorneoFisico) {
            info.append("Tipo: Fisico\n");
            info.append("Deporte: ").append(((TorneoFisico) base).getDeporte()).append("\n");
        } else if (base instanceof TorneoVideojuegos) {
            info.append("Tipo: Videojuego\n");
            info.append("Videojuego: ").append(((TorneoVideojuegos) base).getVideojuego()).append("\n");
        }

        info.append("Equipos:\n");
        for (Equipos eq : torneo.getEquipos()) {
            info.append("  - ").append(eq.getNombre()).append("\n");
        }

        area.setText(info.toString());
        JScrollPane scroll = new JScrollPane(area);
        scroll.setBounds(350, 90, 700, 320);
        add(scroll);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(350, 450, 120, 35);
        add(btnVolver);

        btnVolver.addActionListener(e -> {
            // vuelve al panel principal admin
            Container parent = this.getParent();
            while (parent != null && !(parent instanceof PanelPrincipal)) {
                parent = parent.getParent();
            }
            if (parent instanceof PanelPrincipal) {
                PanelPrincipal pp = (PanelPrincipal) parent;
                pp.remove(this);
                pp.panelCentral = new PizarraAdmin();
                pp.panelCentral.setBounds(0, 0, 1200, 1000);
                pp.add(pp.panelCentral);
                pp.setComponentZOrder(pp.panelUsuario, 0);
                pp.repaint();
                pp.revalidate();
            }
        });
    }
}
