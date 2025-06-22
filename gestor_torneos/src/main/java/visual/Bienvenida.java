package visual;

import javax.swing.*;
import java.awt.*;

public class Bienvenida extends JPanel {
    public Bienvenida() {
        setBackground(new Color(60, 150, 200));
        setLayout(null);
        JLabel label = new JLabel("Bienvenido al Gestor de Torneos");
        label.setFont(new Font("Arial", Font.BOLD, 28));
        label.setForeground(Color.WHITE);
        label.setBounds(250, 400, 600, 50);
        add(label);
    }
}
