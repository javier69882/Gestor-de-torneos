package visual;

import Logico.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * panel para administracion, permite crear y acceder a torneos
 * al crear torneos se usa el patron decorator para agregar modalidad
 * @pattern decorator
 */
public class PizarraAdmin extends JPanel {
    private Image fondo;
    private JButton botonCrearTorneo;
    private JButton botonAccederTorneo;
    private JButton botonEquipos;

    /**
     * crea el panel de administracion con sus botones principales
     */
    public PizarraAdmin() {
        setPreferredSize(new Dimension(1000, 1000));
        setLayout(null);

        ImageIcon fondoIcon = new ImageIcon(getClass().getResource("/Fondos/pizarra.png"));
        fondo = fondoIcon.getImage();

        botonCrearTorneo = new JButton("Crear Torneo");
        botonAccederTorneo = new JButton("Acceder Torneo");
        botonEquipos = new JButton("Gestionar Equipos");

        botonCrearTorneo.setBounds(80, 300, 150, 30);
        botonAccederTorneo.setBounds(80, 350, 150, 30);
        botonEquipos.setBounds((800 - 150) / 2, 700, 150, 30);

        add(botonCrearTorneo);
        add(botonAccederTorneo);
        add(botonEquipos);

        botonCrearTorneo.addActionListener(e -> abrirVentanaCrearTorneo());
        botonAccederTorneo.addActionListener(e -> abrirVentanaAccederTorneo());
    }

    /**
     * retorna el boton para gestionar equipos
     * @return boton equipos
     */
    public JButton getBotonEquipos() {
        return botonEquipos;
    }

    /**
     * abre la ventana para crear un nuevo torneo usando patron decorator
     */
    private void abrirVentanaCrearTorneo() {
        JFrame ventana = new JFrame("Crear Nuevo Torneo");
        ventana.setSize(600, 650);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(220, 220, 250));

        JLabel lblNombre = new JLabel("Nombre del torneo:");
        JTextField txtNombre = new JTextField();
        lblNombre.setBounds(30, 20, 140, 25);
        txtNombre.setBounds(180, 20, 350, 25);

        JLabel lblTipo = new JLabel("Tipo de torneo:");
        String[] tipos = {"Físico", "Videojuego"};
        JComboBox<String> comboTipo = new JComboBox<>(tipos);
        lblTipo.setBounds(30, 60, 140, 25);
        comboTipo.setBounds(180, 60, 200, 25);

        JLabel lblDeporteVideojuego = new JLabel("Deporte:");
        JTextField txtDeporteVideojuego = new JTextField();
        lblDeporteVideojuego.setBounds(30, 100, 140, 25);
        txtDeporteVideojuego.setBounds(180, 100, 200, 25);

        JLabel lblModalidad = new JLabel("Modalidad:");
        JComboBox<Modalidad> comboModalidad = new JComboBox<>(Modalidad.values());
        lblModalidad.setBounds(30, 140, 140, 25);
        comboModalidad.setBounds(180, 140, 200, 25);

        JLabel lblCantidadEquipos = new JLabel("Cantidad de equipos:");
        JComboBox<CantidadEquipos> comboCantidadEquipos = new JComboBox<>(CantidadEquipos.values());
        lblCantidadEquipos.setBounds(30, 180, 140, 25);
        comboCantidadEquipos.setBounds(180, 180, 200, 25);

        JLabel lblEquipos = new JLabel("Equipos participantes:");
        lblEquipos.setBounds(30, 220, 180, 25);

        JPanel panelChecks = new JPanel();
        panelChecks.setLayout(new GridLayout(0, 1));
        JScrollPane scrollChecks = new JScrollPane(panelChecks);
        scrollChecks.setBounds(30, 250, 500, 220);

        List<Equipos> equiposDisponibles = Equipo.getEquiposCreados();
        ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
        for (Equipos eq : equiposDisponibles) {
            JCheckBox cb = new JCheckBox(eq.getNombre());
            cb.setFont(new Font("Arial", Font.PLAIN, 16));
            panelChecks.add(cb);
            checkBoxes.add(cb);
        }

        JButton btnCrear = new JButton("Crear Torneo");
        btnCrear.setBounds(230, 500, 140, 35);

        comboTipo.addActionListener(e -> {
            if (comboTipo.getSelectedIndex() == 0) {
                lblDeporteVideojuego.setText("Deporte:");
            } else {
                lblDeporteVideojuego.setText("Videojuego:");
            }
        });

        panel.add(lblNombre); panel.add(txtNombre);
        panel.add(lblTipo); panel.add(comboTipo);
        panel.add(lblDeporteVideojuego); panel.add(txtDeporteVideojuego);
        panel.add(lblModalidad); panel.add(comboModalidad);
        panel.add(lblCantidadEquipos); panel.add(comboCantidadEquipos);
        panel.add(lblEquipos); panel.add(scrollChecks);
        panel.add(btnCrear);

        ventana.add(panel);
        ventana.setVisible(true);

        btnCrear.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String deporteVideojuego = txtDeporteVideojuego.getText().trim();
            Modalidad modalidad = (Modalidad) comboModalidad.getSelectedItem();
            CantidadEquipos cantidad = (CantidadEquipos) comboCantidadEquipos.getSelectedItem();

