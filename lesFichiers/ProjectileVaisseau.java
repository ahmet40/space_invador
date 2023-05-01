public class ProjectileVaisseau extends Projectile{
    private double evoluer;
    public ProjectileVaisseau(double x,double y){
        super(x,y);
        this.evoluer=0.2;
    }
    public void evolue(){
        double nouvelleposition=super.getPosY() +this.evoluer;
        super.setPosY(nouvelleposition);
    }


}