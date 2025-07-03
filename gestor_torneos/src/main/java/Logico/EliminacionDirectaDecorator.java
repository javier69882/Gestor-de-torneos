package Logico;

import java.util.ArrayList;
import java.util.List;

public class EliminacionDirectaDecorator extends TorneoDecorator {
    public EliminacionDirectaDecorator(ITorneo torneo) {
        super(torneo);
    }

    @Override
    public String getModalidad() {
        return "ELIMINACION_DIRECTA";
    }

    @Override
    public void jugarTorneo() {
        System.out.println("Jugando torneo en modalidad Eliminación Directa.");
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
