
public class Main {
    public static void main(String[] args) {
        Juego juego = new Juego();
        Vista vista = new Vista();
        new Controlador(juego, vista).iniciar();
    }
}
