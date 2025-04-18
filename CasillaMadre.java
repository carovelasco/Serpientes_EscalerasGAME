public class CasillaMadre {
  private int idCasilla;
  private String textoImprimir;

  public CasillaMadre(int pIdCasilla, String pTextoImprimir) {
    this.idCasilla = pIdCasilla;
    this.textoImprimir = pTextoImprimir;
  }

  public void realizarAccion(Jugador pJugador) {
    imprimirTexto();
    pJugador.setIdCasillaPosicion(this.idCasilla);
  }

  public int getIdCasilla() {
    return idCasilla;
  }

  public void setIdCasilla(int pIdCasilla) {
    this.idCasilla = pIdCasilla;
  }

  protected String getTextoImprimir() {
    return textoImprimir;
  }

  protected void imprimirTexto() {
    System.out.println(textoImprimir);
  }
}