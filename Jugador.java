public class Jugador {
    private int idJugador;
    private int idCasillaPosicion;

    public Jugador(int pIdJugador) {
        this.idJugador = pIdJugador;
        this.idCasillaPosicion = 1;
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

    public void moverPorDado(int pValorDado) {
        int nuevaPosicion = idCasillaPosicion + pValorDado;

        if (nuevaPosicion > 64) {
            int exceso = nuevaPosicion - 64;
            nuevaPosicion = 64 - exceso;
        }

        setIdCasillaPosicion(nuevaPosicion);
        System.out.println("Jugador " + idJugador + " ha movido hasta la casilla " + idCasillaPosicion);

        CasillaMadre casillaAVerificar = Tablero.getTablero().buscarCasilla(idCasillaPosicion);
        casillaAVerificar.realizarAccion(this);
    }

    public void jugarTurno() {
        int valorDado = Dado.getDado().lanzarDado();
        System.out.println("Jugador " + idJugador + " ha sacado un " + valorDado);
        this.moverPorDado(valorDado);
    }
}