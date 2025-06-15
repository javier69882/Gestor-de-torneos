package visual;

import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {

    public PanelPrincipal() {

        setBackground(Color.BLACK); // Fondo negro
        setLayout(new GridBagLayout()); // Centra los componentes sin expandirlos

        Pizarra pizarra = new Pizarra();
        add(pizarra);
    }
}
