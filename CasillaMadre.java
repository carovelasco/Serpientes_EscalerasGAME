public class CasillaMadre{
  private int idCasilla;
  private String textoImprimir = "Has caído en una casilla normal.";
  
public CasillaMadre(int pIdCasilla){
  this.idCasilla = pIdCasilla;
}

public void realizarAccion(Jugador pJugador){
  System.out.println(textoImprimir);
}

public int getIdCasilla() {
  return idCasilla;
}

public void setIdCasilla(int pIdCasilla) {
  this.idCasilla = pIdCasilla;
}
}
