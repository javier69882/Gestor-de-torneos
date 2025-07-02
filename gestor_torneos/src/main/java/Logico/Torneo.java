package Logico;

import java.util.ArrayList;
import java.util.List;

public class Torneo {
    private String nombre;
    private List<Equipos> equipos = new ArrayList<>();
    private Modalidad modalidad;
    private CantidadEquipos cantidadEquipos;

    public Torneo(String nombre, List<Equipos> equipos, Modalidad modalidad, CantidadEquipos cantidadEquipos) {
        this.nombre = nombre;
        this.equipos = equipos;
        this.modalidad = modalidad;
        this.cantidadEquipos = cantidadEquipos;
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public List<Equipos> getEquipos() { return equipos; }
    public void setEquipos(List<Equipos> equipos) { this.equipos = equipos; }

    public Modalidad getModalidad() { return modalidad; }
    public void setModalidad(Modalidad modalidad) { this.modalidad = modalidad; }

    public CantidadEquipos getCantidadEquipos() { return cantidadEquipos; }
    public void setCantidadEquipos(CantidadEquipos cantidadEquipos) { this.cantidadEquipos = cantidadEquipos; }
}