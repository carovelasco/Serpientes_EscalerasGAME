import static org.junit.Assert.*;

public class JugadorTest
{
  Jugador j1,j2;
	Tablero tablero;
  Dado dado;

	@Before
	public void setUp() throws Exception
	{
		Tablero.reiniciarParaPruebas();
    Dado.reiniciar();
    tablero = Tablero.getTablero();
    dado = Dado.getDado();
    tablero.crearTableroBasico();
	
	  j1=new Jugador(1);
	  j2=new Jugador(2);
	    
	    
	}

	@After
	public void tearDown() throws Exception
	{
		 dado.resetToRandom();
	   Tablero.reiniciarParaPruebas();
	   Dado.reiniciar();
	}

	@Test 
	public void testPoscicionInicial(){
	    assertEquals(1,j1.getIdCasillaPosicion());
	    assertEquals(1,j2.getIdCasillaPosicion());
	    assertNotEquals(3, j2.getIdCasillaPosicion());
      assertNotEquals(0, j1.getIdCasillaPosicion());
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
		dado.setValorDado(5);
        j1.moverPorDado(dado.lanzarDado());
        assertEquals(6, j1.getIdCasillaPosicion());
	        
	    // Rebote
        j2.setIdCasillaPosicion(63);
        dado.setValorDado(5);
        j2.moverPorDado(dado.lanzarDado());
        assertEquals(60, j2.getIdCasillaPosicion());
        
        j1.setIdCasillaPosicion(62);
        dado.setValorDado(5);
        j1.moverPorDado(dado.lanzarDado()); //61->19 Serpiente
        assertEquals(19, j1.getIdCasillaPosicion());
        
	    // Gana Exactamente
        j1.setIdCasillaPosicion(60);
        dado.setValorDado(4);
        j1.moverPorDado(dado.lanzarDado());
        assertEquals(64, j1.getIdCasillaPosicion());

	     j1.setIdCasillaPosicion(63);
	     j1.moverPorDado(1);
	     assertEquals(64,j1.getIdCasillaPosicion());

	     //multiple moves 
	     j1.setIdCasillaPosicion(5);
	     j1.moverPorDado(6);
	     assertEquals(11,j1.getIdCasillaPosicion());

	     j2.setIdCasillaPosicion(30);
	     j2.moverPorDado(5);
	     assertEquals(35, j2.getIdCasillaPosicion());

	     //Serpiente
	     j2.setIdCasillaPosicion(13);
	     j2.moverPorDado(3);
	     assertEquals(6, j2.getIdCasillaPosicion());

	     j2.setIdCasillaPosicion(42);
	     j2.moverPorDado(5);
	     assertEquals(26, j2.getIdCasillaPosicion());
	     
	     j2.setIdCasillaPosicion(45);
	     j2.moverPorDado(4);
	     assertEquals(11, j2.getIdCasillaPosicion());
	     
	     //Escalera
	     j2.setIdCasillaPosicion(1);
	     j2.moverPorDado(3);
	     assertEquals(14, j2.getIdCasillaPosicion());
	     
	     j2.setIdCasillaPosicion(6);
	     j2.moverPorDado(3);
	     assertEquals(39, j2.getIdCasillaPosicion());
	     
	     j2.setIdCasillaPosicion(20);
	     j2.moverPorDado(2);
	     assertEquals(44, j2.getIdCasillaPosicion());
	     
	     j2.setIdCasillaPosicion(32);
	     j2.moverPorDado(4);
	     assertEquals(41, j2.getIdCasillaPosicion());
	     
	     //AlInicio
	     j1.setIdCasillaPosicion(48);
	     j1.moverPorDado(3);
	     assertEquals(1, j1.getIdCasillaPosicion());
	     
	     //CasiFin
	     j1.setIdCasillaPosicion(24);
	     dado.setValorDado(3);
	     j1.moverPorDado(dado.lanzarDado());
	     assertEquals(27, j1.getIdCasillaPosicion()); // Esta en CASIFIN
	     
	     j1.setIdCasillaPosicion(23);
	     dado.setValorDado(4);
	     j1.moverPorDado(dado.lanzarDado());
	     assertEquals(27, j1.getIdCasillaPosicion()); // Esta en CASIFIN

	     j1.setIdCasillaPosicion(21);
	     dado.setValorDado(6);
	     j1.moverPorDado(dado.lanzarDado());
	     assertEquals(62, j1.getIdCasillaPosicion());


	}
	@Test
	public void testJugarTurno()
	{
		 // Test 1: Simple move
	    dado.setValorDado(4);
	    int initialPosition = j1.getIdCasillaPosicion();
	    j1.jugarTurno();
	    assertEquals(initialPosition + 4, j1.getIdCasillaPosicion());
	    
		 // Test 2: Verify dice is random after reset
	    dado.resetToRandom();
	    initialPosition = j1.getIdCasillaPosicion();
	    j1.jugarTurno();
	    assertTrue(j1.getIdCasillaPosicion() >= initialPosition + 1);
	    assertTrue(j1.getIdCasillaPosicion() <= 64);
	    
	    // Test 3: Verify position stays within bounds
	    j1.setIdCasillaPosicion(63);
	    dado.setValorDado(6);
	    j1.jugarTurno();
	    assertEquals(59, j1.getIdCasillaPosicion()); // 63+6=69 → 64-(69-64)=59
	    
	    // Test 4: Verify special squares
	    j1.setIdCasillaPosicion(3); // Position before ladder to 14
	    dado.setValorDado(1);
	    j1.jugarTurno();
	    assertEquals(14, j1.getIdCasillaPosicion()); // Ladder from 4 to 14
	
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

