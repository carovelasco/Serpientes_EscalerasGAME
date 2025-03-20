public class ListaJugadores{
    private ArrayList<Jugador>lista;
    private static ListaJugadores miListaJugadores;

    private ListaJugadores(){
        this.lista = new ArrayList<Jugador>();
    }

    public static ListaJugadores getListaJugadores(){
        if (miListaJugadores == null){
            miListaJugadores = new ListaJugadores();
        }
        return miListaJugadores;
    }
    private Iterator<Jugador> getIterador(){
        return this.lista.iterator();
    }
    public void añadirJugador(Jugador pJugador){
        this.lista.add(pJugador);
    }


    //El primero siempre es el turno que toca 
    public Jugador pasarTurno(){
        Iterator<Jugador> itr = getIterador();
        Jugador unJugador = null;

        while(itr.hasNext()){
            unJugador = itr.next();
        }

        if (unJugador != null) {
            this.lista.remove(unJugador);
            this.lista.add(unJugador);  
        }
        return unJugador;
    }

    public boolean comprobarGanador(){
        Iterator<Jugador> itr = getIterador();
        Jugador unJugador = null;
        int numturnos=0;

        while(itr.hasNext()&&numturnos<=50){
            unJugador = itr.next();
            if (unJugador.getPosicion() = 64){
                return true;
            }else{
                numturnos++;
                //falta mas
            }
    
    }
}