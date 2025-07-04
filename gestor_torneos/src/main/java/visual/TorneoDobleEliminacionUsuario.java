package visual;

import Logico.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class TorneoDobleEliminacionUsuario extends JPanel {
    private DobleEliminacionDecorator torneo;
    private JComboBox<String> comboRondas;
    private JList<String> listaIda, listaVuelta;
    private DefaultListModel<String> modeloIda, modeloVuelta;
    private JLabel lblCampeon;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private final int OFFSET_X = 220;

    public TorneoDobleEliminacionUsuario(DobleEliminacionDecorator torneo) {
        this.torneo = torneo;
        setLayout(null);
        setBackground(new Color(245, 255, 240));

        JLabel lblTitulo = new JLabel("Doble Eliminación - Vista Usuario");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitulo.setBounds(370 + OFFSET_X, 20, 500, 40);
        add(lblTitulo);

        comboRondas = new JComboBox<>();
        for (int i = 0; i < torneo.rondasIda.size(); i++) {
            comboRondas.addItem("Ronda " + (i + 1));
        }
        comboRondas.setBounds(90 + OFFSET_X, 90, 160, 32);
        add(comboRondas);

        modeloIda = new DefaultListModel<>();
        modeloVuelta = new DefaultListModel<>();
        listaIda = new JList<>(modeloIda);
        listaIda.setFont(new Font("Monospaced", Font.PLAIN, 15));
        listaVuelta = new JList<>(modeloVuelta);
        listaVuelta.setFont(new Font("Monospaced", Font.PLAIN, 15));

        JLabel lblIda = new JLabel("Ida:");
        lblIda.setBounds(90 + OFFSET_X, 130, 100, 24);
        add(lblIda);
        JScrollPane scrollIda = new JScrollPane(listaIda);
        scrollIda.setBounds(90 + OFFSET_X, 160, 320, 160);
        add(scrollIda);

        JLabel lblVuelta = new JLabel("Vuelta:");
        lblVuelta.setBounds(90 + OFFSET_X, 330, 100, 24);
        add(lblVuelta);
        JScrollPane scrollVuelta = new JScrollPane(listaVuelta);
        scrollVuelta.setBounds(90 + OFFSET_X, 360, 320, 160);
        add(scrollVuelta);

        comboRondas.addActionListener(e -> refrescarPartidos());

        lblCampeon = new JLabel();
        lblCampeon.setFont(new Font("Arial", Font.BOLD, 22));
        lblCampeon.setBounds(450 + OFFSET_X, 300, 400, 40);
        add(lblCampeon);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(30 + OFFSET_X, 530, 100, 32);
        add(btnVolver);

        btnVolver.addActionListener(e -> {
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

        refrescarVista();
    }

    private void refrescarVista() {
        comboRondas.removeAllItems();
        for (int i = 0; i < torneo.rondasIda.size(); i++) {
            comboRondas.addItem("Ronda " + (i + 1));
        }
        comboRondas.setSelectedIndex(torneo.rondasIda.size() - 1);
        refrescarPartidos();

        if (torneo.getCampeon() != null) {
            lblCampeon.setText("Campeón: " + torneo.getCampeon().getNombre());
        } else {
            lblCampeon.setText("");
        }
    }

    private void refrescarPartidos() {
        int ronda = comboRondas.getSelectedIndex();
        if (ronda < 0) return;
        modeloIda.clear();
        modeloVuelta.clear();
        List<Partido> ida = torneo.rondasIda.get(ronda);
        List<Partido> vuelta = torneo.rondasVuelta.get(ronda);
        for (Partido p : ida) {
            String estado;
            if (p.isJugado()) {
                String fecha = "";
                if (p.getFechaHoraJugado() != null) {
                    fecha = " [" + p.getFechaHoraJugado().format(FORMATTER) + "]";
                }
                estado = p.getEquipoA().getNombre() + " " + p.getPuntajeA() + " - " +
                        p.getPuntajeB() + " " + p.getEquipoB().getNombre() + fecha;
            } else {
                estado = p.getEquipoA().getNombre() + " vs " + p.getEquipoB().getNombre() + " (pendiente)";
            }
            modeloIda.addElement(estado);
        }
        for (Partido p : vuelta) {
            String estado;
            if (p.isJugado()) {
                String fecha = "";
                if (p.getFechaHoraJugado() != null) {
                    fecha = " [" + p.getFechaHoraJugado().format(FORMATTER) + "]";
                }
                estado = p.getEquipoA().getNombre() + " " + p.getPuntajeA() + " - " +
                        p.getPuntajeB() + " " + p.getEquipoB().getNombre() + fecha;
            } else {
                estado = p.getEquipoA().getNombre() + " vs " + p.getEquipoB().getNombre() + " (pendiente)";
            }
            modeloVuelta.addElement(estado);
        }
    }


}
