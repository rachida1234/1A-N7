import java.awt.Color;
import org.junit.*;
import static org.junit.Assert.*;


public class CercleTest {

	// précision pour les comparaisons réelle
	public final static double EPSILON = 0.001;

	// Les points du sujet
	private Point A, B, C, D, E;
	@Before public void setUp() {
		// Construire les points
		A = new Point(1, 2);
		B = new Point(2, 1);
		C = new Point(4, 1);
		D = new Point(8, 1);
		E = new Point(8, 4);

	}
	
	
/** Test : Construction d'un cercle à partir de deux points différents */
	@Test public void testerE12a()
	{   Cercle c2 = new Cercle(A,B);
	    assertEquals(1.5,c2.getCentre().getX(),EPSILON);
	    assertEquals(1.5,c2.getCentre().getY(),EPSILON);
		assertEquals(Math.sqrt(2)/2,c2.getRayon(),EPSILON);
		assertTrue(c2.getCouleur() ==Color.blue);
	}


	
	/** Test :  Construction d'un cercle à partir de deux points diamétralement opposés et d'une couleur.*/
	@Test public void testerE13()
	{   Cercle c1 = new Cercle(C,D,Color.green);
	    assertEquals(6,c1.getCentre().getX(),EPSILON);
	    assertEquals(1,c1.getCentre().getY(),EPSILON);
		assertEquals(2,c1.getRayon(),EPSILON);
		assertTrue(c1.getCouleur() ==Color.green);
	}
	
	/** Test : Construction d'un cercle à partir de deux points : le 1er est le centre et le 2ème un point de sa circonference */
	@Test public void testerE14()
	{  Cercle c3=new Cercle(A,B);
	   c3=Cercle.creerCercle(D,E);
	   assertEquals(8, c3.getCentre().getX(),EPSILON);
	   assertEquals(1, c3.getCentre().getY(),EPSILON);
	   assertEquals(c3.getRayon(), D.distance(E),EPSILON);
	   assertTrue(c3.getCouleur() ==Color.blue); 
	}
	
	
	}
	
	
	
	
