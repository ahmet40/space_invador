
/**
 * Permet de construire un alien de type 2 qui va heriter de la class Alien.
 */
public class AlienTypeDeux extends Alien{
    private ProjectileAlienTypeDeux projectile;
    /**
     * Permet de construire un alien de type 2 avec le constructeur de la class mere (Alien)
     * @param posX la position x de l'alien
     * @param posY la position y de l'alien
     */
    public AlienTypeDeux(double posX,double posY){super(posX,posY);projectile=null;}


    @Override
    public Projectile getProjectileAlien() {
        return projectile;
    }

    @Override
        /**
     * permet de cree l'image de l'alienne
     * @return un ensemble de chaine contenant l'image de notre alien
     */
    public EnsembleChaines getEnsembleChaines(){
        EnsembleChaines ch=new EnsembleChaines();
        
        ch.ajouteChaine((int)this.getPosX(),(int)this.getPosY()+4,"  ▀▄   ▄▀ ");
        ch.ajouteChaine((int)this.getPosX(),(int)this.getPosY()+3," ▄█▀███▀█▄ ");
        ch.ajouteChaine((int)this.getPosX(),(int)this.getPosY()+2,"█▀███████▀█");
        ch.ajouteChaine((int)this.getPosX(),(int)this.getPosY()+1,"█ █▀▀▀▀▀█ █");
        ch.ajouteChaine((int)this.getPosX(),(int)this.getPosY()  ,"   ▀▀ ▀▀   ");
        for (ChainePositionnee c:ch.getChaines()){
            c.setIsRouge(true);
        }
        return ch;
    }

    @Override
    /**
     * permet de cree l'image de l'alien lorsqu'elle va bouger 
     * @return un ensemble de chaine
     */
    public EnsembleChaines anime(){
        EnsembleChaines ch=new EnsembleChaines();
        ch.ajouteChaine((int)this.getPosX(),(int)this.getPosY()+4,"▄ ▀▄   ▄▀ ▄");
        ch.ajouteChaine((int)this.getPosX(),(int)this.getPosY()+3,"█▄███████▄█");                        
        ch.ajouteChaine((int)this.getPosX(),(int)this.getPosY()+2,"███▄███▄███");
        ch.ajouteChaine((int)this.getPosX(),(int)this.getPosY()+1,"▀█████████▀");  
        ch.ajouteChaine((int)this.getPosX(),(int)this.getPosY()  ," ▄▀     ▀▄ ");
        for (ChainePositionnee c:ch.getChaines()){
            c.setIsRouge(true);
        }
        return ch;
    }


    @Override
    /**
     * Cette methode fait tirer l'alien
     */
    public  void tire(){
        if (this.projectile==null){
            this.projectile=new ProjectileAlienTypeDeux(positionCanon(), this.getPosY()+1);
        }
        //this.projectileAlien=new ProjectileAlien(positionCanon(), posY+1);
       //  this.projectileAlien.evolue();
    }

    @Override
    /**
     * methode qui va faire evoluer le projectile
     */
    public  void evolueProjectile(){
        this.projectile.evolue();
    }

    @Override
    /**
     * methode qui va changer l'etat du projectile
     */
    public  void setProjectileAlien() {
        this.projectile = null;
    }

}
