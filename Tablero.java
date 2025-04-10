import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Tablero {
    private CasillaMadre[][] tablero;
    private static Tablero miTablero = null;
    private ListaJugadores listaJugadores;

    private Tablero() {
        this.tablero = new CasillaMadre[8][8];
        this.listaJugadores = ListaJugadores.getListaJugadores();
    }

    public static Tablero getTablero() {
        if (miTablero == null) {
            miTablero = new Tablero();
        }
        return miTablero;
    }
    
    
    private void inicializarCasillasMadre() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int idCasilla = calcularIdCasilla(i, j);
                tablero[i][j] = new CasillaMadre(idCasilla);
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
        return null; 

    private int calcularIdCasilla(int fila, int columna) {
        if (fila % 2 == 0) {
            return 64 - (fila * 8 + columna);
        } else {
            return 64 - (fila * 8 + (7 - columna));
        }
    }

    private ArrayList<Integer> obtenerCoordenadas(int idCasilla) {
        int fila = (64 - idCasilla) / 8;
        int columna;
        
        if (fila % 2 == 0) {
            columna = 64 - idCasilla - (fila * 8);
        } else {
            columna = 7 - (64 - idCasilla - (fila * 8));
        }
        
        ArrayList<Integer> coordenadas = new ArrayList<>();
        coordenadas.add(fila);
        coordenadas.add(columna);
        
        return coordenadas;
    }

    public void cargarTableroDesdeArchivo(String nombreArchivo) {
        String dirActual = System.getProperty("user.dir");
        String pathArchivoTablero = dirActual + File.separator + nombreArchivo;

        InputStream fichero;
        try {
            fichero = new FileInputStream(pathArchivoTablero);
        } catch (Exception e) {
            System.out.println("Error al abrir el archivo: " + e.getMessage());
            return;
        }

        Scanner sc = new Scanner(fichero);
        this.inicializarCasillasMadre();

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

                ArrayList<Integer> coordenadas = obtenerCoordenadas(idCasilla);
                int fila = coordenadas.get(0);
                int columna = coordenadas.get(1);

                if (fila >= 0 && fila < 8 && columna >= 0 && columna < 8) {
                    if (tipoCasilla.equals("SERPIENTE")) {
                        if (textoImprimir.isEmpty()) {
                            textoImprimir = "¡Has caído en una serpiente! Bajas a la casilla " + idCasillaDestino;
                        }
                        tablero[fila][columna] = new CasillaEspecial(idCasillaDestino, textoImprimir);
                    } else if (tipoCasilla.equals("ESCALERA")) {
                        if (textoImprimir.isEmpty()) {
                            textoImprimir = "¡Has encontrado una escalera! Subes a la casilla " + idCasillaDestino;
                        }
                        tablero[fila][columna] = new CasillaEspecial(idCasillaDestino, textoImprimir);
                    } else if (tipoCasilla.equals("ALINICIO")) {
                        if (textoImprimir.isEmpty()) {
                            textoImprimir = "¡Oh no! Vuelves a la casilla de inicio.";
                        }
                        tablero[fila][columna] = new CasillaEspecial(1, textoImprimir);
                    } else if (tipoCasilla.equals("CASIFIN")) {
                        if (textoImprimir.isEmpty()) {
                            textoImprimir = "¡Avanzas hasta casi el final!";
                        }
                        tablero[fila][columna] = new CasillaCasiFin(62, textoImprimir);
                    }

                    tablero[fila][columna].setIdCasilla(idCasilla);
                }
            }
        }

        sc.close();
        System.out.println("Tablero cargado correctamente desde el archivo: " + nombreArchivo);
    }

    public void imprimirTablero() {
        System.out.println("+------+------+------+------+------+------+-------+---------+");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(tablero[i][j].getIdCasilla() + "\t");
            }
            System.out.println();
            System.out.println("+------+------+------+------+------+------+-------+---------+"); }
    }

}