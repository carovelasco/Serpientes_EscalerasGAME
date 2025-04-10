public class CasillaEspecial extends CasillaEspecial {//completar
    private int idCasillaDestino;
    private String textoImprimir;
    
}
   
public class CasillaCasiFin extends CasillaMadre
{
  private int idCasillaDestino;
  private String textoImprimir;
  
  public CasillaEspecial(int pIdCasillaDestino, String pTextoImprimir) {
      super(0); // El ID de la casilla se asigna en el tablero
      this.idCasillaDestino = pIdCasillaDestino;
      this.textoImprimir = pTextoImprimir;
  }
  
  @Override
  public void realizarAccion(Jugador pJugador) {

        //hay que haber sacado un 6, se necesita pValorDado
      System.out.println(textoImprimir);
      pJugador.setIdCasillaPosicion(this.idCasillaDestino);
  }
  public int getIdCasillaDestino() {
    return idCasillaDestino;
  }
}
