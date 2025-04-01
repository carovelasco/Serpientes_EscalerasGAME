import static org.junit.Assert.*;

public class JugadorTest
{
  Jugador j1,j2;

@Before
public void setUp() throws Exception
{
j1=new Jugador(1,0);
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
j2=new Jugador(2,0);
assertEquals(2,j2.getIdJugador());
}


public void testMoverPorDado()
{
int valorDado=5;
assertEquals(5,j1.moverPorDado(valorDado));

j2=new Jugador(2,63);
assertEquals(60,j2.moverPorDado(valorDado));

int valorDado=1;
assertEquals(62,j2.moverPorDado(valorDado));
}



public void testJugarTurno()
{
}



public void testSetIdCasillaPosicion()
{
assertEquals(1,j1.setIdCasillaPosicion(1));

j2=new Jugador(2,22)
assertEquals(25,j2.setIdCasillaPosicion(25));
}

}

