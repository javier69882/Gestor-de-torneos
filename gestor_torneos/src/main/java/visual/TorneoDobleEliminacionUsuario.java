package visual;

import Logico.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.time.format.DateTimeFormatter;

/**
 * panel para visualizar torneos de doble eliminacion en modo usuario
 * muestra rondas ida y vuelta y el campeon si existe
 * usa el patron decorator para extender funcionalidad del torneo base
 */
public class TorneoDobleEliminacionUsuario extends JPanel {
    private DobleEliminacionDecorator torneo;
    private JComboBox<String> comboRondas;
    private JList<String> listaIda, listaVuelta;
    private DefaultListModel<String> modeloIda, modeloVuelta;
    private JLabel lblCampeon;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private final int OFFSET_X = 220;
    private JTextArea areaInfo;

    /**
     * crea el panel para visualizar doble eliminacion al usuario
     * @param torneo decorador de doble eliminacion
     */
    public TorneoDobleEliminacionUsuario(DobleEliminacionDecorator torneo) {
        this.torneo = torneo;
        setLayout(null);
        setBackground(new Color(245, 255, 240));

        areaInfo = new JTextArea();
        areaInfo.setEditable(false);
        areaInfo.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaInfo.setBackground(new Color(240, 250, 255));
        JScrollPane scrollInfo = new JScrollPane(areaInfo);
        scrollInfo.setBounds(20 + OFFSET_X, 10, 900, 110);
        add(scrollInfo);

        JLabel lblTitulo = new JLabel("Doble Eliminacion - Vista Usuario");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitulo.setBounds(370 + OFFSET_X, 130, 500, 40);
        add(lblTitulo);

        comboRondas = new JComboBox<>();
        for (int i = 0; i < torneo.rondasIda.size(); i++) {
            comboRondas.addItem("Ronda " + (i + 1));
        }
        comboRondas.setBounds(90 + OFFSET_X, 180, 160, 32);
        add(comboRondas);

        modeloIda = new DefaultListModel<>();
        modeloVuelta = new DefaultListModel<>();
        listaIda = new JList<>(modeloIda);
        listaIda.setFont(new Font("Monospaced", Font.PLAIN, 15));
        listaVuelta = new JList<>(modeloVuelta);
        listaVuelta.setFont(new Font("Monospaced", Font.PLAIN, 15));

        JLabel lblIda = new JLabel("Ida:");
        lblIda.setBounds(90 + OFFSET_X, 220, 100, 24);
        add(lblIda);
        JScrollPane scrollIda = new JScrollPane(listaIda);
        scrollIda.setBounds(90 + OFFSET_X, 250, 320, 160);
        add(scrollIda);

        JLabel lblVuelta = new JLabel("Vuelta:");
        lblVuelta.setBounds(90 + OFFSET_X, 420, 100, 24);
        add(lblVuelta);
        JScrollPane scrollVuelta = new JScrollPane(listaVuelta);
        scrollVuelta.setBounds(90 + OFFSET_X, 450, 320, 160);
        add(scrollVuelta);

        comboRondas.addActionListener(e -> refrescarPartidos());

        lblCampeon = new JLabel();
        lblCampeon.setFont(new Font("Arial", Font.BOLD, 22));
        lblCampeon.setBounds(450 + OFFSET_X, 620, 400, 40);
        add(lblCampeon);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(30 + OFFSET_X, 670, 100, 32);
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

    /**
     * actualiza info del torneo, rondas y campeon
     */
    private void refrescarVista() {
        comboRondas.removeAllItems();
        for (int i = 0; i < torneo.rondasIda.size(); i++) {
            comboRondas.addItem("Ronda " + (i + 1));
        }
        comboRondas.setSelectedIndex(torneo.rondasIda.size() - 1);
        refrescarPartidos();

        StringBuilder info = new StringBuilder();
        info.append("Nombre: ").append(torneo.getNombre()).append("\n");
        info.append("Modalidad: ").append(torneo.getModalidad()).append("\n");
        info.append("Fecha de creacion:").append(torneo.getFechaCreacion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append("\n");
        info.append("Cantidad equipos: ").append(torneo.getCantidadEquipos()).append("\n");

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

        areaInfo.setText(info.toString());

        if (torneo.getCampeon() != null) {
            lblCampeon.setText("Campeon: " + torneo.getCampeon().getNombre());
        } else {
            lblCampeon.setText("");
        }
    }

    /**
     * actualiza las listas de partidos segun la ronda seleccionada
     */
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
