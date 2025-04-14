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
      System.out.println(textoImprimir);
      int valorDado = Dado.getDado().lanzarDado();  //hay que sacar un 6, se necesita pValorDado
      if (valorDado == 6)
      {
          System.out.println("¡Felicidades! Has sacado un 6.");
          pJugador.setIdCasillaPosicion(this.idCasillaDestino);
      }
      else
      {
          System.out.println("Qué pena, no has sacado un 6.")
  }
  public int getIdCasillaDestino() {
    return idCasillaDestino;
  }
}
