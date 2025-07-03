package Logico;

public class LigaSimpleDecorator extends TorneoDecorator {
    public LigaSimpleDecorator(ITorneo torneo) {
        super(torneo);
    }

    @Override
    public String getModalidad() {
        return "Liga Simple";
    }

    @Override
    public void jugarTorneo() {
        System.out.println("Jugando torneo en modalidad Liga Simple.");
    }
}
