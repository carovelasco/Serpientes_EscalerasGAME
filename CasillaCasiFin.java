public class CasillaCasiFin extends CasillaMadre {
    private int idCasillaDestino;

    public CasillaCasiFin(int pIdCasilla, String pTextoImprimir) {
        super(pIdCasilla, pTextoImprimir);
        this.idCasillaDestino = 62; 
    }

    @Override
    public void realizarAccion(Jugador pJugador) {
        super.imprimirTexto();
        int valorDado = Dado.getDado().lanzarDado();

        if (valorDado == 6) {
            System.out.println("¡Felicidades! Has sacado un 6.");
            pJugador.setIdCasillaPosicion(idCasillaDestino);
        } else {
            System.out.println("...");
            System.out.println("...");
            System.out.println("Qué pena, no has sacado un 6. Permanece en esa casilla");
            pJugador.setIdCasillaPosicion(this.getIdCasilla());
        }
    }
}