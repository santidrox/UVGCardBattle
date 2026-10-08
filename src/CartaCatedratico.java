
class CartaCatedratico extends Carta {
    private final String departamento;
    private final int llamadasAtencion;
    private final int tiempoAtencion;

    public CartaCatedratico(int id, String nombre, int costo, String descripcion,
                            String departamento, int llamadasAtencion, int tiempoAtencion) {
        super(id, nombre, costo, descripcion);
        this.departamento = departamento;
        this.llamadasAtencion = llamadasAtencion;
        this.tiempoAtencion = tiempoAtencion;
    }

    @Override
    public String jugarCarta(Juego juego, Jugador jugador) {
        Jugador rival = juego.getRival();
        rival.agregarLlamadas(llamadasAtencion);
        jugador.recuperarEnergia(tiempoAtencion);
        return jugador.getNombre() + " recupera " + tiempoAtencion
                + " energia; " + rival.getNombre() + " recibe "
                + llamadasAtencion + " llamada(s) de atencion.";
    }

    @Override
    public String toString() {
        return super.toString() + " | catedratico, departamento: " + departamento
                + ", llamadas: " + llamadasAtencion
                + ", tiempo de atencion: " + tiempoAtencion;
    }
}
