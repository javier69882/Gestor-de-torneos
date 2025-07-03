package Logico;

import java.util.List;
import java.util.ArrayList;

public class TorneoBase implements ITorneo {
    protected String nombre;
    protected List<Equipos> equipos;
    protected CantidadEquipos cantidadEquipos;

    public TorneoBase(String nombre, List<Equipos> equipos, CantidadEquipos cantidadEquipos) {
        this.nombre = nombre;
        this.equipos = equipos;
        this.cantidadEquipos = cantidadEquipos;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public List<Equipos> getEquipos() {
        return equipos;
    }

    @Override
    public CantidadEquipos getCantidadEquipos() {
        return cantidadEquipos;
    }

    @Override
    public String getTipoTorneo() {
        return "Base";
    }

    @Override
    public String getModalidad() {
        return "Sin modalidad";
    }

    @Override
    public void jugarTorneo() {
        System.out.println("Torneo base (sin modalidad) iniciado.");
    }


    @Override
    public List<Partido> getPartidos() {
        return new ArrayList<>();
    }

    @Override
    public void registrarResultado(Partido partido, int puntajeA, int puntajeB) {

    }

    @Override
    public List<PosicionLiga> getTablaPosiciones() {
        return new ArrayList<>();
    }
}
