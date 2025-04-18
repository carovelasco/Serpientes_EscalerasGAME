import java.util.ArrayList;

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
    
    public void añadirJugador(Jugador pJugador) {
        this.lista.add(pJugador);
    }

    public Jugador pasarTurno(){
        Jugador unJugador=this.lista.get(0);
        this.lista.remove(unJugador);
        this.lista.add(unJugador);      
        return unJugador;
    }

    public boolean comprobarGanador() {
        Jugador jugadorActual = this.lista.get(1);
        
        if (jugadorActual.getIdCasillaPosicion() == 64) {
            return true;
        }
        
        return false;
    }
}

