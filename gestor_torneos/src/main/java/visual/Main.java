package visual;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("GESTOR DE TORNEOS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1000);

        PanelPrincipal panelPrincipal = new PanelPrincipal();
        frame.add(panelPrincipal);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
