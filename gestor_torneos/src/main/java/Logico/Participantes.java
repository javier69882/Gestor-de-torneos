package Logico;

public class Participantes extends Persona {
    private Equipos equipo;



    public Participantes(String apellidos, String nombre, String correo, Equipos equipo) {
        super(nombre, apellidos, correo);
        if (equipo == null) {
            throw new ValorNullException("El equipo no puede ser null.");
        }
        this.equipo = equipo;
        equipo.agregarParticipante(this);
    }
    //getters y setters
    public Equipos getEquipo() {
        return equipo;
    }
    public void setEquipo(Equipos equipo) {

        this.equipo = equipo;
    }
}
