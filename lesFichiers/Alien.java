/**
 * class permetant d'avoir des aliennes dans le jeu
 */
public abstract class Alien {
    private double posX;
    private double posY;
    private boolean deplacementDroiteGauche;
    private boolean dep;

    /**
     * permet de crée un alien
     * @param posX sa position en x representer par un double 
     * @param posY se position en y representer par un double
     */
    public Alien(double posX,double posY){
        this.posX=posX;
        this.posY=posY;
        this.deplacementDroiteGauche=true;
        this.dep=false;
        
    }
    
    /**
     * permet de renvoyer le boolean indiquant si un deplacement vers la droite à ete fait
     * @return le boolean
     */
    public boolean getDep(){
        return this.dep;
    }

    /**
     * va renvoyer le prejectil de l'alien. Toutes les methodes avec abstract seront coder dans les heritiers.
     * @return le projectile
     */
    public abstract Projectile getProjectileAlien();


    /**
     * permet de changer la valeur de dep
     */
    public void changerDep(){
        if (dep){dep=false;}
        else{dep=true;}
    }


    /**
     * permet de renvoyer la position en X du vaisseau
     * @return un double la position du vaisseau
     */
    public double getPosX() {
        return posX;
    }

    /**
     * methode qui renvoie la position y de l'alienne
     * @return un entier representant la position y
     */
    public double getPosY() {
        return posY;
    }


    /**
     * permet de deplacer l'alien de x position
     * @param dx le nombre de x qui va se deplacer
     */ 
    public void deplace(double dx){
        this.posX+=dx;
    }


    /**
     * permet de cree l'image de l'alien. Cette methode sera codé pour chaque heritié de la class.
     * @return un ensemble de chaine contenant l'image de notre alienne
     */
    public abstract EnsembleChaines getEnsembleChaines();

    /**
     * permet de cree l'image de l'alien lorsqu'elle va bouger. Cette methode sera codé pour chaque heritié de la class.
     * @return un ensemble de chaine
     */
    public abstract EnsembleChaines anime();
    
    /**
     * permet de changer la position y de l'alienne
     * @param p un double qui represente la nouvelle position
     */
    public void setPosY(double p){
        this.posY -= p;

    }

    /**
     * permet de changer la valeur de deplecement DroiteGauche
     */
    public void changerDeplacement(){
        if (deplacementDroiteGauche){deplacementDroiteGauche=false;}        //si ils se deplacait à droite on va maintenant les deplacer à gauche
        else{deplacementDroiteGauche=true;}                             // il vont maintenant se deplacer à droite
    }

    /**
     * Cette methode va permettre à l'alien de ce deplacer vers la droite durant 100 tours puis descendre de 1 et se deplacer à gauche durant 100 tours
     */
    public void evolue(){

        if (deplacementDroiteGauche){
            posX+=0.1;
        }
        else{
            posX-=0.1;
        }

    }

    /**
     * cette methode permet de verifier si la position x,y d'un objet touche l'alien
     * @param x un entier representant la position x
     * @param y un entier representant la position y
     * @return un boolean indiquant si l'objet touche notre alienne
     */
    public boolean contient(int x,int y){
        return this.getEnsembleChaines().contient(x, y);
    }

    @Override
    /**
     * cette methode va nous permettre de verifier si l'objet donner en parametre appartient à cette class et qu'il à une instance 
     * @return un boolean 
     */
    public boolean equals(Object o){
        if (o==this){return true;}
        if(o==null){return false;}
        if (o instanceof Alien){
            Alien al=(Alien) o;
            return this.posX==al.posX && this.posY==al.getPosY();
        }
        return false;

    }
    @Override
    /** 
     *  Permet d'avoir le hashcode de l'alien
     * @return renvoie un int indiquant le hashcode de l'alien
     */
    public int hashCode() {
        return 17*(int)posX+31*(int) posY;
    }

    /**
     * methode qui va nous permettre de postionner le canon d'ou le projectile va etre tirer
     * @return la postion en X du canon
     */
    public int positionCanon(){
        return (int)posX+5;
    }

    /**
     * va faire tirer le projectile
     */
    public abstract void tire();

    /**
     * va faire evoluer le projectile
     */
    public abstract void evolueProjectile();

    /**
     * permet de changer l'etat du projectile ( le faire passer à null)
     */
    public abstract void setProjectileAlien();
    
}

