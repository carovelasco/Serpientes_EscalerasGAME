public class Tablero(){
    private int[][] tablero = new Casilla[8][8];
    
    
    public void jugarPartida() {
        Jugador jugador1 = new Jugador(1, 1);
        Jugador jugador2 = new Jugador(2, 1);
        
        listaJugadores.añadirJugador(jugador1);
        listaJugadores.añadirJugador(jugador2);
        
        System.out.println("¡Comienza el juego de Serpientes y Escaleras!");
        imprimirTablero();
        
        int numTurnos = 0;
        boolean hayGanador = false;
        
        while (!listaJugadores.comprobarGanador && numTurnos < 50) {
            numTurnos++;
            System.out.println("\n--- Turno " + numTurnos + " ---");
            
            Jugador jugadorActual = listaJugadores.getJugadorActual();
            System.out.println("Turno del Jugador " + jugadorActual.getIdJugador());
            
            jugadorActual.jugarTurno();
            
            hayGanador = listaJugadores.comprobarGanador();
            
            if (!hayGanador) {
                listaJugadores.pasarTurno();
            }
        }
        
        if (numTurnos >= 50 && !hayGanador) {
            System.out.println("\n¡El juego ha terminado en empate después de 50 turnos!");
        }
        
        System.out.println("\n¡Fin del juego!");
    }
}