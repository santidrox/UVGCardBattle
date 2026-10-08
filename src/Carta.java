
abstract class Carta {
    private final int id;
    private final String nombre;
    private final int costo;
    private final String descripcion;

    protected Carta(int id, String nombre, int costo, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.costo = costo;
        this.descripcion = descripcion;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getCosto() { return costo; }
    public String getDescripcion() { return descripcion; }
    public abstract String jugarCarta(Juego juego, Jugador jugador);

    @Override
    public String toString() {
        return "#" + id + " " + nombre + " | energia: " + costo
                + " | " + descripcion;
    }

    @Override
    public boolean equals(Object otro) {
        if (this == otro) return true;
        if (!(otro instanceof Carta)) return false;
        return id == ((Carta) otro).id;
    }

    @Override
    public int hashCode() { return Integer.hashCode(id); }
}
