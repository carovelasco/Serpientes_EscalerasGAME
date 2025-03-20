import java.util.Random;

public class Dado {
    private static Dado miDado;
    private Random random; 

    private Dado() {
        random = new Random(); 
    }

    public static Dado getDado() {
        if (miDado == null) {
            miDado = new Dado();
        }
        return miDado;
    }

    public int lanzarDado() {
        return random.nextInt(6) + 1; 
    }

}
