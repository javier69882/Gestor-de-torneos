package visual;

import Logico.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.time.format.DateTimeFormatter;

/**
 * Panel de visualización de torneos en modalidad Eliminación Directa (vista usuario).
 * Permite consultar el bracket, rondas y el campeón del torneo.
 * <p>
 * <b>Patrón de diseño:</b> Decorator.<br>
 * Esta clase utiliza una instancia de {@link EliminacionDirectaDecorator}
 * para extender dinámicamente la funcionalidad de un torneo base sin modificar su código.
 */
public class TorneoEliminacionDirectaUsuario extends JPanel {
    private EliminacionDirectaDecorator torneo;
    private JComboBox<String> comboRondas;
    private JList<String> listaPartidos;
    private DefaultListModel<String> modeloPartidos;
    private JLabel lblCampeon;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private JTextArea areaInfo;  // Muestra información básica del torneo

    private final int OFFSET_X = 220;

    /**
     * Construye la vista de usuario para un torneo de eliminación directa decorado.
     * @param torneo Instancia decorada de {@link EliminacionDirectaDecorator} (Decorator Pattern)
     */
    public TorneoEliminacionDirectaUsuario(EliminacionDirectaDecorator torneo) {
        this.torneo = torneo;
        setLayout(null);
        setBackground(new Color(245, 255, 240));

        // Panel informativo superior
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

        // Combo de rondas (permite navegar el bracket)
        comboRondas = new JComboBox<>();
        for (int i = 0; i < torneo.rondas.size(); i++) {
            comboRondas.addItem("Ronda " + (i + 1));
        }
        comboRondas.setBounds(90 + OFFSET_X, 180, 160, 32);
        add(comboRondas);

        // Lista de partidos de la ronda seleccionada
        modeloPartidos = new DefaultListModel<>();
        listaPartidos = new JList<>(modeloPartidos);
        listaPartidos.setFont(new Font("Monospaced", Font.PLAIN, 15));
        JScrollPane scrollLista = new JScrollPane(listaPartidos);
        scrollLista.setBounds(90 + OFFSET_X, 220, 380, 270);
        add(scrollLista);

        comboRondas.addActionListener(e -> refrescarPartidos());

        // Campeón del torneo (si ya hay uno)
        lblCampeon = new JLabel();
        lblCampeon.setFont(new Font("Arial", Font.BOLD, 22));
        lblCampeon.setBounds(450 + OFFSET_X, 300, 400, 40);
        add(lblCampeon);

        // Botón para volver a la pantalla principal del usuario
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

    /**
     * Actualiza la info del torneo, rondas y campeón en la interfaz.
     */
    private void refrescarVista() {
        comboRondas.removeAllItems();
        for (int i = 0; i < torneo.rondas.size(); i++) {
            comboRondas.addItem("Ronda " + (i + 1));
        }
        comboRondas.setSelectedIndex(torneo.rondas.size() - 1);
        refrescarPartidos();

        // Info del torneo
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
            info.append("Tipo: Físico\n");
            info.append("Deporte: ").append(((TorneoFisico) base).getDeporte()).append("\n");
        } else if (base instanceof TorneoVideojuegos) {
            info.append("Tipo: Videojuego\n");
            info.append("Videojuego: ").append(((TorneoVideojuegos) base).getVideojuego()).append("\n");
        }
        areaInfo.setText(info.toString());

        if (torneo.getCampeon() != null) {
            lblCampeon.setText("Campeón: " + torneo.getCampeon().getNombre());
        } else {
            lblCampeon.setText("");
        }
    }

    /**
     * Refresca la lista de partidos según la ronda seleccionada.
     */
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
