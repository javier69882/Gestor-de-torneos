package visual;

import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {

    public PanelPrincipal() {

        setBackground(Color.BLACK); // Fondo negro
        setLayout(null); // Sin layout, permite usar setBounds

        // INSTANCIAS DE CLASE
        Usuario usuario = new Usuario();
        Pizarra pizarra = new Pizarra();

        // Establecer posiciones y tamaños de los componentes
        usuario.setBounds(0, 0, 200, 1000); // Posición y tamaño del panel Usuario
        pizarra.setBounds(200, 0, 1000, 1000); // Posición y tamaño del panel Pizarra

        // Añadir componentes al panel principal
        add(usuario);
        add(pizarra);
    }
}



