import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class Tablero {
    //atributos
    private CasillaMadre[][] tablero;
    private static Tablero miTablero = null;

    //constructor
    private Tablero() {
        this.tablero = new CasillaMadre[8][8];
    }

    //unica instancia
    public static Tablero getTablero() {
        if (miTablero == null) {
            miTablero = new Tablero();
        }
        return miTablero;
    }
    
    //metodos

    //para el caso en que el archivo ingresado no sea correcto o no exista se crea uno por default 
    public void crearTableroBasico() {
        this.asignarIdACasillasMadre();
        
        setCasillaEspecial(4, "ESCALERA", 14);
        setCasillaEspecial(9, "SERPIENTE", 6);
        setCasillaEspecial(20, "ESCALERA", 38);
        setCasillaEspecial(28, "SERPIENTE", 15);
        setCasillaEspecial(40, "ALINICIO", 1);
        setCasillaEspecial(37, "ESCALERA", 55);
        setCasillaEspecial(54, "CASIFIN", 62);
        setCasillaEspecial(71, "SERPIENTE", 21);
        
        System.out.println("Tablero básico automatico creado");
    } 

    //metodo principal para jugar partida
    public void jugarPartida() {
        Jugador Jugador1=new Jugador(1);
        Jugador Jugador2=new Jugador(2);

        ListaJugadores.getListaJugadores().añadirJugador(Jugador1);
        ListaJugadores.getListaJugadores().añadirJugador(Jugador2);

        int numTurnos = 1;
        boolean comprobarGanador = false;
        Jugador unJugador = null;

        while (!comprobarGanador && numTurnos < 101) {
            System.out.println(" ");
            unJugador = ListaJugadores.getListaJugadores().pasarTurno();
            System.out.println("_________________________Turno del jugador "+unJugador.getIdJugador()+"_________________________");
            unJugador.jugarTurno();
            comprobarGanador = ListaJugadores.getListaJugadores().comprobarGanador();
            numTurnos = numTurnos + 1;
        }
        
        if (comprobarGanador) {
            System.out.println(" ");
            System.out.println("¡Fin de la partida! Ha ganado " + "Jugador "+unJugador.getIdJugador());
            this.imprimirTablero();

        } else {
            System.out.println(" ");
            System.out.println("Fin de la partida Se ha superado el número de turnos.");
        }
    }
    
    //metodos auxiliares a otros metodos
    private void asignarIdACasillasMadre() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int idCasilla = calcularIdCasilla(i, j);
                tablero[i][j] = new CasillaMadre(idCasilla, "Has caído en una casilla normal.");
            }
        }
    }
    public CasillaMadre buscarCasilla(int idCasillaBuscada) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tablero[i][j].getIdCasilla() == idCasillaBuscada) {
                    return tablero[i][j]; 
                }
            }
        }
        return null; // Retorna null si no encuentra la casilla
    }
    private int calcularIdCasilla(int fila, int columna) {
        if (fila % 2 == 0) {
            return 64 - (fila * 8 + columna); // Si la fila es par (0, 2, 4, 6)

        } else {  // Si la fila es impar (1, 3, 5, 7)
            return 64 - (fila * 8 + (7 - columna));// Como va en dirección inversa, es 7-columna
        }
    }
    private Coordenadas obtenerCoordenadas(int idCasilla) {
        int fila = (64 - idCasilla) / 8;// no se considera el residuo
        int columna;
    
        if (fila % 2 == 0) { // Si la fila es Par (0, 2, 4, 6)
            columna = (64 - idCasilla)- (fila * 8);
        } else { // Si la fila es impar (1, 3, 5, 7)
            columna = 7 - (64 - idCasilla - (fila * 8));
        }
    
        return new Coordenadas(fila, columna);
    }
    

    //set necesario para convertir casillas madres originales a casillas especiales 

    public void setCasillaEspecial(int pIdCasilla, String pTipoCasilla, int pIdCasillaDestino) {
        Coordenadas coordenadas = obtenerCoordenadas(pIdCasilla);
        int fila = coordenadas.getFila();
        int columna = coordenadas.getColumna();
        
        String textoImprimir = "";
        
        if (fila >= 0 && fila < 8 && columna >= 0 && columna < 8) {
            switch(pTipoCasilla) {
                case "SERPIENTE":
                    textoImprimir = "¡Has caído en una serpiente! Bajas a la casilla " + pIdCasillaDestino;
                    tablero[fila][columna] = new CasillaEspecial(pIdCasilla, pIdCasillaDestino, textoImprimir);
                    break;
                case "ESCALERA":
                    textoImprimir = "¡Has encontrado una escalera! Subes a la casilla " + pIdCasillaDestino;
                    tablero[fila][columna] = new CasillaEspecial(pIdCasilla, pIdCasillaDestino, textoImprimir);
                    break;
                case "ALINICIO":
                    textoImprimir = "¡Oh no! Vuelves a la casilla de inicio.";
                    tablero[fila][columna] = new CasillaEspecial(pIdCasilla, 1, textoImprimir);
                    break;
                case "CASIFIN":
                    textoImprimir = "¡Lanza el dado y si obtienes 6, Avanza a la casilla 62!";
                    tablero[fila][columna] = new CasillaCasiFin(pIdCasilla, textoImprimir);
                    break;
            }
        }
    }
    public void cargarTableroDesdeArchivo(String pNombreArchivo) {
        this.asignarIdACasillasMadre();

        //para poder usar ruta relativa y no absoluta 
        String dirActual = System.getProperty("user.dir");
        String pathArchivoTablero = dirActual + File.separator + pNombreArchivo;

        try {
            InputStream fichero = new FileInputStream(pathArchivoTablero);
            Scanner sc = new Scanner(fichero);
            
            while (sc.hasNextLine()) {//recorre cada linea del archivo
                String linea = sc.nextLine();
                if (linea.isEmpty() || linea.startsWith("#")) {// trim es metodo de la clase String de java
                    continue;
                }

                String[] datos = linea.split(",");

                if (datos.length >= 3) {
                    String pTipoCasilla = datos[0].trim();
                    //al leer del archivo es tipo string, con parseint se hace tipo int
                    int pIdCasilla = Integer.parseInt(datos[1].trim());
                    int pIdCasillaDestino = Integer.parseInt(datos[2].trim());
                    
                    String textoImprimir = "";
                    if (datos.length > 3) { //en caso de que agreguen su propio texto 
                        textoImprimir = datos[3].trim();
                    }
                    
                    //modifica de casilla normal a casilla especial 
                    this.setCasillaEspecial(pIdCasilla, pTipoCasilla, pIdCasillaDestino);
                }
            }

            sc.close();
            System.out.println("Tablero cargado correctamente desde el archivo");
            
        } catch (Exception e) {
            System.out.println("Error al abrir el archivo: " + e.getMessage());
            System.out.println("Se creara tablero automatico");
            this.crearTableroBasico();
        }
    }

    public void imprimirTablero() {
        System.out.println("------------------------------------------------------");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(tablero[i][j].getIdCasilla() + "\t");
            }
            System.out.println();
            System.out.println("+------+------+------+------+------+------+-------+---------+");
        }
    }

    
}