import java.awt.Color;
/** Cercle modélisé par un point géométrique qui est un centre et un rayon
 * dans un plan équipé d'un repère cartésien.
 * @author  Rachida Oussakel
 */
        public class Cercle implements Mesurable2D { /** classe cercle réalise l'interface.*/
        private Point centre; /** le centre du cercle.*/
        private double rayon; /** le rayon du cercle.*/
        private Color couleur; /** couleur de la circonference.*/
        public static final double PI = Math.PI;
        /** Construire un cercle à partir d'un point et d'un rayon .
        * @param c centre
        * @param r rayon
         */
        public Cercle(Point c, double r) {
        assert c != null && r > 0;
        this.centre = new Point(c.getX(), c.getY());
        this.rayon = r;
        this.couleur = Color.blue;
		        }
        /** Construire un cercle à partir de deux points diamétralement opposés .
        * @param point1 Point
        * @param point2 Point
        */
        public Cercle(Point point1, Point point2) {
        assert point1 != null && point2 != null && point1.distance(point2) != 0;
        this.centre = new Point((point1.getX() + point2.getX()) / 2, (point1.getY() + point2.getY()) / 2);
        this.rayon = (point1.distance(point2)) / 2;
        this.couleur = Color.blue;
		        }
        /** Construire un cercle à partir de deux points diamétralement opposés et d'une couleur.
        * @param point1 Point
        * @param point2 Point
        * @param color couleur du cercle
        */
        public Cercle(Point point1, Point point2, Color color) {
        this(point1, point2);
        assert color != null ;
	    this.couleur = color;
		}
		/** Convertir les paramètres en un string.
		* @return string
		*/
		public String toString() {
		return "C" + this.rayon + "@" + this.centre.toString();
	    }
		/** Changer le centre du cercle.
		  * @param centre nouveau centre
		  */
		public void setCentre(Point centre) {
		    assert centre != null;
		    Point copiecentre = centre;
			this.centre = copiecentre;
		}
		/** Obtenir le centre du cercle.
		 * @return centre du cercle
		 */
		public Point getCentre() {
		    Point copiecentre = new Point(this.centre.getX(), this.centre.getY());
			return copiecentre;
		}
		/** Changer le rayon du centre.
		  * @param rayon nouveau rayon
		  */
		public void setRayon(double rayon) {
			assert rayon > 0;
			this.rayon = rayon;
		}
		/** Obtenir le rayon du cercle.
		 * @return rayon du cercle
		 */
		public double getRayon() {
			return rayon;
		}
		/** Changer le diamètre du centre.
		  * @param d nouveau diamètre
		  */
		public void setDiametre(double d) {
			assert d > 0;
			this.rayon = d / 2;
		}
		/** Obtenir le diamètre du cercle.
		 * @return diamètre du cercle
		 */
		public double getDiametre() {
			return rayon * 2;
		}
		/** Translater le cercle.
		* @param dx déplacement suivant l'axe des X
		* @param dy déplacement suivant l'axe des Y
		*/
		public void translater(double dx, double dy) {
			 this.centre.translater(dx, dy);
		}
		/** Vérifier si le point appartient au cercle.
		* @param autre Point
		* @return Boolean
		*/
		public boolean contient(Point autre) {
		    assert autre != null;
			return centre.distance(autre) <= rayon;
		}
         /** Calculer le périmètre du cercle.
		   * @return double perimetre
		  */
		 public double perimetre() {
		          return 2 * this.rayon * PI;
		        }
		  /** Calculer l'aire du cercle.
		   * @return double aire
		  */
         public double aire() {
			        return this.rayon * this.rayon * PI;
	     }
/** Créer un cercle à partir de deux points : le 1er est son centre et le 2ème    un point de sa circonference.
		* @param centre le centre du cercle
		* @param autre un point de la circonférence
		* @return c
	*/
		public static Cercle creerCercle(Point centre, Point autre) {
		     assert centre != null && autre != null && centre.distance(autre) != 0;
		     Cercle c = new Cercle(centre, centre.distance(autre));
		     return c;
		}
		 /** Afficher le cercle sous la forme décrite dans le sujet  Cr@(x, y).*/
		public void afficher() {
System.out.println("C" + this.rayon + "@(" + this.centre.getX() + "," + this.centre.getY() + ")");
		    }
	//  Gestion de la couleur
	     /** Obtenir la couleur du cercle.
		 * @return la couleur du cercle
		 */
		public Color getCouleur() {
			return this.couleur;
		}
        /** Changer la couleur du cercle.
		  * @param nouvelleCouleur nouvelle couleur
		  */
		public void setCouleur(Color nouvelleCouleur) {
		    assert nouvelleCouleur != null;
			this.couleur = nouvelleCouleur;
		}
	}

