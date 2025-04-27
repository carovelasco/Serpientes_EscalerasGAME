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

    public static void reiniciarParaPruebas(){
        miTablero=null;
    }

    //para el caso en que el archivo ingresado no sea correcto o no exista se crea uno por default 
    public void crearTableroBasico() {
        this.asignarIdACasillasMadre();
        
        setCasillaEspecial(4, "ESCALERA", 14);
        setCasillaEspecial(16, "SERPIENTE", 6);
        setCasillaEspecial(9, "ESCALERA", 39);
        setCasillaEspecial(47, "SERPIENTE", 26);
        setCasillaEspecial(51, "ALINICIO", 1);
        setCasillaEspecial(22, "ESCALERA", 44);
        setCasillaEspecial(27, "CASIFIN", 62);
        setCasillaEspecial(61, "SERPIENTE", 19);
        setCasillaEspecial(36, "ESCALERA", 41);
        setCasillaEspecial(61, "SERPIENTE", 19);
        
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

        while (!comprobarGanador && numTurnos < 51) {
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
    public Coordenadas obtenerCoordenadas(int idCasilla) {
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
        
        boolean formatoValido = true;
        
        try {
            InputStream fichero = new FileInputStream(pathArchivoTablero);
            Scanner sc = new Scanner(fichero);
            
            while (sc.hasNextLine() && formatoValido) {
                String linea = sc.nextLine();
                // Ignorar líneas vacías o comentarios
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }
                
                String[] datos = linea.split(",");// dividir segun las comas
                
                if (datos.length < 3) {// debe haber 3 campos
                    System.out.println("Error de formato: la línea no tiene los 3 campos requeridos: " + linea);
                    formatoValido = false;
                    continue;
                }
                
                try {
                    String pTipoCasilla = datos[0].trim().toUpperCase(); // Revisar que el tipo de casilla se llame como deberia
                    if (!pTipoCasilla.equals("SERPIENTE") && !pTipoCasilla.equals("ESCALERA") && 
                        !pTipoCasilla.equals("ALINICIO") && !pTipoCasilla.equals("CASIFIN")) {
                        System.out.println("Error: tipo de casilla no válido: " + pTipoCasilla);
                        formatoValido = false;
                        continue;
                    }
                    
                    int pIdCasilla = Integer.parseInt(datos[1].trim());// convertir el numero en string a entero 
                    int pIdCasillaDestino = Integer.parseInt(datos[2].trim());
                    
                    if (pIdCasilla < 1 || pIdCasilla > 64 || pIdCasillaDestino < 1 || pIdCasillaDestino > 64) {//checar que si este dentro de 1-64
                        System.out.println("Error: ID de casilla fuera de rango (1-64): " + pIdCasilla + ", " + pIdCasillaDestino);
                        formatoValido = false;
                        continue;
                    }
                    
                    // que tenga sentido subir o bajar 
                    if (pTipoCasilla.equals("SERPIENTE") && pIdCasillaDestino >= pIdCasilla) {
                        System.out.println("Error: una serpiente debe llegar a una casilla con ID menor: " + pIdCasilla + " -> " + pIdCasillaDestino);
                        formatoValido = false;
                        continue;
                    }
                    
                    if (pTipoCasilla.equals("ESCALERA") && pIdCasillaDestino <= pIdCasilla) {
                        System.out.println("Error: una escalera debe llevar a una casilla con ID mayor: " + pIdCasilla + " -> " + pIdCasillaDestino);
                        formatoValido = false;
                        continue;
                    }
                    
                    if (pTipoCasilla.equals("ALINICIO") && pIdCasillaDestino != 1) {
                        System.out.println("Error: Casilla AlInicio siempre debe llevarte a casilla 1: ");
                        formatoValido = false;
                        continue;
                    }
                    
                    if (pTipoCasilla.equals("CASIFIN") && pIdCasillaDestino != 62) {
                        System.out.println("Error: Casilla CasiFin siempre debe llevarte a casilla 62: ");
                        formatoValido = false;
                        continue;
                    }
                    
                    // Si llega aquí, todo es correcto
                    if (formatoValido) {
                        this.setCasillaEspecial(pIdCasilla, pTipoCasilla, pIdCasillaDestino);
                    }
                    
                } catch (NumberFormatException e) {
                    System.out.println("Error: los IDs de casillas deben ser números ");
                    formatoValido = false;
                }
            }
            
            sc.close();
            
            if (formatoValido) {
                System.out.println("Tablero cargado correctamente desde el archivo");
            } else {
                System.out.println("Se encontraron errores en el formato del archivo, se creará un tablero automático");
                this.crearTableroBasico();
            }
            
        } catch (Exception e) {
            System.out.println("Error al abrir o procesar el archivo: " + e.getMessage());
            System.out.println("Se creará tablero automático");
            this.crearTableroBasico();
        }
    }




    public void imprimirTablero() {
        System.out.println("------------------------------------------------------");
        for (int i = 0; i < 8; i++) {
            if (i %2 ==0){
                
            for (int j = 0; j < 8; j++) {
                System.out.print(tablero[i][j].getIdCasilla() + "\t");
            }
            }
            else{
                for(int j=7; j>=0; j--){
                    System.out.println(tablero[i][j].getIdCasilla()+"\t");
                }
            }
            System.out.println();
            System.out.println("+------+------+------+------+------+------+-------+---------+");
        }
    }

    
}
