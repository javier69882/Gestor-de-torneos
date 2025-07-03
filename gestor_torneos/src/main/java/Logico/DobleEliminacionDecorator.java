package Logico;

public class DobleEliminacionDecorator extends TorneoDecorator {
    public DobleEliminacionDecorator(ITorneo torneo) {
        super(torneo);
    }

    @Override
    public String getModalidad() {
        return "Doble Eliminación";
    }

    @Override
    public void jugarTorneo() {
        System.out.println("Jugando torneo en modalidad Doble Eliminación.");
    }
}
