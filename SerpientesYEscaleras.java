public class SerpientesYEscaleras {
    public static void main(String[] args) {
        Tablero tablero = Tablero.getTablero();
        tablero.cargarTableroDesdeArchivo("FicheroParaTablero.txt");
        tablero.imprimirTablero();
        tablero.jugarPartida();
    }
}
