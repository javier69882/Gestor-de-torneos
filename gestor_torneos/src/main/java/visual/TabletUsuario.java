package visual;

import javax.swing.*;
import java.awt.*;
import Logico.*;

public class TabletUsuario extends JPanel {
    private JButton botonVerEquipos;
    private JButton botonVerTorneos;

    public TabletUsuario() {
        setBackground(new Color(180, 240, 200));
        setLayout(null);

        JLabel label = new JLabel("Vista de Usuario");
        label.setFont(new Font("Arial", Font.BOLD, 28));
        label.setForeground(Color.BLACK);
        label.setBounds(350, 60, 400, 50);
        add(label);

        botonVerEquipos = new JButton("Ver Equipos");
        botonVerEquipos.setBounds(350, 180, 200, 40);
        add(botonVerEquipos);

        botonVerTorneos = new JButton("Ver Torneos");
        botonVerTorneos.setBounds(350, 240, 200, 40);
        add(botonVerTorneos);

        // Listener para abrir nuevo JFrame al presionar "Ver Torneos"
        botonVerTorneos.addActionListener(e -> abrirVentanaVerTorneos());
    }

    private void abrirVentanaVerTorneos() {
        JFrame ventana = new JFrame("Ver Torneos Disponibles");
        ventana.setSize(600, 400);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(220, 240, 250));

        JLabel lbl = new JLabel("Selecciona un torneo para ver detalles:");
        lbl.setFont(new Font("Arial", Font.BOLD, 16));
        lbl.setBounds(40, 25, 400, 30);
        panel.add(lbl);

        DefaultListModel<String> modelo = new DefaultListModel<>();
        java.util.List<Torneo> torneos = visual.PanelPrincipal.depositoTorneos.getElementos();
        for (Torneo t : torneos) {
            modelo.addElement(t.getNombre());
        }
        JList<String> listaTorneos = new JList<>(modelo);
        listaTorneos.setFont(new Font("Arial", Font.PLAIN, 15));
        JScrollPane scroll = new JScrollPane(listaTorneos);
        scroll.setBounds(40, 70, 500, 200);
        panel.add(scroll);

        JButton btnVer = new JButton("Ver Detalles");
        btnVer.setBounds(220, 290, 150, 35);
        panel.add(btnVer);

        JTextArea detalles = new JTextArea();
        detalles.setEditable(false);
        detalles.setFont(new Font("Monospaced", Font.PLAIN, 13));
        detalles.setBounds(40, 340, 500, 80);
        panel.add(detalles);

        btnVer.addActionListener(e -> {
            int idx = listaTorneos.getSelectedIndex();
            if (idx < 0) {
                JOptionPane.showMessageDialog(ventana, "Selecciona un torneo.");
                return;
            }
            Torneo seleccionado = torneos.get(idx);
            StringBuilder info = new StringBuilder();
            info.append("Nombre: ").append(seleccionado.getNombre()).append("\n");
            info.append("Modalidad: ").append(seleccionado.getModalidad()).append("\n");
            info.append("Cantidad equipos: ").append(seleccionado.getCantidadEquipos()).append("\n");
            if (seleccionado instanceof TorneoFisico) {
                info.append("Tipo: FÃsico\nDeporte: ").append(((TorneoFisico) seleccionado).getDeporte()).append("\n");
            } else if (seleccionado instanceof TorneoVideojuegos) {
                info.append("Tipo: Videojuego\nVideojuego: ").append(((TorneoVideojuegos) seleccionado).getVideojuego()).append("\n");
            }
            info.append("Equipos:\n");
            for (Equipos eq : seleccionado.getEquipos()) {
                info.append("  - ").append(eq.getNombre()).append("\n");
            }
            detalles.setText(info.toString());
        });

        ventana.add(panel);
        ventana.setVisible(true);
    }

    public JButton getBotonVerEquipos() {
        return botonVerEquipos;
    }

    public JButton getBotonVerTorneos() {
        return botonVerTorneos;
    }
}
