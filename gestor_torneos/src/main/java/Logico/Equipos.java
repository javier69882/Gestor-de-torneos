package Logico;

import java.util.ArrayList;
import java.util.List;

/**
 * representa un equipo con nombre y una lista de participantes
 */
public class Equipos{

    private String nombre;
    private List<Participantes> participantes;

    /**
     * crea un equipo con nombre y lista vacia de participantes
     * @param nombre nombre del equipo
     * @throws ValorNullException si el nombre es null o vacio
     */
    public Equipos(String nombre) {
        if(nombre == null || nombre.isBlank()) {
            throw new ValorNullException("El nombre del equipo no puede ser null o vacio");
        }
        this.nombre = nombre;
        this.participantes = new ArrayList<>();
    }

    /**
     * agrega un participante al equipo
     * @param participante participante a agregar
     * @throws ValorNullException si el participante es null
     */
    public void agregarParticipante(Participantes participante) {
        if (participante == null) {
            throw new ValorNullException("No se puede agregar un participante null al equipo");
        }
        participantes.add(participante);
    }

    /**
     * retorna la cantidad de participantes en el equipo
     * @return cantidad de participantes
     */
    public int obtenerCantidadparticipantes() {
        return participantes.size();
    }

    /**
     * retorna el nombre del equipo
     * @return nombre del equipo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * cambia el nombre del equipo
     * @param nombre nuevo nombre del equipo
     * @throws ValorNullException si el nombre es null o vacio
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new ValorNullException("El nombre del departamento no puede ser null o vacio");
        }
        this.nombre = nombre;
    }

    /**
     * retorna la lista de participantes del equipo
     * @return lista de participantes
     */
    public List<Participantes> getPaticipante() {
        return participantes;
    }

    /**
     * reemplaza la lista de participantes del equipo
     * @param participantes nueva lista de participantes
     * @throws ValorNullException si la lista es null
     */
    public void setParticipantes(List<Participantes> participantes) {
        if (participantes == null) {
            throw new ValorNullException("La lista de participantes no puede ser null");
        }
        this.participantes = participantes;
    }
}
