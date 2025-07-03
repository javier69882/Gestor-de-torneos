package Logico;

public class EliminacionDirectaDecorator extends TorneoDecorator {
    public EliminacionDirectaDecorator(ITorneo torneo) {
        super(torneo);
    }

    @Override
    public String getModalidad() {
        return "Eliminación Directa";
    }

    @Override
    public void jugarTorneo() {
        System.out.println("Jugando torneo en modalidad Eliminación Directa.");
    }
}
