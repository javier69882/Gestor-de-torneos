package visual;

import Logico.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TorneoActualUsuario extends JPanel {
    private ITorneo torneo;
    private JTable tablaPosiciones;

    public TorneoActualUsuario(ITorneo torneo) {
        this.torneo = torneo;
        setLayout(null);
        setBackground(new Color(230, 250, 255));


        JLabel lblTitulo = new JLabel("Detalles del Torneo (Vista Usuario)");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setBounds(350, 20, 700, 40);
        add(lblTitulo);

        //Info básica del torneo
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 16));
        area.setBackground(new Color(245, 250, 255));

        StringBuilder info = new StringBuilder();
        info.append("Nombre: ").append(torneo.getNombre()).append("\n");
        info.append("Modalidad: ").append(torneo.getModalidad()).append("\n");
        info.append("Cantidad equipos: ").append(torneo.getCantidadEquipos()).append("\n");

        // Ver si el torneo base es físico o videojuego
        ITorneo base = torneo;
        while (base instanceof TorneoDecorator) {
            base = ((TorneoDecorator) base).getBase();
        }

        if (base instanceof TorneoFisico) {
            info.append("Tipo: Físico\n");
            info.append("Deporte: ").append(((TorneoFisico) base).getDeporte()).append("\n");
        } else if (base instanceof TorneoVideojuegos) {
            info.append("Tipo: Videojuego\n");
            info.append("Videojuego: ").append(((TorneoVideojuegos) base).getVideojuego()).append("\n");
        }

        area.setText(info.toString());
        JScrollPane scrollInfo = new JScrollPane(area);
        scrollInfo.setBounds(350, 70, 500, 100);
        add(scrollInfo);

        //Tabla de posiciones
        tablaPosiciones = new JTable(getDatosTabla(), new String[]{"Equipo", "PTS", "J", "GF", "GC"});
        JScrollPane scrollTabla = new JScrollPane(tablaPosiciones);
        scrollTabla.setBounds(350, 190, 500, 300);
        add(scrollTabla);

        // Botón Volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(350, 510, 120, 35);
        add(btnVolver);

        btnVolver.addActionListener(e -> {
            // Volver al TabletUsuario
            Container parent = this.getParent();
            while (parent != null && !(parent instanceof PanelPrincipal)) {
                parent = parent.getParent();
            }
            if (parent instanceof PanelPrincipal) {
                PanelPrincipal pp = (PanelPrincipal) parent;
                pp.remove(this);
                pp.panelCentral = new TabletUsuario();
                pp.panelCentral.setBounds(0, 0, 1200, 1000);
                pp.add(pp.panelCentral);
                pp.setComponentZOrder(pp.panelUsuario, 0);
                pp.repaint();
                pp.revalidate();
            }
        });
    }

    private Object[][] getDatosTabla() {
        List<PosicionLiga> tabla = torneo.getTablaPosiciones();
        Object[][] datos = new Object[tabla.size()][5];
        for (int i = 0; i < tabla.size(); i++) {
            PosicionLiga pos = tabla.get(i);
            datos[i][0] = pos.equipo.getNombre();
            datos[i][1] = pos.puntos;
            datos[i][2] = pos.jugados;
            datos[i][3] = pos.golesFavor;
            datos[i][4] = pos.golesContra;
        }
        return datos;
    }
}
