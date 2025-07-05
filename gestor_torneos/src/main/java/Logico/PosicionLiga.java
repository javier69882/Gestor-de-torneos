package Logico;

/**
 * representa la posicion de un equipo en una liga con puntos, partidos jugados y goles
 */
public class PosicionLiga {
    public Equipos equipo;
    public int puntos = 0;
    public int jugados = 0;
    public int golesFavor = 0;
    public int golesContra = 0;

    /**
     * crea la posicion de un equipo en la tabla
     * @param equipo equipo al que corresponde la posicion
     */
    public PosicionLiga(Equipos equipo) {
        this.equipo = equipo;
    }
}
