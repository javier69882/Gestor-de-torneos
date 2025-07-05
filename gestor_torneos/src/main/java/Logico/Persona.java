package Logico;

/**
 * clase base para personas, contiene nombre, apellidos y correo
 * este codigo se saco de una tarea anterior
 */
public abstract class Persona{
    protected String nombre;
    protected String apellidos;
    protected String correo;

    /**
     * crea una persona con nombre, apellidos y correo
     * @param nombre nombre de la persona
     * @param apellidos apellidos de la persona
     * @param correo correo de la persona
     * @throws ValorNullException si algun parametro es null o vacio
     */
    public Persona(String nombre, String apellidos, String correo) {
        if (nombre == null || nombre.isBlank()) {
            throw new ValorNullException("El nombre no puede ser null o vacio");
        }
        if (apellidos == null || apellidos.isBlank()) {
            throw new ValorNullException("Los apellidos no pueden ser null o vacios");
        }
        if (correo == null || correo.isBlank()) {
            throw new ValorNullException("El correo no puede ser null o vacio");
        }
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
    }

    /**
     * constructor vacio
     */
    public Persona(){
    }

    /**
     * retorna el nombre de la persona
     * @return nombre de la persona
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * retorna los apellidos de la persona
     * @return apellidos de la persona
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * retorna el correo de la persona
     * @return correo de la persona
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * cambia el nombre de la persona
     * @param nombre nuevo nombre
     * @throws ValorNullException si el nombre es null o vacio
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new ValorNullException("El nombre no puede ser null o vacio");
        }
        this.nombre = nombre;
    }

    /**
     * cambia los apellidos de la persona
     * @param apellidos nuevos apellidos
     * @throws ValorNullException si los apellidos son null o vacios
     */
    public void setApellidos(String apellidos) {
        if (apellidos == null || apellidos.isBlank()) {
            throw new ValorNullException("Los apellidos no pueden ser null o vacios");
        }
        this.apellidos = apellidos;
    }

    /**
     * cambia el correo de la persona
     * @param correo nuevo correo
     * @throws ValorNullException si el correo es null o vacio
     */
    public void setCorreo(String correo) {
        if (correo == null || correo.isBlank()) {
            throw new ValorNullException("El correo no puede ser null o vacio");
        }
        this.correo = correo;
    }

    /**
     * retorna la informacion de la persona en texto
     * @return nombre, apellidos y correo
     */
    @Override
    public String toString() {
        return nombre + " " + apellidos + ", Correo: " + correo;
    }
}
