import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Jugador {
    private final String nombre;
    private final List<Integer> mano = new ArrayList<>();
    private int energia = 5;
    private int creditos;
    private int llamadas;

    public Jugador(String nombre) { this.nombre = nombre; }
    public String getNombre() { return nombre; }
    public List<Integer> getMano() { return Collections.unmodifiableList(mano); }
    public int getEnergia() { return energia; }
    public int getCreditos() { return creditos; }
    public int getLlamadas() { return llamadas; }
    public int getPuntaje() { return creditos - llamadas; }
    public void recuperarEnergia(int cantidad) { energia = Math.min(10, energia + cantidad); }
    public void gastarEnergia(int cantidad) { energia -= cantidad; }
    public void agregarCreditos(int cantidad) { creditos += cantidad; }
    public void agregarLlamadas(int cantidad) { llamadas += cantidad; }
    public void recibir(int id) { mano.add(id); }
    public void descartar(int id) { mano.remove(Integer.valueOf(id)); }
}
