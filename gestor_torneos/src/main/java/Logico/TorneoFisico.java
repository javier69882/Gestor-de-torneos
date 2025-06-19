package Logico;

public class TorneoFisico extends Torneo{
    private String deporte;

    public TorneoFisico(String nombre, String deporte) {
        super(nombre, null); // Inicializa la lista de equipos como null
        this.deporte = deporte;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String Deporte) {
        this.deporte = deporte;
    }
}
