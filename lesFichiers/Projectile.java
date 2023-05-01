
/**
 * Cette class permet de representer un projectile
 */
public class Projectile {
    private double posX;
    private double posY;
    /**
     * permet de construire un projectile
     * @param x un double la position x du projectile
     * @param y un double la position y du projectile
     */
    public Projectile(double x,double y){
        this.posX=x;
        this.posY=y;
    }
    /**
     * cette methode permet de reresenter le projectile sous forme d'un ensemble de chaine
     * @return ensemble de chaine
     */
    public EnsembleChaines getEnsembleChaines(){
        EnsembleChaines ch=new EnsembleChaines();
        ch.ajouteChaine((int)posX,(int)posY, "^");
        return ch;
    }
    /**
     * methode qui va renvoyer la valeur de la position en X du projectile
     * @return
     */
    public double getPosX() {
        return posX;
    }
    public double getPosY() {
        return posY;
    }
    public void evolue(){
        this.posY+=0.2;
    }

}