import java.util.List;
import java.util.Scanner;

class Vista {
    private final Scanner entrada = new Scanner(System.in);

    public void mostrar(String mensaje) { System.out.println(mensaje); }
    public String leer(String pregunta) {
        System.out.print(pregunta);
        return entrada.nextLine().trim();
    }
    public Integer leerEntero(String pregunta) {
        try { return Integer.valueOf(leer(pregunta)); }
        catch (NumberFormatException ex) {
            mostrar("Ingresa un numero valido.");
            return null;
        }
    }
    public void mostrarCartas(List<Carta> cartas) {
        if (cartas.isEmpty()) mostrar("No hay cartas.");
        for (Carta carta : cartas) mostrar(carta.toString());
    }
}
