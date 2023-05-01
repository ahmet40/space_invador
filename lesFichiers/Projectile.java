
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
        ch.ajouteChaine((int)posX,(int)posY, "¤");
        return ch;
    }
    /**
     * methode qui va renvoyer la valeur de la position en X du projectile
     * @return
     */
    public double getPosX() {
        return posX;
    }
    /**
     * permet de renvoyer la position en y du projectile
     * @return un double
     */
    public double getPosY() {
        return posY;
    }

    /**
     * permet de modifier la position y
     * @param posY la nouvelle position
     */
    public void setPosY(double posY) {
        this.posY = posY;
    }

    public boolean contient(int x,int y){
        return this.getEnsembleChaines().contient(x, y);
    }



    ///**
    // * permet de faire evolue le projectile vers les aliens
    // */
    //public void evolue(){
    //    this.posY+=0.2;
    //}

}