package visual;

import Logico.Equipos;
import Logico.Participantes;
import Logico.ValorNullException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * panel para gestionar equipos y participantes en la interfaz grafica
 * permite crear, borrar y ver equipos y participantes
 */
public class Equipo extends JPanel {
    private JButton botonCrearEquipo;
    private JButton botonBorrarEquipo;
    private JButton botonCrearParticipante;
    private JButton botonVerEquiposParticipantes;

    private static List<Participantes> participantesCreados = new ArrayList<>();
    private static List<Equipos> equiposCreados = new ArrayList<>();

    /**
     * crea el panel con los botones y acciones para gestionar equipos y participantes
     */
    public Equipo() {
        setLayout(null);
        setBackground(new Color(255, 255, 200));

        botonCrearEquipo = new JButton("Crear Equipo");
        botonCrearEquipo.setFont(new Font("Arial", Font.BOLD, 22));
        botonCrearEquipo.setBounds(425, 240, 350, 60);
        add(botonCrearEquipo);
        botonCrearEquipo.addActionListener(e -> abrirVentanaCrearEquipo());

        botonBorrarEquipo = new JButton("Borrar Equipo");
        botonBorrarEquipo.setFont(new Font("Arial", Font.BOLD, 22));
        botonBorrarEquipo.setBounds(425, 320, 350, 60);
        add(botonBorrarEquipo);
        botonBorrarEquipo.addActionListener(e -> abrirVentanaBorrarEquipo());

        botonCrearParticipante = new JButton("Crear Participante");
        botonCrearParticipante.setFont(new Font("Arial", Font.BOLD, 22));
        botonCrearParticipante.setBounds(425, 400, 350, 60);
        add(botonCrearParticipante);
        botonCrearParticipante.addActionListener(e -> abrirVentanaCrearParticipante());

        botonVerEquiposParticipantes = new JButton("Ver Equipos y Participantes");
        botonVerEquiposParticipantes.setFont(new Font("Arial", Font.BOLD, 20));
        botonVerEquiposParticipantes.setBounds(425, 480, 350, 60);
        add(botonVerEquiposParticipantes);
        botonVerEquiposParticipantes.addActionListener(e -> abrirVentanaVerEquiposParticipantes());
    }

