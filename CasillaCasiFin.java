public class CasillaCasiFin extends CasillaEspecial {

    public CasillaCasiFin(int pIdCasilla, String pTextoImprimir) {
        super(pIdCasilla, 62, pTextoImprimir);
    }

    @Override
    public void realizarAccion(Jugador pJugador) {
        super.imprimirTexto(); 
        int valorDado = Dado.getDado().lanzarDado();

        if (valorDado == 6) {
            System.out.println("...");
            System.out.println("¡Felicidades! Has sacado un 6. Puedes moverte hacia la casilla");
            pJugador.setIdCasillaPosicion(super.getIdCasillaDestino()); 
        } else {
            System.out.println("...");
            System.out.println("...");
            System.out.println("Qué pena, no has sacado un 6. Permanece en esa casilla");
            pJugador.setIdCasillaPosicion(this.getIdCasilla());
        }
    }
}