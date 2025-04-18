
public class CasillaEspecial extends CasillaMadre {
  private int idCasillaDestino;
  
  public CasillaEspecial(int pIdCasillaDestino, String pTextoImprimir) {
      super(0, pTextoImprimir); 
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