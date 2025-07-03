package Logico;

import java.util.ArrayList;
import java.util.List;

public class DobleEliminacionDecorator extends TorneoDecorator {
    public DobleEliminacionDecorator(ITorneo torneo) {
        super(torneo);
    }

    @Override
    public String getModalidad() {
        return "DOBLE_ELIMINACION";
    }

    @Override
    public void jugarTorneo() {
        System.out.println("Jugando torneo en modalidad Doble Eliminación.");
    }

    @Override
    public List<PosicionLiga> getTablaPosiciones() {

        return new ArrayList<>();
    }


    @Override
    public List<Partido> getPartidos() {
        return new ArrayList<>();
    }

    @Override
    public void registrarResultado(Partido partido, int puntajeA, int puntajeB) {

    }
}
