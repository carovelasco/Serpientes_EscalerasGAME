public class CasillaEspecial extends CasillaMadre {
    private int idCasillaDestino; 

    protected CasillaEspecial(int pIdCasilla, int pIdCasillaDestino, String pTextoImprimir) {
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
    
    @Override
    public void imprimirId() {
        System.out.print( "*" + getIdCasilla()+"\t" );  
    }
}