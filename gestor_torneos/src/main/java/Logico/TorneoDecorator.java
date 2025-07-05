package Logico;

import java.util.List;

/**
 * clase abstracta para decorar torneos usando el patron decorator
 * permite agregar comportamiento y modalidad a un torneo base
 * @pattern decorator
 */
public abstract class TorneoDecorator implements ITorneo {
    protected ITorneo torneoDecorado;

    /**
     * recibe el torneo que va a ser decorado
     * @param torneo torneo a decorar
     */
    public TorneoDecorator(ITorneo torneo) {
        this.torneoDecorado = torneo;
    }

    /**
     * retorna el torneo base decorado
     * @return torneo decorado
     */
    public ITorneo getBase() {
        return torneoDecorado;
    }

    /**
     * retorna el nombre del torneo
     * @return nombre del torneo
     */
    @Override
    public String getNombre() {
        return torneoDecorado.getNombre();
    }

    /**
     * retorna la lista de equipos del torneo
     * @return lista de equipos
     */
    @Override
    public List<Equipos> getEquipos() {
        return torneoDecorado.getEquipos();
    }

    /**
     * retorna la cantidad de equipos del torneo
     * @return cantidad de equipos
     */
    @Override
    public CantidadEquipos getCantidadEquipos() {
        return torneoDecorado.getCantidadEquipos();
    }

    /**
     * retorna el tipo de torneo
     * @return tipo de torneo
     */
    @Override
    public String getTipoTorneo() {
        return torneoDecorado.getTipoTorneo();
    }
}
