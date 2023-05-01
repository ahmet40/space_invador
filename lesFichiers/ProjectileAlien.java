public class ProjectileAlien extends Projectile{
    private double evoluer;
    public ProjectileAlien(double x,double y){
        super(x,y);
        this.evoluer=0.2;
    }
    public void evolue(){
        double nouvelleposition=super.getPosY() - this.evoluer;
        super.setPosY(nouvelleposition);
    }


}