package Logico;

public class ValorNullException extends RuntimeException{

  public ValorNullException(String mensaje) {
    super(mensaje);
  }

  public ValorNullException(String mensaje, Throwable causa) {
    super(mensaje, causa);
  }

  public ValorNullException() {
    super("Se ha proporcionado un valor nulo no permitido.");
  }
}
