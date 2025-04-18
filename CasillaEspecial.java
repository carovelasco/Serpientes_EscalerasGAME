public class CasillaEspecial extends CasillaMadre {
    protected int idCasillaDestino; // Cambiado a protected para que las subclases accedan

    public CasillaEspecial(int pIdCasillaDestino, String pTextoImprimir) {
        super(0, pTextoImprimir); // ID temporal que será actualizado por setCasillaEspecial
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