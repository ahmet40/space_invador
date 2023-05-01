import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import java.awt.Rectangle;
import javafx.scene.shape.Circle;


/**
 * class permetant d'avoir des aliennes dans le jeu
 */
public class Alien {
    private double posX;
    private double posY;
    private String deplacementDroiteGauche;
    private boolean dep;
    private Color couleur;

    /**
     * permet de crée un alienne
     * @param posX sa position en x representer par un entier 
     * @param posY se position en y representer par un entier
     */
    public Alien(double posX,double posY){
        this.posX=posX;
        this.posY=posY;
        this.deplacementDroiteGauche="droite";
        this.dep=false;
        this.couleur=null;
    }
    
    /**
     * permet de renvoyer le boolean indiquant si un deplacement vers la droite à ete fait
     * @return le boolean
     */
    public boolean getDep(){
        return this.dep;
    }


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
     * permet de deplacer le vaisseau de x position
     * @param dx le nombre de x qui va se deplacer
     */ 
    public void deplace(double dx){
        this.posX+=dx;
    }


    /**
     * permet de cree l'image de l'alienne
     * @return un ensemble de chaine contenant l'image de notre alienne
     */
    public EnsembleChaines getEnsembleChaines(){
        EnsembleChaines ch=new EnsembleChaines();
        ch.ajouteChaine((int)posX,(int)posY+4," ▄▄████▄▄ ");
        ch.ajouteChaine((int)posX,(int)posY+3,"██████████");
        ch.ajouteChaine((int)posX,(int)posY+2,"██▄▄██▄▄██");
        ch.ajouteChaine((int)posX,(int)posY+1," ▄▀ ▀▀ ▀▄ ");
        ch.ajouteChaine((int)posX,(int)posY  ,"▀        ▀");
        for (ChainePositionnee c:ch.getChaines()){
            c.setIsRouge(true);
        }
        return ch;
    }


    /**
     * permet de cree l'image de l'alienne lorsqu'elle va bouger 
     * @return un ensemble de chaine
     */
    public EnsembleChaines anime(){
        EnsembleChaines ch=new EnsembleChaines();
        ch.ajouteChaine((int)posX,(int)posY+4," ▄▄████▄▄ ");
        ch.ajouteChaine((int)posX,(int)posY+3,"██████████");                        
        ch.ajouteChaine((int)posX,(int)posY+2,"██▄▄██▄▄██");
        ch.ajouteChaine((int)posX,(int)posY+1," ▄▀ ▀▀ ▀▄ ");  
        ch.ajouteChaine((int)posX,(int)posY  ,"  ▀    ▀  ");
        for (ChainePositionnee c:ch.getChaines()){
            c.setIsRouge(true);
        }
        return ch;

    }

    public void setPosY(double p){
        this.posY -= p;

    }

    /**
     * permet de changer la valeur de deplecement DroiteGauche
     */
    public void changerDeplacement(){
        if (deplacementDroiteGauche.equals("droite")){deplacementDroiteGauche="gauche";}
        else{deplacementDroiteGauche="droite";}
    }

    /**
     * Cette methode va permettre à l'alienne de ce deplacer vers la droite durant 100 tours puis descendre de 1 et se deplacer à gauche durant 100 tours
     */
    public void evolue(){

        if (deplacementDroiteGauche.equals("droite")){
            posX+=0.1;
//            anime();
            //if (dep){dep=false;}
            //else{dep=true;}
        }
        else{
            posX-=0.1;
//            anime();
            //if (dep){dep=false;}
            //else{dep=true;}
        }
        //posY-=0.01;
    }

    /**
     * cette methode permet de verifier si la position x,y d'un objet touche l'alienne
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
    public String toString(){
        String ch="";
        EnsembleChaines e=getEnsembleChaines();
        for (ChainePositionnee c:e.getChaines()){
            ch+=c;
        }
        return ch;
    }
    public void setColor(Color c){this.couleur=c;}



}
