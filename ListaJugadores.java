import java.util.ArrayList;
import java.util.Iterator;

public class ListaJugadores {
    private ArrayList<Jugador> lista;
    private static ListaJugadores miListaJugadores;
    
    private ListaJugadores() {
        this.lista = new ArrayList<Jugador>();
    }

    public static ListaJugadores getListaJugadores() {
        if (miListaJugadores == null) {
            miListaJugadores = new ListaJugadores();
        }
        return miListaJugadores;
    }
    
    private Iterator<Jugador> getIterador() {
        return this.lista.iterator();
    }
    
    public void añadirJugador(Jugador pJugador) {
        this.lista.add(pJugador);
    }

    public Jugador pasarTurno(){
        Jugador unJugador=this.lista.get(0);
        this.lista.remove(unJugador);
        this.lista.add(unJugador);      
        return unJugador;
    }

    public boolean comprobarGanador(){
        Iterator<Jugador> itr = getIterador();
        Jugador unJugador = null;
        int numturnos=0;

        while(itr.hasNext()&&numturnos<=50)
        {
            unJugador = itr.next();
            if (unJugador.getIdCasillaPosicion() == 64)
            {
                return true;
            }
            else{
                numturnos++;
            }
      }
        return false;
}
}
