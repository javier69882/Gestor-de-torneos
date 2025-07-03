package visual;

import Logico.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TorneoEliminacionDirectaAdmin extends JPanel {
    private EliminacionDirectaDecorator torneo;
    private JComboBox<String> comboRondas;
    private JList<Partido> listaPartidos;
    private DefaultListModel<Partido> modeloPartidos;
    private JTextField txtGolesA, txtGolesB;
    private JButton btnRegistrar;
    private JLabel lblCampeon;

    public TorneoEliminacionDirectaAdmin(EliminacionDirectaDecorator torneo) {
        this.torneo = torneo;
        setLayout(null);
        setBackground(new Color(240, 255, 250));

        JLabel lblTitulo = new JLabel("Eliminación Directa - ADMIN");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitulo.setBounds(370, 20, 500, 40);
        add(lblTitulo);

        // Combo de rondas
        comboRondas = new JComboBox<>();
        for (int i = 0; i < torneo.rondas.size(); i++) {
            comboRondas.addItem("Ronda " + (i + 1));
        }
        comboRondas.setBounds(90, 90, 160, 32);
        add(comboRondas);

        // Lista de partidos de la ronda seleccionada
        modeloPartidos = new DefaultListModel<>();
        listaPartidos = new JList<>(modeloPartidos);
        listaPartidos.setFont(new Font("Monospaced", Font.PLAIN, 15));
        JScrollPane scrollLista = new JScrollPane(listaPartidos);
        scrollLista.setBounds(90, 140, 320, 350);
        add(scrollLista);

        comboRondas.addActionListener(e -> refrescarPartidos());

        // Panel registro resultado
        JPanel panelRegistro = new JPanel(null);
        panelRegistro.setBounds(450, 150, 450, 120);
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

        // Campeón
        lblCampeon = new JLabel();
        lblCampeon.setFont(new Font("Arial", Font.BOLD, 22));
        lblCampeon.setBounds(450, 300, 400, 40);
        add(lblCampeon);

        // Volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(30, 530, 100, 32);
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
            Partido seleccionado = listaPartidos.getSelectedValue();
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
        });

        refrescarBracket();
    }

    private void refrescarBracket() {
        // Refresca lista de rondas
        comboRondas.removeAllItems();
        for (int i = 0; i < torneo.rondas.size(); i++) {
            comboRondas.addItem("Ronda " + (i + 1));
        }
        // Selecciona la última ronda por defecto
        comboRondas.setSelectedIndex(torneo.rondas.size() - 1);
        refrescarPartidos();

        // Mostrar campeón si existe
        if (torneo.getCampeon() != null) {
            lblCampeon.setText("🏆 Campeón: " + torneo.getCampeon().getNombre());
        } else {
            lblCampeon.setText("");
        }
    }

    private void refrescarPartidos() {
        int ronda = comboRondas.getSelectedIndex();
        if (ronda < 0) return;
        modeloPartidos.clear();
        List<Partido> partidos = torneo.rondas.get(ronda);
        for (Partido p : partidos) modeloPartidos.addElement(p);
    }
}
