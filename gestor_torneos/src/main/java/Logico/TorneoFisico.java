package Logico;

import java.util.List;

/**
 * representa un torneo fisico que incluye el tipo de deporte
 * extiende torneo base
 */
public class TorneoFisico extends TorneoBase {
    private String deporte;

    /**
     * crea un torneo fisico con nombre, equipos, cantidad de equipos y deporte
     * @param nombre nombre del torneo
     * @param equipos lista de equipos
     * @param cantidadEquipos cantidad de equipos permitidos
     * @param deporte tipo de deporte
     */
    public TorneoFisico(String nombre, List<Equipos> equipos, CantidadEquipos cantidadEquipos, String deporte) {
        super(nombre, equipos, cantidadEquipos);
        this.deporte = deporte;
    }

    /**
     * retorna el deporte del torneo
     * @return deporte del torneo
     */
    public String getDeporte() {
        return deporte;
    }

    /**
     * cambia el deporte del torneo
     * @param deporte nuevo deporte
     */
    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    /**
     * retorna el tipo de torneo (fisico)
     * @return tipo de torneo
     */
    @Override
    public String getTipoTorneo() {
        return "Fisico";
    }
}
