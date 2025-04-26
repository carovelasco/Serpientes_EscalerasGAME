import static org.junit.Assert.*;

public class JugadorTest
{
  Jugador j1,j2;

@Before
public void setUp() throws Exception
{
  j1=new Jugador(1);
  j2=new Jugador(2);
}

@After
public void tearDown() throws Exception
{
  j1=null;
  j2=null;
}

@Test 
public void testPoscicionInicial(){
    assertEquals(1,j1.getIdCasillaPosicion());
    assertEquals(2,j2.getIdCasillaPosicion());
}
@Test
public void testGetIdJugador()
{
assertEquals(1,j1.getIdJugador());
j2=new Jugador(2);
assertEquals(2,j2.getIdJugador());
assertNotEquals(3,j2.getIdJugador());
}

@Test
public void testMoverPorDado()
{

    // Normal move
    j1.moverPorDado(5);
    assertEquals(6, j1.getIdCasillaPosicion());
        
    // Rebote
    j2.setIdCasillaPosicion(63);
    j2.moverPorDado(5);
    assertEquals(60, j2.getIdCasillaPosicion()); // 63+5=68 ->64-(68-64)=60

    j1.setIdCasillaPosicion(62);
    j1.moverPorDado(3);
    assertEquals(63,j1.getIdCasillaPosicion());// 62+3=65 ->64-(65-64)=63
        
    // Gana Exactamente
     j2.setIdCasillaPosicion(61);
     j2.moverPorDado(3);
     assertEquals(64, j2.getIdCasillaPosicion());

     j1.setIdCasillaPosicion(63);
     j1.moverPorDado(1);
     assertEquals(64,j1.getIdCasillaPosicion());

     //multiple moves 
     j1.setIdCasillaPosicion(5);
     j1.moverPorDado(6);
     assertEquals(11,j1.getIdCasillaPosicion());

     j1.moverPorDado(4);
     assertEquals(15,j1.getIdCasillaPosicion());

     j1.moverPorDado(5);
     assertEquals(20,j1.getIdCasillaPosicion());
     
}

@Test
public void testJugarTurno()
{
 int initialPosition = j1.getIdCasillaPosicion();
 j1.jugarTurno();
 assertNotEquals(initialPosition, j1.getIdCasillaPosicion());

 assertTrue(j1.getIdCasillaPosicion()>=1);
 assertTrue(j1.getIdCasillaPosicion()<=64);
}


@Test
public void testSetIdCasillaPosicion()
{
  j1.setIdCasillaPosicion(9);
  assertEquals(9,j1.getIdCasillaPosicion());

  j2=new Jugador(2);
  j2.setIdCasillaPosicion(25);
  assertEquals(25,j2.getIdCasillaPosicion());
}


}
