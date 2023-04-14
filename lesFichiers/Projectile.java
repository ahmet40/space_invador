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
    public EnsembleChaines getEnsembleChaines(){
        EnsembleChaines ch=new EnsembleChaines();
        ch.ajouteChaine((int)posX,(int)posY, "^");
        return ch;
    }
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