
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
    public int positionCanon(){
        return (int)posX+8;
    }
    public EnsembleChaines getEnsembleChaines(){
        EnsembleChaines ch=new EnsembleChaines();
        ch.ajouteChaine((int)posX,4,"░░░░░░░░▄░░░░░░░░");
        ch.ajouteChaine((int)posX,3,"░░░░░░░███░░░░░░░");                        
        ch.ajouteChaine((int)posX,2,"░░▄███████████▄░░" );
        ch.ajouteChaine((int)posX,1,"░░▄███████████▄░░");  
        ch.ajouteChaine((int)posX,0,"░░█████████████░░");
        return ch;
    }
}
