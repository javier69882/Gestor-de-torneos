package visual;

import Logico.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TorneoDobleEliminacionAdmin extends JPanel {
    private DobleEliminacionDecorator torneo;
    private JComboBox<String> comboRondas;
    private JList<Partido> listaIda, listaVuelta;
    private DefaultListModel<Partido> modeloIda, modeloVuelta;
    private JTextField txtGolesA, txtGolesB;
    private JButton btnRegistrar;
    private JLabel lblCampeon;

    private final int OFFSET_X = 220;

    public TorneoDobleEliminacionAdmin(DobleEliminacionDecorator torneo) {
        this.torneo = torneo;
        setLayout(null);
        setBackground(new Color(240, 255, 250));

        JLabel lblTitulo = new JLabel("Doble Eliminación - ADMIN");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitulo.setBounds(370 + OFFSET_X, 20, 500, 40);
        add(lblTitulo);

        comboRondas = new JComboBox<>();
        for (int i = 0; i < torneo.rondasIda.size(); i++) {
            comboRondas.addItem("Ronda " + (i + 1));
        }
        comboRondas.setBounds(90 + OFFSET_X, 90, 160, 32);
        add(comboRondas);

        // Modelos y listas
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

        // Panel registro resultado
        JPanel panelRegistro = new JPanel(null);
        panelRegistro.setBounds(450 + OFFSET_X, 150, 450, 120);
        panelRegistro.setBackground(new Color(235, 245, 255));

        JLabel lblA = new JLabel("Goles Equipo A:");
        lblA.setBounds(20, 20, 120, 25);
        txtGolesA = new JTextField();
        txtGolesA.setBounds(140, 20, 50, 25);

        JLabel lblB = new JLabel("Goles Equipo B:");
        lblB.setBounds(210, 20, 120, 25);
        txtGolesB = new JTextField();
        txtGolesB.setBounds(320, 20, 50, 25);

        btnRegistrar = new JButton("Registrar Resultado");
        btnRegistrar.setBounds(120, 65, 180, 30);

        panelRegistro.add(lblA); panelRegistro.add(txtGolesA);
        panelRegistro.add(lblB); panelRegistro.add(txtGolesB);
        panelRegistro.add(btnRegistrar);
        add(panelRegistro);

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
                seleccionado = listaIda.getSelectedValue();
            } else if (!listaVuelta.isSelectionEmpty()) {
                seleccionado = listaVuelta.getSelectedValue();
            }
            if (seleccionado != null && !seleccionado.isJugado()) {
                try {
                    int golesA = Integer.parseInt(txtGolesA.getText());
                    int golesB = Integer.parseInt(txtGolesB.getText());
                    // No se permite empate en partidos de ronda
                    if (golesA == golesB) {
                        throw new ValorNullException("No puede haber empate en esta fase. Reingrese los datos.");
                    }
                    torneo.registrarResultado(seleccionado, golesA, golesB);
                    txtGolesA.setText("");
                    txtGolesB.setText("");
                    refrescarVista();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese números válidos.");
                } catch (ValorNullException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un partido no jugado.");
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
        for (Partido p : ida) modeloIda.addElement(p);
        for (Partido p : vuelta) modeloVuelta.addElement(p);
    }
}


