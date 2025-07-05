package visual;

import Logico.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.time.format.DateTimeFormatter;

/**
 * panel de administración para torneos de eliminación directa
 * permite ver, registrar resultados y visualizar el bracket de rondas
 * usa el patron decorator para extender la funcionalidad del torneo base
 */
public class TorneoEliminacionDirectaAdmin extends JPanel {
    private EliminacionDirectaDecorator torneo;
    private JComboBox<String> comboRondas;
    private JList<String> listaPartidos;
    private DefaultListModel<String> modeloPartidos;
    private JTextField txtGolesA, txtGolesB;
    private JButton btnRegistrar;
    private JLabel lblCampeon;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private JTextArea areaInfo;

    /**
     * crea el panel de administración para torneo de eliminación directa
     * @param torneo decorador de eliminación directa (patron decorator)
     */
    public TorneoEliminacionDirectaAdmin(EliminacionDirectaDecorator torneo) {
        this.torneo = torneo;
        setLayout(null);
        setBackground(new Color(230, 255, 250));

        // área de texto con info del torneo
        areaInfo = new JTextArea();
        areaInfo.setEditable(false);
        areaInfo.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaInfo.setBackground(new Color(240, 250, 255));
        JScrollPane scrollInfo = new JScrollPane(areaInfo);
        scrollInfo.setBounds(240, 10, 720, 110);
        add(scrollInfo);

        // título
        JLabel lblTitulo = new JLabel("Eliminacion Directa - ADMIN");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setBounds(0, 130, 1200, 40);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        // combo de rondas
        comboRondas = new JComboBox<>();
        for (int i = 0; i < torneo.rondas.size(); i++) {
            comboRondas.addItem("Ronda " + (i + 1));
        }
        comboRondas.setBounds(440, 180, 320, 36);
        add(comboRondas);

        // lista de partidos
        modeloPartidos = new DefaultListModel<>();
        listaPartidos = new JList<>(modeloPartidos);
        listaPartidos.setFont(new Font("Monospaced", Font.PLAIN, 20));
        JScrollPane scrollLista = new JScrollPane(listaPartidos);
        scrollLista.setBounds(240, 220, 720, 260);
        add(scrollLista);

        comboRondas.addActionListener(e -> refrescarPartidos());

        // panel para registrar resultado de partido
        JPanel panelRegistro = new JPanel(null);
        panelRegistro.setBounds(340, 500, 520, 80);
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

        add(panelRegistro);

        // campeón
        lblCampeon = new JLabel();
        lblCampeon.setFont(new Font("Arial", Font.BOLD, 20));
        lblCampeon.setBounds(0, 600, 1200, 35);
        lblCampeon.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblCampeon);

        // botón volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(40, 850, 120, 35);
        add(btnVolver);

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
            int idx = listaPartidos.getSelectedIndex();
            if (idx >= 0) {
                Partido seleccionado = torneo.rondas.get(comboRondas.getSelectedIndex()).get(idx);
                if (seleccionado != null && !seleccionado.isJugado()) {
                    try {
                        int golesA = Integer.parseInt(txtGolesA.getText());
                        int golesB = Integer.parseInt(txtGolesB.getText());
                        torneo.registrarResultado(seleccionado, golesA, golesB);
                        txtGolesA.setText("");
                        txtGolesB.setText("");
                        refrescarBracket();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Ingrese numeros validos.");
                    } catch (ValorNullException ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione un partido no jugado.");
                }
            }
        });

        refrescarBracket();
    }

    /**
     * actualiza info, rondas y campeón en la interfaz
     */
    private void refrescarBracket() {
        comboRondas.removeAllItems();
        for (int i = 0; i < torneo.rondas.size(); i++) {
            comboRondas.addItem("Ronda " + (i + 1));
        }
        comboRondas.setSelectedIndex(torneo.rondas.size() - 1);
        refrescarPartidos();

        StringBuilder info = new StringBuilder();
        info.append("Nombre: ").append(torneo.getNombre()).append("\n");
        info.append("Modalidad: ").append(torneo.getModalidad()).append("\n");
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
     * actualiza la lista de partidos segun ronda seleccionada
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
