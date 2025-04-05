    public CasillaMadre buscarCasilla(Jugador jugador) {
        int idCasillaJugador = jugador.getIdCasillaPosicion();  
    
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tablero[i][j].getIdCasilla() == idCasillaJugador) {
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

    private int[] obtenerCoordenadas(int idCasilla) {
        int fila = (64 - idCasilla) / 8;
        int columna;

        if (fila % 2 == 0) {
            columna = 64 - idCasilla - (fila * 8);
        } else {
            columna = 7 - (64 - idCasilla - (fila * 8));
        }

        return new int[]{fila, columna};
    }