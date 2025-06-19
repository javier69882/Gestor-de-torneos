package Logico;

import java.util.ArrayList;
import java.util.List;

public class Torneo{
    private String nombre;
    private List<Equipos> equipos=new ArrayList<>();

    public Torneo(String nombre, List<Equipos> equipos) {
        this.nombre = nombre;
        this.equipos = equipos;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public List<Equipos> getEquipos() {
        return equipos;
    }
    public void setEquipos(List<Equipos> equipos) {
        this.equipos = equipos;
    }


}
