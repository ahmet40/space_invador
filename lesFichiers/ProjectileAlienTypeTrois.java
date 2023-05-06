
/**
 * Cette classe represente le projectile des aliens? C'est une class fille qui herite de la class mére Projectile.
 */

 public class ProjectileAlienTypeTrois extends Projectile{
    private double evoluer;
    /**
     * Cette methode permet de crée un ProjectilAlien en utilisant le constructeur de sa mére.
     * @param x  la position x representer par un double
     * @param y  la position l representer par un double
     */
    public ProjectileAlienTypeTrois(double x,double y){
        super(x,y);
        this.evoluer=0.4;
    }
    @Override 
    public void evolue(){
        double nouvelleposition=super.getPosY() - this.evoluer;
        super.setPosY(nouvelleposition);
    }
    @Override
    /**
     * cette methode permet de reresenter le projectile sous forme d'un ensemble de chaine
     * @return ensemble de chaine
     */
    public EnsembleChaines getEnsembleChaines(){
        EnsembleChaines ch=new EnsembleChaines();
        ch.ajouteChaine((int)super.getPosX(),(int)super.getPosY(), "/");
        for (ChainePositionnee c:ch.getChaines()){c.setIsBlue(true);}
        // grâce au changement de l'attribut setIsBlue sur cette chaine, on va pouvoir mettre la couleur blanche à ce projectile. 
        return ch;
    };


}