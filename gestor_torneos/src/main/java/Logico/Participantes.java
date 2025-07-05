package Logico;

/**
 * representa un participante que pertenece a un equipo, hereda de persona
 */
public class Participantes extends Persona {
    private Equipos equipo;

    /**
     * crea un participante con apellidos, nombre, correo y equipo
     * agrega el participante al equipo al crearlo
     * @param apellidos apellidos del participante
     * @param nombre nombre del participante
     * @param correo correo del participante
     * @param equipo equipo al que pertenece
     * @throws ValorNullException si el equipo es null
     */
    public Participantes(String apellidos, String nombre, String correo, Equipos equipo) {
        super(nombre, apellidos, correo);
        if (equipo == null) {
            throw new ValorNullException("El equipo no puede ser null");
        }
        this.equipo = equipo;
        equipo.agregarParticipante(this);
    }

    /**
     * retorna el equipo del participante
     * @return equipo del participante
     */
    public Equipos getEquipo() {
        return equipo;
    }

    /**
     * cambia el equipo del participante
     * @param equipo nuevo equipo
     */
    public void setEquipo(Equipos equipo) {
        this.equipo = equipo;
    }
}
