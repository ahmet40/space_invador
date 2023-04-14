
/**
 * permet d'initialiser la class vaisseau
 */
public class Alien {
    private double posX;
    private double posY;
    /**
     * permet de crée un vaisseau
     */
    public Alien(double posX,double posY){
        this.posX=posX;
        this.posY=posY;
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
    public EnsembleChaines getEnsembleChaines(){
        EnsembleChaines ch=new EnsembleChaines();
        ch.ajouteChaine((int)posX,(int)posY+4,"░░░░▄▄████▄▄░░░░░");
        ch.ajouteChaine((int)posX,(int)posY+3,"░░░██████████░░░░");                        
        ch.ajouteChaine((int)posX,(int)posY+2,"░░░██▄▄██▄▄██░░░░");
        ch.ajouteChaine((int)posX,(int)posY+1,"░░░░▄▀▄▀▀▄▀▄░░░░░");  
        ch.ajouteChaine((int)posX,(int)posY,"░░░▀░░░░░░░░▀░░░░");
        return ch;
    }
}





