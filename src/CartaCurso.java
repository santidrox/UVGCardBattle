
class CartaCurso extends Carta {
    private final int creditos;
    private final int dificultad;

    public CartaCurso(int id, String nombre, int costo, String descripcion,
                      int creditos, int dificultad) {
        super(id, nombre, costo, descripcion);
        this.creditos = creditos;
        this.dificultad = dificultad;
    }

    @Override
    public String jugarCarta(Juego juego, Jugador jugador) {
        int puntos = Math.max(1, creditos - dificultad + 2);
        jugador.agregarCreditos(puntos);
        return jugador.getNombre() + " gana " + puntos + " credito(s).";
    }

    @Override
    public String toString() {
        return super.toString() + " | curso, creditos: " + creditos
                + ", dificultad: " + dificultad;
    }
}
