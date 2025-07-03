package Logico;

import java.util.List;

public class TorneoFisico extends TorneoBase {
    private String deporte;

    public TorneoFisico(String nombre, List<Equipos> equipos, CantidadEquipos cantidadEquipos, String deporte) {
        super(nombre, equipos, cantidadEquipos);
        this.deporte = deporte;
    }

    public String getDeporte() {
        return deporte;
    }
    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    @Override
    public String getTipoTorneo() {
        return "Físico";
    }
}
