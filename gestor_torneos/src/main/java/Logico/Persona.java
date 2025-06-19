package Logico;

public abstract class Persona{
    protected String nombre;
    protected String apellidos;
    protected String correo;




    public Persona(String nombre, String apellidos, String correo) {
        if (nombre == null || nombre.isBlank()) {
            throw new ValorNullException("El nombre no puede ser null o vacío.");
        }
        if (apellidos == null || apellidos.isBlank()) {
            throw new ValorNullException("Los apellidos no pueden ser null o vacíos.");
        }
        if (correo == null || correo.isBlank()) {
            throw new ValorNullException("El correo no puede ser null o vacío.");
        }
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
    }

    public Persona(){
    }





    public String getNombre() {
        return nombre;
    }




    public String getApellidos() {
        return apellidos;
    }




    public String getCorreo() {
        return correo;
    }




    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new ValorNullException("El nombre no puede ser null o vacío.");
        }
        this.nombre = nombre;
    }




    public void setApellidos(String apellidos) {
        if (apellidos == null || apellidos.isBlank()) {
            throw new ValorNullException("Los apellidos no pueden ser null o vacíos.");
        }
        this.apellidos = apellidos;
    }



    public void setCorreo(String correo) {
        if (correo == null || correo.isBlank()) {
            throw new ValorNullException("El correo no puede ser null o vacío.");
        }
        this.correo = correo;
    }




    @Override
    public String toString() {
        return nombre + " " + apellidos + ", Correo: " + correo;
    }
}
