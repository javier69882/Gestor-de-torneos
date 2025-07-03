package Logico;

import java.util.List;

public abstract class TorneoDecorator implements ITorneo {
    protected ITorneo torneoDecorado;

    public TorneoDecorator(ITorneo torneo) {
        this.torneoDecorado = torneo;
    }

    public ITorneo getBase() {
        return torneoDecorado;
    }

    @Override
    public String getNombre() {
        return torneoDecorado.getNombre();
    }

    @Override
    public List<Equipos> getEquipos() {
        return torneoDecorado.getEquipos();
    }

    @Override
    public CantidadEquipos getCantidadEquipos() {
        return torneoDecorado.getCantidadEquipos();
    }

    @Override
    public String getTipoTorneo() {
        return torneoDecorado.getTipoTorneo();
    }
}
