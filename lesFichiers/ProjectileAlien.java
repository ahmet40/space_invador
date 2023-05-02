public class ProjectileAlien extends Projectile{
    private double evoluer;
    public ProjectileAlien(double x,double y){
        super(x,y);
        this.evoluer=0.2;
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
        ch.ajouteChaine((int)super.getPosX(),(int)super.getPosY(), "¤");
        return ch;
    };


}