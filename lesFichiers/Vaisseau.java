
/**
 * permet d'initialiser la class vaisseau
 */
public class Vaisseau {
    private double posX;
    /**
     * permet de crée un vaisseau
     */
    public Vaisseau(double posX){
        this.posX=posX;
    }
    /**
     * permet de renvoyer la position en X du vaisseau
     * @return un double la position du vaisseau
     */
    public double getPosX() {
        return posX;
    }
    /**
     * permet de deplacer le vaisseau de x position
     * @param dx le nombre de x qui va se deplacer
     */ 
    public void deplace(double dx){
        this.posX+=dx;
    }
    /**
     * methode qui va nous permettre de postionner le canon d'ou le projectile va etre tirer
     * @return la postion en X du canon
     */
    public int positionCanon(){
        return (int)posX+6;
    }
    /**
     * methode qui va representer notre vaisseau sous forme d'un ensemble de chaine
     * @return l'ensemble de chaine 
     */
    public EnsembleChaines getEnsembleChaines(){
        EnsembleChaines ch=new EnsembleChaines();
        ch.ajouteChaine((int)posX,4,"      ▄      ");
        ch.ajouteChaine((int)posX,3,"     ███     ");                        
        ch.ajouteChaine((int)posX,2,"▄███████████▄" );
        ch.ajouteChaine((int)posX,1,"▄███████████▄");  
        ch.ajouteChaine((int)posX,0,"█████████████");
        return ch;
    }
}