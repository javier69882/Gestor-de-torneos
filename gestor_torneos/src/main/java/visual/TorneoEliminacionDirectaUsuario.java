package visual;

import Logico.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class TorneoEliminacionDirectaUsuario extends JPanel {
    private EliminacionDirectaDecorator torneo;
    private JComboBox<String> comboRondas;
    private JList<String> listaPartidos;
    private DefaultListModel<String> modeloPartidos;
    private JLabel lblCampeon;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private JTextArea areaInfo;  // Nueva variable para info

    private final int OFFSET_X = 220;

    public TorneoEliminacionDirectaUsuario(EliminacionDirectaDecorator torneo) {
        this.torneo = torneo;
        setLayout(null);
        setBackground(new Color(245, 255, 240));

        // Área de texto con info del torneo arriba
        areaInfo = new JTextArea();
        areaInfo.setEditable(false);
        areaInfo.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaInfo.setBackground(new Color(240, 250, 255));
        JScrollPane scrollInfo = new JScrollPane(areaInfo);
        scrollInfo.setBounds(90 + OFFSET_X, 10, 380, 110);
        add(scrollInfo);

        JLabel lblTitulo = new JLabel("Eliminación Directa ");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitulo.setBounds(370 + OFFSET_X, 130, 500, 40);
        add(lblTitulo);

        comboRondas = new JComboBox<>();
        for (int i = 0; i < torneo.rondas.size(); i++) {
            comboRondas.addItem("Ronda " + (i + 1));
        }
        comboRondas.setBounds(90 + OFFSET_X, 180, 160, 32);
        add(comboRondas);

        modeloPartidos = new DefaultListModel<>();
        listaPartidos = new JList<>(modeloPartidos);
        listaPartidos.setFont(new Font("Monospaced", Font.PLAIN, 15));
        JScrollPane scrollLista = new JScrollPane(listaPartidos);
        scrollLista.setBounds(90 + OFFSET_X, 220, 380, 270);
        add(scrollLista);

        comboRondas.addActionListener(e -> refrescarPartidos());

        lblCampeon = new JLabel();
        lblCampeon.setFont(new Font("Arial", Font.BOLD, 22));
        lblCampeon.setBounds(450 + OFFSET_X, 300, 400, 40);
        add(lblCampeon);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(30 + OFFSET_X, 510, 100, 32);
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
        for (int i = 0; i < torneo.rondas.size(); i++) {
            comboRondas.addItem("Ronda " + (i + 1));
        }
        comboRondas.setSelectedIndex(torneo.rondas.size() - 1);
        refrescarPartidos();

        // Construir info del torneo para mostrar arriba
        StringBuilder info = new StringBuilder();
        info.append("Nombre: ").append(torneo.getNombre()).append("\n");
        info.append("Modalidad: ").append(torneo.getModalidad()).append("\n");
        info.append("Cantidad equipos: ").append(torneo.getCantidadEquipos()).append("\n");

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
        areaInfo.setText(info.toString());

        if (torneo.getCampeon() != null) {
            lblCampeon.setText(" Campeón: " + torneo.getCampeon().getNombre());
        } else {
            lblCampeon.setText("");
        }
    }

    private void refrescarPartidos() {
        int ronda = comboRondas.getSelectedIndex();
        if (ronda < 0) return;
        modeloPartidos.clear();
        List<Partido> partidos = torneo.rondas.get(ronda);
        for (Partido p : partidos) {
            String estado;
            if (p.isJugado()) {
                String fecha = "";
                if (p.getFechaHoraJugado() != null) {
                    fecha = " [" + p.getFechaHoraJugado().format(FORMATTER) + "]";
                }
                estado = p.getEquipoA().getNombre() + " " + p.getPuntajeA() + " - "
                        + p.getPuntajeB() + " " + p.getEquipoB().getNombre() + fecha;
            } else {
                estado = p.getEquipoA().getNombre() + " vs " + p.getEquipoB().getNombre() + " (pendiente)";
            }
            modeloPartidos.addElement(estado);
        }
    }
}