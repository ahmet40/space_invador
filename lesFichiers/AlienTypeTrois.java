
/**
 * Permet de construire un alien de type 3 qui va heriter de la class Alien.
 */
public class AlienTypeTrois extends Alien{
    /**
     * Permet de construire un alien de type 3 avec le constructeur de la class mere (Alien)
     * @param posX la position x de l'alien
     * @param posY la position y de l'alien
     */
    public AlienTypeTrois(double posX,double posY){super(posX,posY);}

    @Override
    /**
     * permet de cree l'image de l'alien
     * @return un ensemble de chaine contenant l'image de notre alien
     */
    public EnsembleChaines getEnsembleChaines(){
        EnsembleChaines ch=new EnsembleChaines();
        
        ch.ajouteChaine((int)this.getPosX(),(int)this.getPosY()+4," ▄▄████▄▄ ");
        ch.ajouteChaine((int)this.getPosX(),(int)this.getPosY()+3,"██████████");
        ch.ajouteChaine((int)this.getPosX(),(int)this.getPosY()+2,"██▄▄██▄▄██");
        ch.ajouteChaine((int)this.getPosX(),(int)this.getPosY()+1," ▄▀ ▀▀ ▀▄ ");
        ch.ajouteChaine((int)this.getPosX(),(int)this.getPosY()  ,"▀        ▀");
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
        ch.ajouteChaine((int)this.getPosX(),(int)this.getPosY()+4," ▄▄████▄▄ ");
        ch.ajouteChaine((int)this.getPosX(),(int)this.getPosY()+3,"██████████");                        
        ch.ajouteChaine((int)this.getPosX(),(int)this.getPosY()+2,"██▄▄██▄▄██");
        ch.ajouteChaine((int)this.getPosX(),(int)this.getPosY()+1," ▄▀ ▀▀ ▀▄ ");  
        ch.ajouteChaine((int)this.getPosX(),(int)this.getPosY()  ,"  ▀    ▀  ");
        for (ChainePositionnee c:ch.getChaines()){
            c.setIsRouge(true);
        }
        return ch;
    }

}
