package visual;

import Logico.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.time.format.DateTimeFormatter;

/**
 * @pattern Decorator
 * @tag Decorator, LigaSimple, Torneos
 *
 * Panel administrador para visualizar y gestionar un torneo en modalidad Liga Simple.
 * Permite ver partidos, registrar resultados y mostrar la tabla de posiciones.
 */
public class TorneoLigaSimpleAdmin extends JPanel {
    private ITorneo torneo;
    private JList<String> listaPartidos;
    private DefaultListModel<String> modeloPartidos;
    private JTable tablaPosiciones;
    private JButton btnRegistrar;
    private JTextField txtGolesA, txtGolesB;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private JTextArea areaInfo;

    public TorneoLigaSimpleAdmin(ITorneo torneo) {
        this.torneo = torneo;
        setLayout(new BorderLayout());

        // Información general del torneo y título
        JPanel panelSuperior = new JPanel(null);
        panelSuperior.setPreferredSize(new Dimension(1200, 150));
        add(panelSuperior, BorderLayout.NORTH);

        JLabel titulo = new JLabel("Torneo - Modalidad LIGA_SIMPLE", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(240, 5, 720, 30);
        panelSuperior.add(titulo);

        areaInfo = new JTextArea();
        areaInfo.setEditable(false);
        areaInfo.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaInfo.setBackground(new Color(240, 250, 255));

        JScrollPane scrollInfo = new JScrollPane(areaInfo);
        scrollInfo.setBounds(240, 40, 720, 100);
        panelSuperior.add(scrollInfo);

        // Panel inferior delimitado
        JPanel panelInferior = new JPanel(null);
        panelInferior.setBounds(10, 110, 1200, 500);
        add(panelInferior);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setBounds(160, 70, 1100, 500);
        splitPane.setDividerLocation(300);
        splitPane.setResizeWeight(0.3);

        // Lista de partidos
        modeloPartidos = new DefaultListModel<>();
        for (Partido p : torneo.getPartidos()) {
            String texto;
            if (p.isJugado()) {
                String fecha = "";
                if (p.getFechaHoraJugado() != null) {
                    fecha = " [" + p.getFechaHoraJugado().format(FORMATTER) + "]";
                }
                texto = p.getEquipoA().getNombre() + " " + p.getPuntajeA() + " - "
                        + p.getPuntajeB() + " " + p.getEquipoB().getNombre() + fecha;
            } else {
                texto = p.getEquipoA().getNombre() + " vs " + p.getEquipoB().getNombre() + " (pendiente)";
            }
            modeloPartidos.addElement(texto);
        }
        listaPartidos = new JList<>(modeloPartidos);
        listaPartidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollLista = new JScrollPane(listaPartidos);
        splitPane.setLeftComponent(scrollLista);

        JPanel panelDerecho = new JPanel(new BorderLayout());

        // Tabla de posiciones
        tablaPosiciones = new JTable(getDatosTabla(), new String[]{"Equipo", "PUNTOS", "JUGADOS", "GOLES FAVOR", "GOLES CONTRA"});
        JScrollPane scrollTabla = new JScrollPane(tablaPosiciones);
        JPanel panelTablaCentrada = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTablaCentrada.add(scrollTabla);
        panelDerecho.add(panelTablaCentrada, BorderLayout.CENTER);

        // Panel para registrar resultado de un partido
        JPanel panelRegistro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelRegistro.setPreferredSize(new Dimension(500, 80));
        panelRegistro.add(new JLabel("Goles Equipo A:"));
        txtGolesA = new JTextField(2);
        panelRegistro.add(txtGolesA);
        panelRegistro.add(new JLabel("Goles Equipo B:"));
        txtGolesB = new JTextField(2);
        panelRegistro.add(txtGolesB);

        btnRegistrar = new JButton("Registrar Resultado");
        panelRegistro.add(btnRegistrar);

        JButton btnVolver = new JButton("Volver");
        panelRegistro.add(btnVolver);

        btnRegistrar.addActionListener(e -> {
            int idx = listaPartidos.getSelectedIndex();
            if (idx >= 0) {
                Partido seleccionado = torneo.getPartidos().get(idx);
                if (seleccionado != null && !seleccionado.isJugado()) {
                    try {
                        int golesA = Integer.parseInt(txtGolesA.getText());
                        int golesB = Integer.parseInt(txtGolesB.getText());
                        torneo.registrarResultado(seleccionado, golesA, golesB);
                        actualizarVista();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Ingrese números válidos.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione un partido no jugado.");
                }
            }
        });

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

        panelDerecho.add(panelRegistro, BorderLayout.SOUTH);
        splitPane.setRightComponent(panelDerecho);
        panelInferior.add(splitPane);

        actualizarVista();
    }

    // Obtiene la matriz de datos para la tabla de posiciones
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

    // Actualiza la vista después de registrar un resultado
    private void actualizarVista() {
        modeloPartidos.clear();
        for (Partido p : torneo.getPartidos()) {
            String texto;
            if (p.isJugado()) {
                String fecha = "";
                if (p.getFechaHoraJugado() != null) {
                    fecha = " [" + p.getFechaHoraJugado().format(FORMATTER) + "]";
                }
                texto = p.getEquipoA().getNombre() + " " + p.getPuntajeA() + " - "
                        + p.getPuntajeB() + " " + p.getEquipoB().getNombre() + fecha;
            } else {
                texto = p.getEquipoA().getNombre() + " vs " + p.getEquipoB().getNombre() + " (pendiente)";
            }
            modeloPartidos.addElement(texto);
        }

        tablaPosiciones.setModel(new DefaultTableModel(getDatosTabla(),
                new String[]{"Equipo", "PUNTOS", "JUGADOS", "GOLES FAVOR", "GOLES CONTRA"}));
        repaint();

        // Información del torneo
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
    }
}