            List<Equipos> equiposSeleccionados = new ArrayList<>();
            for (int i = 0; i < checkBoxes.size(); i++) {
                if (checkBoxes.get(i).isSelected()) {
                    equiposSeleccionados.add(equiposDisponibles.get(i));
                }
            }

            int cantidadEsperada = 0;
            if (cantidad == CantidadEquipos.CUATRO) cantidadEsperada = 4;
            else if (cantidad == CantidadEquipos.OCHO) cantidadEsperada = 8;
            else if (cantidad == CantidadEquipos.DIESEIS) cantidadEsperada = 16;

            if (nombre.isEmpty() || deporteVideojuego.isEmpty()) {
                JOptionPane.showMessageDialog(ventana, "Completa todos los campos.");
                return;
            }
            if (equiposSeleccionados.size() != cantidadEsperada) {
                JOptionPane.showMessageDialog(ventana, "Debes seleccionar exactamente " + cantidadEsperada + " equipos.");
                return;
            }

            // uso de patron decorator
            ITorneo torneoBase;
            if (comboTipo.getSelectedIndex() == 0) {
                torneoBase = new TorneoFisico(nombre, equiposSeleccionados, cantidad, deporteVideojuego);
            } else {
                torneoBase = new TorneoVideojuegos(nombre, equiposSeleccionados, cantidad, deporteVideojuego);
            }

            ITorneo torneoDecorado;
            switch (modalidad) {
                case ELIMINACIONDIRECTA:
                    torneoDecorado = new EliminacionDirectaDecorator(torneoBase);
                    break;
                case DOBLEELIMINACION:
                    torneoDecorado = new DobleEliminacionDecorator(torneoBase);
                    break;
                case LIGASIMPLE:
                    torneoDecorado = new LigaSimple(torneoBase);
                    break;
                default:
                    torneoDecorado = torneoBase;
                    break;
            }
            PanelPrincipal.depositoTorneos.addElemento(torneoDecorado);
            JOptionPane.showMessageDialog(ventana, "¡Torneo creado exitosamente!");
            ventana.dispose();
        });
    }

    /**
     * abre la ventana para acceder a un torneo existente
     */
    private void abrirVentanaAccederTorneo() {
        Container parent = this.getParent();
        while (parent != null && !(parent instanceof PanelPrincipal)) {
            parent = parent.getParent();
        }
        if (!(parent instanceof PanelPrincipal)) {
            JOptionPane.showMessageDialog(this, "No se pudo encontrar el panel principal.");
            return;
        }
        PanelPrincipal panelPrincipal = (PanelPrincipal) parent;

        JFrame ventana = new JFrame("Seleccionar Torneo");
        ventana.setSize(600, 400);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(230, 255, 230));

        JLabel lbl = new JLabel("Selecciona un torneo:");
        lbl.setFont(new Font("Arial", Font.BOLD, 18));
        lbl.setBounds(40, 25, 300, 30);
        panel.add(lbl);

        DefaultListModel<String> modelo = new DefaultListModel<>();
        java.util.List<ITorneo> torneos = PanelPrincipal.depositoTorneos.getElementos();
        for (ITorneo t : torneos) {
            modelo.addElement(t.getNombre());
        }
        JList<String> listaTorneos = new JList<>(modelo);
        listaTorneos.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scroll = new JScrollPane(listaTorneos);
        scroll.setBounds(40, 70, 500, 200);
        panel.add(scroll);

        JButton btnVer = new JButton("Ver Detalles");
        btnVer.setBounds(220, 290, 150, 35);
        panel.add(btnVer);

        btnVer.addActionListener(e -> {
            int idx = listaTorneos.getSelectedIndex();
            if (idx < 0) {
                JOptionPane.showMessageDialog(ventana, "Selecciona un torneo.");
                return;
            }
            ITorneo seleccionado = torneos.get(idx);
            ventana.dispose();

            panelPrincipal.remove(panelPrincipal.panelCentral);

            if (seleccionado instanceof EliminacionDirectaDecorator) {
                panelPrincipal.panelCentral = new TorneoEliminacionDirectaAdmin((EliminacionDirectaDecorator) seleccionado);
            } else if (seleccionado instanceof DobleEliminacionDecorator) {
                panelPrincipal.panelCentral = new TorneoDobleEliminacionAdmin((DobleEliminacionDecorator) seleccionado);
            } else if ("LIGA_SIMPLE".equalsIgnoreCase(seleccionado.getModalidad())) {
                panelPrincipal.panelCentral = new TorneoLigaSimpleAdmin(seleccionado);
            } else {
                panelPrincipal.panelCentral = new TorneoActualAdmin(seleccionado);
            }

            panelPrincipal.panelCentral.setBounds(0, 0, 1200, 1000);
            panelPrincipal.add(panelPrincipal.panelCentral);
            panelPrincipal.setComponentZOrder(panelPrincipal.panelUsuario, 0);
            panelPrincipal.repaint();
            panelPrincipal.revalidate();
        });

        ventana.add(panel);
        ventana.setVisible(true);
    }

    /**
     * pinta el fondo de pizarra
     * @param g contexto grafico
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        FontMetrics metrics = g.getFontMetrics();
        g.drawString("Gestor de Torneos", (getWidth() - metrics.stringWidth("Gestor de Torneos")) / 2, metrics.getHeight());
    }
}
