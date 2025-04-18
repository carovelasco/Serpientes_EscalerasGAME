import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class Tablero {
    private CasillaMadre[][] tablero;
    private static Tablero miTablero = null;

    private Tablero() {
        this.tablero = new CasillaMadre[8][8];
    }

    public static Tablero getTablero() {
        if (miTablero == null) {
            miTablero = new Tablero();
        }
        return miTablero;
    }
    
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
            System.out.println("_________________________Turno nº" + numTurnos + "_________________________");
            unJugador = ListaJugadores.getListaJugadores().pasarTurno();
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
    
    private void asignarIdACasillasMadre() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int idCasilla = calcularIdCasilla(i, j);
                tablero[i][j] = new CasillaMadre(idCasilla, "Has caído en una casilla normal.");
            }
        }
    }

    public CasillaMadre buscarCasilla(Jugador pJugador) {
        int idCasillaBuscada = pJugador.getIdCasillaPosicion();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tablero[i][j].getIdCasilla() == idCasillaBuscada) {
                    return tablero[i][j];
                }
            }
        }
        return null;
    }

    private int calcularIdCasilla(int fila, int columna) {
        if (fila % 2 == 0) {
            return 64 - (fila * 8 + columna);
        } else {
            return 64 - (fila * 8 + (7 - columna));
        }
    }

    private Coordenadas obtenerCoordenadas(int idCasilla) {
        int fila = (64 - idCasilla) / 8;
        int columna;
    
        if (fila % 2 == 0) {
            columna = 64 - idCasilla - (fila * 8);
        } else {
            columna = 7 - (64 - idCasilla - (fila * 8));
        }
    
        return new Coordenadas(fila, columna);
    }
    
     public void setCasillaEspecial(int idCasilla, String tipoCasilla, int idCasillaDestino, String textoImprimir) {
        Coordenadas coordenadas = obtenerCoordenadas(idCasilla);
        int fila = coordenadas.getFila();
        int columna = coordenadas.getColumna();
        
        if (fila >= 0 && fila < 8 && columna >= 0 && columna < 8) {
            switch(tipoCasilla) {
                case "SERPIENTE":
                    if (textoImprimir.isEmpty()) {
                        textoImprimir = "¡Has caído en una serpiente! Bajas a la casilla " + idCasillaDestino;
                    }
                    tablero[fila][columna] = new CasillaEspecial(idCasillaDestino, textoImprimir);
                    break;
                case "ESCALERA":
                    if (textoImprimir.isEmpty()) {
                        textoImprimir = "¡Has encontrado una escalera! Subes a la casilla " + idCasillaDestino;
                    }
                    tablero[fila][columna] = new CasillaEspecial(idCasillaDestino, textoImprimir);
                    break;
                case "ALINICIO":
                    if (textoImprimir.isEmpty()) {
                        textoImprimir = "¡Oh no! Vuelves a la casilla de inicio.";
                    }
                    tablero[fila][columna] = new CasillaEspecial(1, textoImprimir);
                    break;
                case "CASIFIN":
                    if (textoImprimir.isEmpty()) {
                        textoImprimir = "¡Avanzas hasta casi el final!";
                    }
                    tablero[fila][columna] = new CasillaCasiFin(idCasillaDestino, textoImprimir);
                    break;
                default:
                    tablero[fila][columna] = new CasillaMadre(idCasilla, textoImprimir);
            }
            
            tablero[fila][columna].setIdCasilla(idCasilla);
        }
    }
    
    public void cargarTableroDesdeArchivo(String nombreArchivo) {
        this.asignarIdACasillasMadre();

        String dirActual = System.getProperty("user.dir");
        String pathArchivoTablero = dirActual + File.separator + nombreArchivo;

        try {
            InputStream fichero = new FileInputStream(pathArchivoTablero);
            Scanner sc = new Scanner(fichero);
            
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (linea.trim().isEmpty() || linea.startsWith("#")) {
                    continue;
                }

                String[] datos = linea.split(",");

                if (datos.length >= 3) {
                    String tipoCasilla = datos[0].trim();
                    int idCasilla = Integer.parseInt(datos[1].trim());
                    int idCasillaDestino = Integer.parseInt(datos[2].trim());
                    
                    String textoImprimir = "";
                    if (datos.length > 3) {
                        textoImprimir = datos[3].trim();
                    }
                    
                    this.setCasillaEspecial(idCasilla, tipoCasilla, idCasillaDestino, textoImprimir);
                }
            }

            sc.close();
            System.out.println("Tablero cargado correctamente desde el archivo: " + nombreArchivo);
            
        } catch (Exception e) {
            System.out.println("Error al abrir el archivo: " + e.getMessage());
            System.out.println("Se crea tablero automatico");
            this.crearTableroBasico();
        }
    }

    public void imprimirTablero() {
        System.out.println("------------------------------------------");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(tablero[i][j].getIdCasilla() + "\t");
            }
            System.out.println();
            System.out.println("+------+------+------+------+------+------+-------+---------+");
        }
    }

    public void crearTableroBasico() {
        this.asignarIdACasillasMadre();
        
        setCasillaEspecial(4, "ESCALERA", 14, "¡Has encontrado una escalera! Subes a la casilla 14");
        setCasillaEspecial(9, "SERPIENTE", 6, "¡Has caído en una serpiente! Bajas a la casilla 6");
        setCasillaEspecial(20, "ESCALERA", 38, "¡Has encontrado una escalera! Subes a la casilla 38");
        setCasillaEspecial(28, "SERPIENTE", 15, "¡Has caído en una serpiente! Bajas a la casilla 15");
        setCasillaEspecial(40, "ALINICIO", 1, "¡Oh no! Vuelves a la casilla de inicio.");
        setCasillaEspecial(51, "ESCALERA", 67, "¡Has encontrado una escalera! Subes a la casilla 67");
        setCasillaEspecial(54, "CASIFIN", 62, "¡Avanzas hasta casi el final!");
        setCasillaEspecial(71, "SERPIENTE", 21, "¡Has caído en una serpiente! Bajas a la casilla 21");
        
        System.out.println("Tablero básico automatico creado");
    }
}