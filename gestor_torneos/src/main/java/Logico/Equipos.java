package Logico;

import java.util.ArrayList;
import java.util.List;


public class Equipos{

    private String nombre;
    private List<Participantes> participantes;


    public Equipos(String nombre) {
        if(nombre == null || nombre.isBlank()) {
            throw new ValorNullException("El nombre del equipo no puede ser null o vacío.");
        }
        this.nombre = nombre;
        this.participantes = new ArrayList<>();
    }



    public void agregarParticipante(Participantes participante) {
        if (participante == null) {
            throw new ValorNullException("No se puede agregar un participante null al equipo.");
        }
        participantes.add(participante);
    }


    public int obtenerCantidadparticipantes() {
        return participantes.size();
    }


    public String getNombre() {
        return nombre;
    }



    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new ValorNullException("El nombre del departamento no puede ser null o vacío.");
        }
        this.nombre = nombre;
    }




    public List<Participantes> getPaticipante() {
        return participantes;
    }



    public void setParticipantes(List<Participantes> participantes) {
        if (participantes == null) {
            throw new ValorNullException("La lista de participantes no puede ser null.");
        }
        this.participantes = participantes;
    }
}
