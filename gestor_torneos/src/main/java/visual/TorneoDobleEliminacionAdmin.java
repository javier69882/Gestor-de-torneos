package visual;

import Logico.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.time.format.DateTimeFormatter;


/**
 * panel administrador para la modalidad de doble eliminacion
 * permite ver rondas, registrar resultados y mostrar campeon
 * usa el patron decorator para extender la funcionalidad de torneos
 */
public class TorneoDobleEliminacionAdmin extends JPanel {
    private DobleEliminacionDecorator torneo;
    private JComboBox<String> comboRondas;
    private JList<String> listaIda, listaVuelta;
    private DefaultListModel<String> modeloIda, modeloVuelta;
    private JTextField txtGolesA, txtGolesB;
    private JButton btnRegistrar;
    private JLabel lblCampeon;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private JTextArea areaInfo;

    /**
     * crea el panel para gestionar y visualizar el torneo doble eliminacion
     * @param torneo instancia decorada del torneo
     */
    public TorneoDobleEliminacionAdmin(DobleEliminacionDecorator torneo) {
        this.torneo = torneo;
        setLayout(new BorderLayout());
        setBackground(new Color(230, 255, 250));

        JLabel titulo = new JLabel("Torneo - Modalidad DOBLE_ELIMINACION", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        add(titulo, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(null);
        add(panelCentral, BorderLayout.CENTER);

        areaInfo = new JTextArea();
        areaInfo.setEditable(false);
        areaInfo.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaInfo.setBackground(new Color(240, 250, 255));
        JScrollPane scrollInfo = new JScrollPane(areaInfo);
        scrollInfo.setBounds(240, 10, 720, 110);
        panelCentral.add(scrollInfo);

        comboRondas = new JComboBox<>();
        for (int i = 0; i < torneo.rondasIda.size(); i++) {
            comboRondas.addItem("Ronda " + (i + 1));
        }
        comboRondas.setBounds(440, 130, 320, 36);
        panelCentral.add(comboRondas);

        JLabel lblIda = new JLabel("Ida:");
        lblIda.setFont(new Font("Arial", Font.BOLD, 17));
        lblIda.setBounds(350, 180, 70, 24);
        panelCentral.add(lblIda);

        JLabel lblVuelta = new JLabel("Vuelta:");
        lblVuelta.setFont(new Font("Arial", Font.BOLD, 17));
        lblVuelta.setBounds(770, 180, 90, 24);
        panelCentral.add(lblVuelta);

        modeloIda = new DefaultListModel<>();
        modeloVuelta = new DefaultListModel<>();
        listaIda = new JList<>(modeloIda);
        listaIda.setFont(new Font("Monospaced", Font.PLAIN, 18));
        JScrollPane scrollIda = new JScrollPane(listaIda);
        scrollIda.setBounds(220, 210, 420, 220);
        panelCentral.add(scrollIda);

        listaVuelta = new JList<>(modeloVuelta);
        listaVuelta.setFont(new Font("Monospaced", Font.PLAIN, 18));
        JScrollPane scrollVuelta = new JScrollPane(listaVuelta);
        scrollVuelta.setBounds(640, 210, 420, 220);
        panelCentral.add(scrollVuelta);

        comboRondas.addActionListener(e -> refrescarPartidos());

        JPanel panelRegistro = new JPanel(null);
        panelRegistro.setBounds(340, 440, 520, 80);
        panelRegistro.setBackground(new Color(240, 250, 255));
        panelRegistro.setBorder(BorderFactory.createTitledBorder("Registrar Resultado"));

        JLabel lblA = new JLabel("Goles Equipo A:");
        lblA.setBounds(30, 30, 120, 25);
        panelRegistro.add(lblA);
        txtGolesA = new JTextField();
        txtGolesA.setBounds(140, 30, 50, 25);
        panelRegistro.add(txtGolesA);

        JLabel lblB = new JLabel("Goles Equipo B:");
        lblB.setBounds(210, 30, 120, 25);
        panelRegistro.add(lblB);
        txtGolesB = new JTextField();
        txtGolesB.setBounds(320, 30, 50, 25);
        panelRegistro.add(txtGolesB);

        btnRegistrar = new JButton("Registrar Resultado");
        btnRegistrar.setBounds(390, 27, 120, 32);
        panelRegistro.add(btnRegistrar);

        panelCentral.add(panelRegistro);

        lblCampeon = new JLabel();
        lblCampeon.setFont(new Font("Arial", Font.BOLD, 20));
        lblCampeon.setBounds(0, 530, 1200, 35);
        lblCampeon.setHorizontalAlignment(SwingConstants.CENTER);
        panelCentral.add(lblCampeon);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(40, 850, 120, 35);
        panelCentral.add(btnVolver);

        btnVolver.addActionListener(e -> {
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

        btnRegistrar.addActionListener(e -> {
            Partido seleccionado = null;
            if (!listaIda.isSelectionEmpty()) {
                int idx = listaIda.getSelectedIndex();
                if (idx >= 0) {
                    seleccionado = torneo.rondasIda.get(comboRondas.getSelectedIndex()).get(idx);
                }
            } else if (!listaVuelta.isSelectionEmpty()) {
                int idx = listaVuelta.getSelectedIndex();
                if (idx >= 0) {
                    seleccionado = torneo.rondasVuelta.get(comboRondas.getSelectedIndex()).get(idx);
                }
            }
            if (seleccionado != null && !seleccionado.isJugado()) {
                try {
                    int golesA = Integer.parseInt(txtGolesA.getText());
                    int golesB = Integer.parseInt(txtGolesB.getText());
                    torneo.registrarResultado(seleccionado, golesA, golesB);
                    txtGolesA.setText("");
                    txtGolesB.setText("");
                    refrescarVista();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese numeros validos");
                } catch (ValorNullException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un partido no jugado");
            }
        });

        refrescarVista();
    }

    /**
     * actualiza la vista del panel, partidos, rondas y campeon
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
            lblCampeon.setText("\uD83C\uDFC6 Campeon: " + torneo.getCampeon().getNombre());
        } else {
            lblCampeon.setText("");
        }
    }

    /**
     * refresca la lista de partidos mostrados segun ronda seleccionada
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
                estado = p.getEquipoA().getNombre() + " " + p.getPuntajeA() + " - "
                        + p.getPuntajeB() + " " + p.getEquipoB().getNombre() + fecha;
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
                estado = p.getEquipoA().getNombre() + " " + p.getPuntajeA() + " - "
                        + p.getPuntajeB() + " " + p.getEquipoB().getNombre() + fecha;
            } else {
                estado = p.getEquipoA().getNombre() + " vs " + p.getEquipoB().getNombre() + " (pendiente)";
            }
            modeloVuelta.addElement(estado);
        }
    }
}
