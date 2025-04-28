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

  protected void imprimirTexto() {
    System.out.println(this.textoImprimir);
  }

  public void imprimirId() {
     System.out.print(this.idCasilla);
  }
}
