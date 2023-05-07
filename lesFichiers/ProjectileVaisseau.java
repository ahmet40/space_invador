/**
 * Cette classe represente le projectile des Vaisseau? C'est une class fille qui herite de la class mére Projectile.
 */

public class ProjectileVaisseau extends Projectile{
    private double evoluer;
    /**
     * Cette methode permet de crée un ProjectilVaisseau en utilisant le constructeur de sa mére.
     * @param x  la position x representer par un double
     * @param y  la position l representer par un double
     */
    public ProjectileVaisseau(double x,double y){
        super(x,y);                 // permet d'appeler le constructeur de sa mére avec les position X,Y
        this.evoluer=0.4;
    }

    /**
     * cette methode permet de faire evoluer le projectile vers sa cible
     */
    public void evolue(){       
        double nouvelleposition=super.getPosY() + this.evoluer;
        super.setPosY(nouvelleposition);
    }
    @Override
    
    /**
     * cette methode permet de reresenter le projectile sous forme d'un ensemble de chaine
     * @return ensemble de chaine
     */
    public EnsembleChaines getEnsembleChaines(){                    
        EnsembleChaines ch=new EnsembleChaines();                       
        ch.ajouteChaine((int)super.getPosX(),(int)super.getPosY(), "⬛");
        for (ChainePositionnee c:ch.getChaines()){c.setIsWhite(true);}      
        // grâce au changement de l'attribut setIsWhite sur cette chaine, on va pouvoir mettre la couleur blanche à ce projectile. 
        return ch;
    };


}



