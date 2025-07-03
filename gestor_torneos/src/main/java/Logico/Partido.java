package Logico;

public class Partido {
    private Equipos equipoA;
    private Equipos equipoB;
    private Integer puntajeA = null;
    private Integer puntajeB = null;
    private boolean jugado = false;

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

    public void setPuntajeA(int puntajeA) {
        this.puntajeA = puntajeA;
    }
    public void setPuntajeB(int puntajeB) {
        this.puntajeB = puntajeB;
    }
    public void setJugado(boolean jugado) {
        this.jugado = jugado;
    }

    @Override
    public String toString() {
        return equipoA.getNombre() + " vs " + equipoB.getNombre() +
                (jugado ? (" ["+puntajeA+"-"+puntajeB+"]") : "");
    }
}
