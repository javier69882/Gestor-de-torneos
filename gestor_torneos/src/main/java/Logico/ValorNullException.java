package Logico;

/**
 * excepcion personalizada para indicar valores nulos no permitidos
 */
public class ValorNullException extends RuntimeException{

  /**
   * crea la excepcion con un mensaje personalizado
   * @param mensaje mensaje descriptivo del error
   */
  public ValorNullException(String mensaje) {
    super(mensaje);
  }

  /**
   * crea la excepcion con un mensaje y una causa
   * @param mensaje mensaje descriptivo
   * @param causa causa original del error
   */
  public ValorNullException(String mensaje, Throwable causa) {
    super(mensaje, causa);
  }

  /**
   * crea la excepcion con un mensaje por defecto
   */
  public ValorNullException() {
    super("Se ha proporcionado un valor nulo no permitido.");
  }
}
