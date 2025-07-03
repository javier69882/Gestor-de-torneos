package visual;

import Logico.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TorneoLigaSimpleAdmin extends JPanel {
    private ITorneo torneo;
    private JList<Partido> listaPartidos;
    private DefaultListModel<Partido> modeloPartidos;
    private JTable tablaPosiciones;
    private JButton btnRegistrar;
    private JTextField txtGolesA, txtGolesB;

    public TorneoLigaSimpleAdmin(ITorneo torneo) {
        this.torneo = torneo;
        setLayout(new BorderLayout());


        JLabel titulo = new JLabel("Torneo - Modalidad LIGA_SIMPLE", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        add(titulo, BorderLayout.NORTH);


        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(0, 250, 0, 0)); // 250 px margen izquierdo


        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(250); // Ajusta para más/menos ancho de la lista

        // Panel izquierdo lista de partidos
        modeloPartidos = new DefaultListModel<>();
        for (Partido p : torneo.getPartidos()) modeloPartidos.addElement(p);
        listaPartidos = new JList<>(modeloPartidos);
        listaPartidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollLista = new JScrollPane(listaPartidos);
        splitPane.setLeftComponent(scrollLista);

        // tabla de posiciones + registro resultado
        JPanel panelDerecho = new JPanel(new BorderLayout());

        //Tabla de posiciones con scroll
        tablaPosiciones = new JTable(getDatosTabla(), new String[]{"Equipo", "PTS", "J", "GF", "GC"});
        JScrollPane scrollTabla = new JScrollPane(tablaPosiciones);
        panelDerecho.add(scrollTabla, BorderLayout.CENTER);

        //  Panel de registro resultado
        JPanel panelRegistro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelRegistro.setPreferredSize(new Dimension(500, 80)); // altura fija para que siempre se vea

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
            Partido seleccionado = listaPartidos.getSelectedValue();
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


        panelPrincipal.add(splitPane, BorderLayout.CENTER);


        add(panelPrincipal, BorderLayout.CENTER);
    }

    //Generar datos para la tabla de posiciones
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

    // Refrescar la vista después de registrar un resultado
    private void actualizarVista() {
        modeloPartidos.clear();
        for (Partido p : torneo.getPartidos()) modeloPartidos.addElement(p);

        tablaPosiciones.setModel(new DefaultTableModel(getDatosTabla(),
                new String[]{"Equipo", "PTS", "J", "GF", "GC"}));
        repaint();
    }
}