    /**
     * abre una ventana para crear un equipo nuevo
     */
    private void abrirVentanaCrearEquipo() {
        JFrame ventana = new JFrame("Crear Equipo");
        ventana.setSize(350, 180);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(null);

        JLabel lblNombre = new JLabel("Nombre del Equipo:");
        JTextField txtNombre = new JTextField();
        lblNombre.setBounds(20, 30, 130, 25);
        txtNombre.setBounds(160, 30, 150, 25);

        JButton btnCrear = new JButton("Crear Equipo");
        btnCrear.setBounds(100, 75, 120, 30);

        panel.add(lblNombre); panel.add(txtNombre); panel.add(btnCrear);
        ventana.add(panel);
        ventana.setVisible(true);

        btnCrear.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText().trim();
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(ventana, "Debe ingresar un nombre.");
                    return;
                }
                Equipos equipo = new Equipos(nombre);
                equiposCreados.add(equipo);
                JOptionPane.showMessageDialog(ventana, "Equipo creado exitosamente.");
                ventana.dispose();
            } catch (ValorNullException ex) {
                JOptionPane.showMessageDialog(ventana, "Error: " + ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(ventana, "Error inesperado: " + ex.getMessage());
            }
        });
    }

    /**
     * abre una ventana para borrar un equipo existente
     */
    private void abrirVentanaBorrarEquipo() {
        JFrame ventana = new JFrame("Borrar Equipo");
        ventana.setSize(400, 300);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(null);

        JLabel lbl = new JLabel("Seleccione un equipo para borrar:");
        lbl.setBounds(20, 20, 350, 25);
        panel.add(lbl);

        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        for (Equipos e : equiposCreados) {
            modeloLista.addElement(e.getNombre());
        }
        JList<String> listaEquipos = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaEquipos);
        scroll.setBounds(20, 60, 340, 120);
        panel.add(scroll);

        JButton btnBorrar = new JButton("Borrar");
        btnBorrar.setBounds(130, 200, 100, 30);
        panel.add(btnBorrar);

        ventana.add(panel);
        ventana.setVisible(true);

        btnBorrar.addActionListener(ev -> {
            int idx = listaEquipos.getSelectedIndex();
            if (idx < 0) {
                JOptionPane.showMessageDialog(ventana, "Seleccione un equipo.");
                return;
            }
            String nombreEquipo = modeloLista.get(idx);

            Equipos equipoABorrar = null;
            for (Equipos e : equiposCreados) {
                if (e.getNombre().equals(nombreEquipo)) {
                    equipoABorrar = e;
                    break;
                }
            }
            if (equipoABorrar != null) {
                equiposCreados.remove(equipoABorrar);

                for (Participantes p : participantesCreados) {
                    if (p.getEquipo() == equipoABorrar) {
                        p.setEquipo(null);
                    }
                }
                JOptionPane.showMessageDialog(ventana, "Equipo borrado correctamente.");
                ventana.dispose();
            }
        });
    }

    /**
     * abre una ventana para crear un nuevo participante y asignarlo a un equipo
     */
    private void abrirVentanaCrearParticipante() {
        JFrame ventana = new JFrame("Crear Participante");
        ventana.setSize(400, 350);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(null);

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();
        lblNombre.setBounds(30, 30, 100, 25);
        txtNombre.setBounds(150, 30, 200, 25);

        JLabel lblApellidos = new JLabel("Apellidos:");
        JTextField txtApellidos = new JTextField();
        lblApellidos.setBounds(30, 70, 100, 25);
        txtApellidos.setBounds(150, 70, 200, 25);

        JLabel lblCorreo = new JLabel("Correo:");
        JTextField txtCorreo = new JTextField();
        lblCorreo.setBounds(30, 110, 100, 25);
        txtCorreo.setBounds(150, 110, 200, 25);

        JLabel lblEquipo = new JLabel("Equipo:");
        lblEquipo.setBounds(30, 150, 100, 25);

        JComboBox<String> comboEquipos = new JComboBox<>();
        for (Equipos eq : equiposCreados) {
            comboEquipos.addItem(eq.getNombre());
        }
        comboEquipos.setBounds(150, 150, 200, 25);

        JButton btnCrear = new JButton("Crear");
        btnCrear.setBounds(150, 200, 100, 30);

        panel.add(lblNombre); panel.add(txtNombre);
        panel.add(lblApellidos); panel.add(txtApellidos);
        panel.add(lblCorreo); panel.add(txtCorreo);
        panel.add(lblEquipo); panel.add(comboEquipos);
        panel.add(btnCrear);

        ventana.add(panel);
        ventana.setVisible(true);

        btnCrear.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText();
                String apellidos = txtApellidos.getText();
                String correo = txtCorreo.getText();
                int idxEquipo = comboEquipos.getSelectedIndex();
                if (idxEquipo < 0) {
                    JOptionPane.showMessageDialog(ventana, "Debe seleccionar un equipo.");
                    return;
                }
                Equipos equipoSeleccionado = equiposCreados.get(idxEquipo);

                Participantes participante = new Participantes(apellidos, nombre, correo, equipoSeleccionado);
                participantesCreados.add(participante);

                JOptionPane.showMessageDialog(ventana, "Participante creado exitosamente.");
                ventana.dispose();
            } catch (ValorNullException ex) {
                JOptionPane.showMessageDialog(ventana, "Error: " + ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(ventana, "Error inesperado: " + ex.getMessage());
            }
        });
    }

    /**
     * abre una ventana con el listado de equipos y sus participantes
     */
    private void abrirVentanaVerEquiposParticipantes() {
        JFrame ventana = new JFrame("Equipos y Participantes");
        ventana.setSize(500, 500);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 15));

        StringBuilder sb = new StringBuilder();

        if (equiposCreados.isEmpty()) {
            sb.append("No hay equipos creados.\n");
        } else {
            for (Equipos eq : equiposCreados) {
                sb.append("Equipo: ").append(eq.getNombre()).append("\n");
                List<Participantes> parts = eq.getPaticipante();
                if (parts.isEmpty()) {
                    sb.append("    (Sin participantes)\n");
                } else {
                    for (Participantes p : parts) {
                        sb.append("    - ").append(p.getNombre())
                                .append(" ").append(p.getApellidos())
                                .append(" (").append(p.getCorreo()).append(")\n");
                    }
                }
                sb.append("\n");
            }
        }

        boolean haySinEquipo = false;
        for (Participantes p : participantesCreados) {
            if (p.getEquipo() == null) {
                if (!haySinEquipo) {
                    sb.append("Participantes sin equipo:\n");
                    haySinEquipo = true;
                }
                sb.append("    - ").append(p.getNombre())
                        .append(" ").append(p.getApellidos())
                        .append(" (").append(p.getCorreo()).append(")\n");
            }
        }

        area.setText(sb.toString());
        JScrollPane scroll = new JScrollPane(area);
        ventana.add(scroll);
        ventana.setVisible(true);
    }

    /**
     * retorna la lista global de participantes creados
     * @return lista de participantes creados
     */
    public static List<Participantes> getParticipantesCreados() {
        return participantesCreados;
    }

    /**
     * retorna la lista global de equipos creados
     * @return lista de equipos creados
     */
    public static List<Equipos> getEquiposCreados() {
        return equiposCreados;
    }

    /**
     * retorna el boton de crear equipo
     * @return boton crear equipo
     */
    public JButton getBotonCrearEquipo() {
        return botonCrearEquipo;
    }

    /**
     * retorna el boton de borrar equipo
     * @return boton borrar equipo
     */
    public JButton getBotonBorrarEquipo() {
        return botonBorrarEquipo;
    }

    /**
     * retorna el boton de crear participante
     * @return boton crear participante
     */
    public JButton getBotonCrearParticipante() {
        return botonCrearParticipante;
    }

    /**
     * retorna el boton de ver equipos y participantes
     * @return boton ver equipos y participantes
     */
    public JButton getBotonVerEquiposParticipantes() {
        return botonVerEquiposParticipantes;
    }
}
