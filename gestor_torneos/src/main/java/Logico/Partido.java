package Logico;

import java.time.LocalDateTime;

public class Partido {
    private Equipos equipoA;
    private Equipos equipoB;
    private Integer puntajeA = null;
    private Integer puntajeB = null;
    private boolean jugado = false;
    private LocalDateTime fechaHoraJugado; // registra hora

    public Partido(Equipos equipoA, Equipos equipoB) {
        this.equipoA = equipoA;
        this.equipoB = equipoB;
    }

    public Equipos getEquipoA() {
        return equipoA;
    }
    public Equipos getEquipoB() {
        return equipoB;
    }
    public Integer getPuntajeA() {
        return puntajeA;
    }
    public Integer getPuntajeB() {
        return puntajeB;
    }
    public boolean isJugado() {
        return jugado;
    }
    public LocalDateTime getFechaHoraJugado() {
        return fechaHoraJugado;
    }

    public void setPuntajeA(int puntajeA) {
        this.puntajeA = puntajeA;
    }
    public void setPuntajeB(int puntajeB) {
        this.puntajeB = puntajeB;
    }

    public void setJugado(boolean jugado) {
        //registra la fecha/hora la primera vez que se marca como jugado
        if (jugado && !this.jugado) {
            this.fechaHoraJugado = LocalDateTime.now();
        }
        this.jugado = jugado;
    }

    public String getNombreEquipoASeguro() {
        if (equipoA != null) {
            return equipoA.getNombre();
        } else {
            return "Pendiente";
        }
    }

    public String getNombreEquipoBSeguro() {
        if (equipoB != null) {
            return equipoB.getNombre();
        } else {
            return "Pendiente";
        }
    }

    public String getMarcadorSeguro() {
        if (puntajeA != null && puntajeB != null) {
            return puntajeA + " - " + puntajeB;
        } else {
            return "-";
        }
    }

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
