
class CartaEvento extends Carta {
    private final TipoEvento tipo;

    public CartaEvento(int id, String nombre, int costo, String descripcion,
                       TipoEvento tipo) {
        super(id, nombre, costo, descripcion);
        this.tipo = tipo;
    }

    @Override
    public String jugarCarta(Juego juego, Jugador jugador) {
        switch (tipo) {
            case PARCIALES:
                juego.activarParciales();
                return "Semana de Parciales: los cursos cuestan 1 energia extra durante 2 turnos.";
            case FERIA:
                jugador.recuperarEnergia(3);
                return jugador.getNombre() + " recupera hasta 3 de energia en la Feria de Clubes.";
            default:
                jugador.recuperarEnergia(2);
                return jugador.getNombre() + " recupera hasta 2 de energia en la Biblioteca.";
        }
    }

    @Override
    public String toString() { return super.toString() + " | evento: " + tipo; }
}
