package visual;

import Logico.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class TorneoEliminacionDirectaAdmin extends JPanel {
    private EliminacionDirectaDecorator torneo;
    private JComboBox<String> comboRondas;
    private JList<String> listaPartidos;
    private DefaultListModel<String> modeloPartidos;
    private JTextField txtGolesA, txtGolesB;
    private JButton btnRegistrar;
    private JLabel lblCampeon;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public TorneoEliminacionDirectaAdmin(EliminacionDirectaDecorator torneo) {
        this.torneo = torneo;
        setLayout(null);
        setBackground(new Color(230, 255, 250)); // Más suave

        // Título centrado
        JLabel lblTitulo = new JLabel("Eliminación Directa - ADMIN");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setBounds(0, 35, 1200, 40);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        // Combo de rondas
        comboRondas = new JComboBox<>();
        for (int i = 0; i < torneo.rondas.size(); i++) {
            comboRondas.addItem("Ronda " + (i + 1));
        }
        comboRondas.setBounds(440, 100, 320, 36); // Centrado
        add(comboRondas);

        // Lista de partidos
        modeloPartidos = new DefaultListModel<>();
        listaPartidos = new JList<>(modeloPartidos);
        listaPartidos.setFont(new Font("Monospaced", Font.PLAIN, 20));
        JScrollPane scrollLista = new JScrollPane(listaPartidos);
        scrollLista.setBounds(240, 160, 720, 260); // Centrado y más alto
        add(scrollLista);

        comboRondas.addActionListener(e -> refrescarPartidos());

        // Panel registro resultado,
        JPanel panelRegistro = new JPanel(null);
        panelRegistro.setBounds(340, 440, 520, 80); // Bien debajo de la lista y centrado
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

        // Campeón, abajo centrado
        lblCampeon = new JLabel();
        lblCampeon.setFont(new Font("Arial", Font.BOLD, 20));
        lblCampeon.setBounds(0, 535, 1200, 35);
        lblCampeon.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblCampeon);

        // Botón Volver, bien abajo a la izquierda
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
                        JOptionPane.showMessageDialog(this, "Ingrese números válidos.");
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

    private void refrescarBracket() {
        comboRondas.removeAllItems();
        for (int i = 0; i < torneo.rondas.size(); i++) {
            comboRondas.addItem("Ronda " + (i + 1));
        }
        comboRondas.setSelectedIndex(torneo.rondas.size() - 1);
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
