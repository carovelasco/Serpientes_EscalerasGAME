import static org.junit.Assert.*;

public class JugadorTest
{
  Jugador j1,j2;

@Before
public void setUp() throws Exception
{
j1=new Jugador(1);
}

@After
public void tearDown() throws Exception
{
j2=null;
}

@Test
public void testGetIdJugador()
{
assertEquals(1,j1.getIdJugador());
j2=new Jugador(2);
assertEquals(2,j2.getIdJugador());
}


public void testMoverPorDado()
{

    // Normal move
    j1.moverPorDado(5);
    assertEquals(6, j1.getIdCasillaPosicion());
        
    // Rebote
    j2 = new Jugador(2);
    j2.setIdCasillaPosicion(63);
    j2.moverPorDado(5);
    assertEquals(60, j2.getIdCasillaPosicion());
        
    // Gana Exactamente
     j2.setIdCasillaPosicion(61);
     j2.moverPorDado(3);
     assertEquals(64, j2.getIdCasillaPosicion());
}

 public void testHaGanado() 
{
     assertFalse(j1.haGanado());

     j1.setIdCasillaPosicion(63);
     assertFalse(j1.haGanado());

     j1.setIdCasillaPosicion(64);
     assertTrue(j1.haGanado());
 }

public void testJugarTurno()
{
 int initialPosition = j1.getIdCasillaPosicion();
 j1.jugarTurno();
 assertNotEquals(initialPosition, j1.getIdCasillaPosicion());
}



public void testSetIdCasillaPosicion()
{
assertEquals(9,j1.setIdCasillaPosicion(9));

j2=new Jugador(2)
this.idCasillaPosicion=22;
assertEquals(25,j2.setIdCasillaPosicion(25));
}

}

