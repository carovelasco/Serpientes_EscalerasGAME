public class CasillaEspecial{
  private int idCasillaDestino;
  private String textoImprimir;
  
public CasillaMadre(int pIdCasillaDestino, String pTextoImprimir){
  this.idCasillaDestino = pIdCasillaDestino;
  this.textoImprimir = pTextoImprimir;
}

public void realizarAccion(Jugador pJugador){
  System.out.println(textoImprimir);
  pJugador.idCasillaPosicion = this.idCasillaDestino;
}
  
