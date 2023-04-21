
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CodigoParcial {
  public static void main(String[] args) {
    LocalTime horaActual = LocalTime.now();
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss");
    String horaActualFormateada = horaActual.format(formato);
    System.out.println("Hora actual: " + horaActualFormateada);
    
    LocalTime horaDetencion = LocalTime.of(22, 0, 0); // Hora de detención: 22:00:00
    String horaDetencionFormateada = horaDetencion.format(formato);
    System.out.println("Hora de detención: " + horaDetencionFormateada);
    
    while (horaActual.isBefore(horaDetencion)) {
      horaActual = LocalTime.now();
      horaActualFormateada = horaActual.format(formato);
      System.out.println("Hora actual: " + horaActualFormateada);
      
      try {
        Thread.sleep(1000); // Espera 1 segundo antes de volver a comprobar la hora
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    
    System.out.println("Bucle finalizado. Hora de detención alcanzada.");
  }
}

