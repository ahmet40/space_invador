public class ProjectileVaisseau extends Projectile{
    private double evoluer;
    public ProjectileVaisseau(double x,double y){
        super(x,y);
        this.evoluer=0.2;
    }

    public void evolue(){
        super.evolue();
    }
    @Override
    public EnsembleChaines getEnsembleChaines(){
        EnsembleChaines ch=new EnsembleChaines();
        ch.ajouteChaine((int)super.getPosX(),(int)super.getPosY(), "|");
        return ch;
    };


}
