import java.util.List;

class Controlador {
    private final Juego juego;
    private final Vista vista;

    public Controlador(Juego juego, Vista vista) {
        this.juego = juego;
        this.vista = vista;
    }

    public void iniciar() {
        vista.mostrar("=== UVG Card Battle ===");
        while (!juego.termino()) {
            Jugador actual = juego.getActual();
            vista.mostrar("\nTurno de " + actual.getNombre() + " | energia: "
                    + actual.getEnergia() + " | creditos: " + actual.getCreditos()
                    + " | llamadas: " + actual.getLlamadas() + " | mazo: "
                    + juego.getMazoRestante() + " | parciales: "
                    + juego.getParcialesRestantes() + " turnos");
            vista.mostrar("1 Catalogo  2 Buscar  3 Ordenar por energia  4 Mi mano"
                    + "  5 Jugar  6 Tomar  7 Pasar  8 Salir");
            Integer opcion = vista.leerEntero("Opcion: ");
            if (opcion == null) continue;
            switch (opcion) {
                case 1:
                    vista.mostrarCartas(juego.getCatalogo());
                    break;
                case 2:
                    buscarCarta();
                    break;
                case 3:
                    juego.ordenarPorCosto();
                    vista.mostrarCartas(juego.getCatalogo());
                    break;
                case 4:
                    mostrarMano();
                    break;
                case 5:
                    Integer id = vista.leerEntero("ID de la carta: ");
                    if (id != null) vista.mostrar(juego.jugar(id));
                    break;
                case 6:
                    vista.mostrar(juego.tomarCarta());
                    break;
                case 7:
                    vista.mostrar(juego.pasar());
                    break;
                case 8:
                    vista.mostrar("Partida terminada por el jugador.");
                    return;
                default:
                    vista.mostrar("Opcion desconocida.");
            }
        }
        vista.mostrar("\nFin de la partida (" + juego.getAcciones() + " turnos).");
        vista.mostrar(juego.resultado());
    }

    private void buscarCarta() {
        String criterio = vista.leer("ID o nombre exacto: ");
        Carta carta;
        try { carta = juego.buscar(Integer.parseInt(criterio)); }
        catch (NumberFormatException ex) { carta = juego.buscar(criterio); }
        vista.mostrar(carta == null ? "Carta no encontrada." : carta.toString());
    }

    private void mostrarMano() {
        List<Integer> mano = juego.getActual().getMano();
        if (mano.isEmpty()) vista.mostrar("Tu mano esta vacia.");
        for (Integer id : mano) {
            Carta carta = juego.buscar(id);
            vista.mostrar(carta + " | costo actual: " + juego.costoReal(carta));
        }
    }
}
