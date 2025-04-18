public class CasillaEspecial extends CasillaMadre {
    protected int idCasillaDestino; 

    public CasillaEspecial(int pIdCasilla, int pIdCasillaDestino, String pTextoImprimir) {
        super(pIdCasilla, pTextoImprimir); 
        this.idCasillaDestino = pIdCasillaDestino;
    }

    @Override
    public void realizarAccion(Jugador pJugador) {
        super.imprimirTexto();
        pJugador.setIdCasillaPosicion(this.idCasillaDestino);
    }

    protected int getIdCasillaDestino() {
        return idCasillaDestino;
    }
}