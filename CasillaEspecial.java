public class CasillaEspecial extends CasillaMadre {
  private int idCasillaDestino;
  private String textoImprimir;
  
  public CasillaEspecial(int pIdCasillaDestino, String pTextoImprimir) {
      super(0); // El ID de la casilla se asigna en el tablero
      this.idCasillaDestino = pIdCasillaDestino;
      this.textoImprimir = pTextoImprimir;
  }
  
  @Override
  public void realizarAccion(Jugador pJugador) {
      System.out.println(textoImprimir);
      pJugador.setIdCasillaPosicion(this.idCasillaDestino);
  }
 
}