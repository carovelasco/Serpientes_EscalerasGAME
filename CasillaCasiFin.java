
public class CasillaCasiFin extends CasillaMadre {
  
  public CasillaCasiFin(int pIdCasillaDestino, String pTextoImprimir) {
      super(pIdCasillaDestino, pTextoImprimir);
  }
  
  @Override
  public void realizarAccion(Jugador pJugador) {
      super.imprimirTexto(); 
      int valorDado = Dado.getDado().lanzarDado(); 
      if (valorDado == 6) {
          System.out.println("¡Felicidades! Has sacado un 6.");
          pJugador.setIdCasillaPosicion(this.getIdCasilla());
      }
      else {
          System.out.println("Qué pena, no has sacado un 6.");
      }
  }
}