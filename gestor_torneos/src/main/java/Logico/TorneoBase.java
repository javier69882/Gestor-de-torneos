package Logico;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * implementacion base de torneo, no tiene modalidad ni comportamiento especial
 * sirve como base para aplicar el patron decorator
 * @pattern decorator
 */
public class TorneoBase implements ITorneo {
    protected String nombre;
    protected List<Equipos> equipos;
    protected CantidadEquipos cantidadEquipos;
    private LocalDateTime fechaCreacion;

    /**
     * crea un torneo base con nombre, equipos y cantidad de equipos
     * @param nombre nombre del torneo
     * @param equipos lista de equipos
     * @param cantidadEquipos cantidad de equipos permitidos
     */
    public TorneoBase(String nombre, List<Equipos> equipos, CantidadEquipos cantidadEquipos) {
        this.nombre = nombre;
        this.equipos = equipos;
        this.cantidadEquipos = cantidadEquipos;
        this.fechaCreacion = LocalDateTime.now();
    }

    /**
     * retorna el nombre del torneo
     * @return nombre del torneo
     */
    @Override
    public String getNombre() {
        return nombre;
    }

    /**
     * retorna la lista de equipos
     * @return lista de equipos
     */
    @Override
    public List<Equipos> getEquipos() {
        return equipos;
    }

    /**
     * retorna la cantidad de equipos permitidos
     * @return cantidad de equipos
     */

    public LocalDateTime getFechaCreacionTorneo() {
        return fechaCreacion;
    }

    @Override
    public CantidadEquipos getCantidadEquipos() {
        return cantidadEquipos;
    }

    /**
     * retorna el tipo de torneo (base)
     * @return tipo de torneo
     */
    @Override
    public String getTipoTorneo() {
        return "Base";
    }

    /**
     * retorna la modalidad del torneo (sin modalidad)
     * @return nombre de la modalidad
     */
    @Override
    public String getModalidad() {
        return "Sin modalidad";
    }

    /**
     * metodo vacio para iniciar el torneo base
     */
    @Override
    public void jugarTorneo() {
        System.out.println("Torneo base (sin modalidad) iniciado.");
    }

    /**
     * retorna la lista de partidos (vacia en torneo base)
     * @return lista vacia de partidos
     */
    @Override
    public List<Partido> getPartidos() {
        return new ArrayList<>();
    }

    /**
     * metodo vacio para registrar resultado en torneo base
     * @param partido partido a registrar
     * @param puntajeA puntaje equipo A
     * @param puntajeB puntaje equipo B
     */
    @Override
    public void registrarResultado(Partido partido, int puntajeA, int puntajeB) {

    }

    /**
     * retorna la tabla de posiciones (vacia en torneo base)
     * @return lista vacia de posiciones
     */
    @Override
    public List<PosicionLiga> getTablaPosiciones() {
        return new ArrayList<>();
    }
    /**
     * retorna la fecha de creacion del torneo
     * @return fecha de creacion
     */
    @Override
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
}
