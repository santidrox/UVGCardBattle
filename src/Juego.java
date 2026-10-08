import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

class Juego {
    private final List<Carta> catalogo = new ArrayList<>();
    private final List<Integer> mazo = new ArrayList<>();
    private final Jugador[] jugadores = { new Jugador("Jugador 1"), new Jugador("Jugador 2") };
    private final Random azar = new Random();
    private int turno;
    private int parcialesRestantes;
    private int acciones;
    private static final int LIMITE_ACCIONES = 12;

    public Juego() {
        cargarCatalogo();
        for (Carta carta : catalogo) mazo.add(carta.getId());
        Collections.shuffle(mazo, azar);
        for (int i = 0; i < 3; i++) {
            robar(jugadores[0]);
            robar(jugadores[1]);
        }
    }

    private void cargarCatalogo() {
        catalogo.add(new CartaCatedratico(1, "Profe de Progra", 2,
                "Revisa los proyectos", "Computacion", 1, 1));
        catalogo.add(new CartaCatedratico(2, "Profe de Mate", 3,
                "Exige demostraciones", "Matematica", 2, 1));
        catalogo.add(new CartaCatedratico(3, "Profe de Fisica", 2,
                "Corrige practicas", "Ciencias", 1, 2));
        catalogo.add(new CartaCatedratico(4, "Profe de Diseno", 4,
                "Evalua prototipos", "Ingenieria", 2, 2));
        catalogo.add(new CartaCurso(5, "POO", 3,
                "Aprende herencia y objetos", 4, 3));
        catalogo.add(new CartaCurso(6, "Calculo", 2,
                "Resuelve integrales", 5, 4));
        catalogo.add(new CartaCurso(7, "Bases de Datos", 3,
                "Modela informacion", 4, 2));
        catalogo.add(new CartaCurso(8, "Algoritmos", 4,
                "Optimiza soluciones", 5, 3));
        catalogo.add(new CartaEvento(9, "Semana de Parciales", 2,
                "Aumenta el costo de cursos", TipoEvento.PARCIALES));
        catalogo.add(new CartaEvento(10, "Feria de Clubes", 1,
                "Recupera energia", TipoEvento.FERIA));
        catalogo.add(new CartaEvento(11, "Noche de Biblioteca", 1,
                "Recupera energia", TipoEvento.BIBLIOTECA));
        catalogo.add(new CartaCurso(12, "Redes", 2,
                "Conecta el campus", 4, 3));
    }

    public List<Carta> getCatalogo() { return Collections.unmodifiableList(catalogo); }
    public Jugador getActual() { return jugadores[turno]; }
    public Jugador getRival() { return jugadores[1 - turno]; }
    public int getMazoRestante() { return mazo.size(); }
    public int getParcialesRestantes() { return parcialesRestantes; }
    public int getAcciones() { return acciones; }
    public boolean termino() { return acciones >= LIMITE_ACCIONES
            || (mazo.isEmpty() && jugadores[0].getMano().isEmpty()
                && jugadores[1].getMano().isEmpty()); }

    // Sobrecarga: el mismo nombre permite buscar con ID o con nombre.
    public Carta buscar(int id) {
        for (Carta carta : catalogo) if (carta.getId() == id) return carta;
        return null;
    }

    public Carta buscar(String nombre) {
        for (Carta carta : catalogo)
            if (carta.getNombre().equalsIgnoreCase(nombre.trim())) return carta;
        return null;
    }

    public void ordenarPorCosto() {
        catalogo.sort(Comparator.comparingInt(Carta::getCosto)
                .thenComparingInt(Carta::getId));
    }

    public int costoReal(Carta carta) {
        return carta.getCosto() + ((carta instanceof CartaCurso && parcialesRestantes > 0) ? 1 : 0);
    }

    private boolean robar(Jugador jugador) {
        if (mazo.isEmpty()) return false;
        jugador.recibir(mazo.remove(mazo.size() - 1));
        return true;
    }

    public String tomarCarta() {
        if (!robar(getActual())) return "El mazo esta vacio; elige otra accion.";
        String mensaje = getActual().getNombre() + " toma una carta.";
        avanzarTurno();
        return mensaje;
    }

    public String jugar(int id) {
        Jugador actual = getActual();
        if (!actual.getMano().contains(id)) return "No tienes esa carta en la mano.";
        Carta carta = buscar(id);
        int costo = costoReal(carta);
        if (actual.getEnergia() < costo) return "Energia insuficiente. Costo: " + costo + ".";
        actual.gastarEnergia(costo);
        String efecto = carta.jugarCarta(this, actual);
        actual.descartar(id);
        avanzarTurno();
        return carta.getNombre() + " jugada. " + efecto;
    }

    public String pasar() {
        String mensaje = getActual().getNombre() + " pasa el turno.";
        avanzarTurno();
        return mensaje;
    }

    public void activarParciales() { parcialesRestantes = 3; }

    private void avanzarTurno() {
        acciones++;
        if (parcialesRestantes > 0) parcialesRestantes--;
        turno = 1 - turno;
        if (!termino()) getActual().recuperarEnergia(2);
    }

    public String resultado() {
        int uno = jugadores[0].getPuntaje();
        int dos = jugadores[1].getPuntaje();
        if (uno == dos) return "Empate: " + uno + " puntos por jugador.";
        return "Gana " + (uno > dos ? jugadores[0].getNombre() : jugadores[1].getNombre())
                + " con " + Math.max(uno, dos) + " puntos.";
    }
}
