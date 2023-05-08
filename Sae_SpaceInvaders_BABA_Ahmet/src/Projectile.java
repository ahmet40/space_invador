
/**
 * Cette class permet de representer un projectile
 */
public abstract class Projectile {
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
     * cette methode permet de reresenter le projectile sous forme d'un ensemble de chaine. Elle sera codé les class fille
     * @return ensemble de chaine
     */
    public EnsembleChaines getEnsembleChaines(){
        EnsembleChaines ch=new EnsembleChaines();                       
        ch.ajouteChaine((int)getPosX(),(int) getPosY(), "|");
        for (ChainePositionnee c:ch.getChaines()){c.setIsWhite(true);}      
        // grâce au changement de l'attribut setIsWhite sur cette chaine, on va pouvoir mettre la couleur blanche à ce projectile. 
        return ch;
    };




    /**
     * methode qui va renvoyer la valeur de la position en X du projectile
     * @return renvoie la position X du projectile
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

    /**
     * permet de savoir si le projectile contient les position x,y
     * @param x la position x que l'on va verifier
     * @param y la position y que l'on va verifier.  
     * @return  le boolean indiquant si le projectile contient les position x,y 
     */
    public boolean contient(int x,int y){
        return this.getEnsembleChaines().contient(x, y);
    }



    /**
     * permet de faire evolue le projectile vers les aliens
     */
    public abstract void evolue();

}