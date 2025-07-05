package Logico;

import java.time.LocalDateTime;

/**
 * representa un partido entre dos equipos con puntajes y fecha de juego
 */
public class Partido {
    private Equipos equipoA;
    private Equipos equipoB;
    private Integer puntajeA = null;
    private Integer puntajeB = null;
    private boolean jugado = false;
    private LocalDateTime fechaHoraJugado;

    /**
     * crea un partido entre dos equipos
     * @param equipoA equipo A
     * @param equipoB equipo B
     */
    public Partido(Equipos equipoA, Equipos equipoB) {
        this.equipoA = equipoA;
        this.equipoB = equipoB;
    }

    /**
     * retorna el equipo A
     * @return equipo A
     */
    public Equipos getEquipoA() {
        return equipoA;
    }

    /**
     * retorna el equipo B
     * @return equipo B
     */
    public Equipos getEquipoB() {
        return equipoB;
    }

    /**
     * retorna el puntaje de equipo A
     * @return puntaje de equipo A
     */
    public Integer getPuntajeA() {
        return puntajeA;
    }

    /**
     * retorna el puntaje de equipo B
     * @return puntaje de equipo B
     */
    public Integer getPuntajeB() {
        return puntajeB;
    }

    /**
     * indica si el partido ya fue jugado
     * @return true si ya fue jugado
     */
    public boolean isJugado() {
        return jugado;
    }

    /**
     * retorna la fecha y hora en que se jugo el partido
     * @return fecha y hora de juego
     */
    public LocalDateTime getFechaHoraJugado() {
        return fechaHoraJugado;
    }

    /**
     * establece el puntaje de equipo A
     * @param puntajeA puntaje de equipo A
     */
    public void setPuntajeA(int puntajeA) {
        this.puntajeA = puntajeA;
    }

    /**
     * establece el puntaje de equipo B
     * @param puntajeB puntaje de equipo B
     */
    public void setPuntajeB(int puntajeB) {
        this.puntajeB = puntajeB;
    }

    /**
     * marca el partido como jugado y registra la fecha si es la primera vez
     * @param jugado true si se jugo el partido
     */
    public void setJugado(boolean jugado) {
        if (jugado && !this.jugado) {
            this.fechaHoraJugado = LocalDateTime.now();
        }
        this.jugado = jugado;
    }

    /**
     * retorna el nombre seguro del equipo A
     * @return nombre del equipo A o "Pendiente"
     */
    public String getNombreEquipoASeguro() {
        if (equipoA != null) {
            return equipoA.getNombre();
        } else {
            return "Pendiente";
        }
    }

    /**
     * retorna el nombre seguro del equipo B
     * @return nombre del equipo B o "Pendiente"
     */
    public String getNombreEquipoBSeguro() {
        if (equipoB != null) {
            return equipoB.getNombre();
        } else {
            return "Pendiente";
        }
    }

    /**
     * retorna el marcador seguro del partido
     * @return marcador en formato "A - B" o "-"
     */
    public String getMarcadorSeguro() {
        if (puntajeA != null && puntajeB != null) {
            return puntajeA + " - " + puntajeB;
        } else {
            return "-";
        }
    }

    /**
     * retorna una representacion en texto del partido
     * @return string con datos del partido
     */
    @Override
    public String toString() {
        String nombreA, nombreB, marcador;
        if (equipoA != null) {
            nombreA = equipoA.getNombre();
        } else {
            nombreA = "Pendiente";
        }
        if (equipoB != null) {
            nombreB = equipoB.getNombre();
        } else {
            nombreB = "Pendiente";
        }
        if (puntajeA != null && puntajeB != null) {
            marcador = " [" + puntajeA + " - " + puntajeB + "]";
        } else {
            marcador = "";
        }
        String fecha = (fechaHoraJugado != null) ? " [" + fechaHoraJugado.toString() + "]" : "";
        return nombreA + " vs " + nombreB + marcador + fecha;
    }
}
