/**
 * class permetant de positionner une chaine de charactere
 */
public class ChainePositionnee{
    int x,y;
    String c;
    /**
     * permet de construire la chaine à potionner au coordonnee a,b
     * @param a un entier representant la position x
     * @param b un entier representant la position y
     * @param d une chaine de charactere representant la chaine
     */
    public ChainePositionnee(int a,int b, String d){x=a; y=b; c=d;}
    /**
     * permet de renvoyer le string 
     * @return la chaine de charactere
     */
    public String getC() {
        return c;
    }

}