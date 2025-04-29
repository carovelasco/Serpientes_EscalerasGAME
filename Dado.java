import java.util.Random;

public class Dado {
    private static Dado miDado;
    private Random random; 
    private int valorFijado =-1;

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
        if(this.valorFijado!=-1)
        {
            return valorFijado;
        }
        return random.nextInt(6) + 1; 
    }

    //Para Pruebas
    /*public void setValorDado(int Valor)
    {
        if(valor<1||valor>6)
        {
            throw new IllegalArgumentException("El Valor debe estar entre 1 y 6.");
        }
        this.valorFijado= Valor;
    }

    public void resetToRandom()
    {
        this.valorFijado=-1;
    }
    public static void reiniciar()
    {
        miDado=null;
    }*/

}
