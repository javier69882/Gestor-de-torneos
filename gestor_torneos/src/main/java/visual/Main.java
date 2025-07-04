package visual;

import Logico.*;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        SingletonDatosPrueba singleton = SingletonDatosPrueba.getInstancia();

        JFrame frame = new JFrame("GESTOR DE TORNEOS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1000);

        PanelPrincipal panelPrincipal = new PanelPrincipal();


        PanelPrincipal.depositoTorneos = singleton.getDepositoTorneos();

        frame.add(panelPrincipal);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
