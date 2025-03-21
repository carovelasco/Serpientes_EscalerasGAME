public class Jugador {
    private int idJugador;
    private int idCasillaPosicion;

    public Jugador(int pIdJugador, int pIdCasillaPosicion) {
        this.idJugador = pIdJugador;
        this.idCasillaPosicion = pIdCasillaPosicion;
    }
    
    public int getIdJugador() {
        return idJugador;
    }
    
    public int getIdCasillaPosicion() {
        return idCasillaPosicion;
    }
    
    public void setIdCasillaPosicion(int pIdCasillaPosicion) {
        this.idCasillaPosicion = pIdCasillaPosicion;
    }
    
    private void moverPorDado(int pValorDado) {
        int nuevaPosicion = idCasillaPosicion + pValorDado;
        
        if (nuevaPosicion > 64) {
            int exceso = nuevaPosicion - 64;
            nuevaPosicion = 64 - exceso;
        }
        
        setIdCasillaPosicion(nuevaPosicion);
        
        Tablero.getTablero().buscarCasilla(this).realizarAccion(this);
    }
    
    public void jugarTurno() {
        int valorDado = Dado.getDado().lanzarDado();
        System.out.println("Jugador " + idJugador + " ha sacado un " + valorDado);
        this.moverPorDado(valorDado);
        System.out.println("Jugador " + idJugador + " ha movido hasta la casilla " + idCasillaPosicion);
    }
}