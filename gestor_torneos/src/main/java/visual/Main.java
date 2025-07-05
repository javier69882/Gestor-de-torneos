package visual;

import Logico.*;
import javax.swing.*;
import java.awt.*;

/**
 * clase principal de la aplicacion, inicia la ventana y el panel principal
 * usa singleton para cargar datos de prueba
 * @pattern singleton
 */
public class Main {
    /**
     * metodo principal, inicia la interfaz grafica y carga los datos usando singleton
     * @param args argumentos de linea de comandos
     */
    public static void main(String[] args) {

        // se usa singleton para obtener los datos iniciales de prueba
        SingletonDatosPrueba singleton = SingletonDatosPrueba.getInstancia();

        JFrame frame = new JFrame("GESTOR DE TORNEOS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1000);

        PanelPrincipal panelPrincipal = new PanelPrincipal();

        // depositoTorneos se inicializa usando singleton
        PanelPrincipal.depositoTorneos = singleton.getDepositoTorneos();

        frame.add(panelPrincipal);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
