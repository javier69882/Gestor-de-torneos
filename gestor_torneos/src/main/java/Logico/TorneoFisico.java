package Logico;

import java.util.List;

public class TorneoFisico extends Torneo {
    private String deporte;

    public TorneoFisico(String nombre, List<Equipos> equipos, Modalidad modalidad, CantidadEquipos cantidadEquipos, String deporte) {
        super(nombre, equipos, modalidad, cantidadEquipos);
        this.deporte = deporte;
    }

    public String getDeporte() { return deporte; }
    public void setDeporte(String deporte) { this.deporte = deporte; }
}