package src;

import java.util.List;
import java.util.ArrayList;


/**
 * permet de cree le gestionneur du jeu
 */
public class GestionJeu{
    private int hauteur;
    private int largeur;
    private int positionX=0;
    private EnsembleChaines e;
    private Vaisseau v;
    /**
     * ceci est la methode qui va crée notre gestionnaire de jeu
     */
    public GestionJeu(){
        this.hauteur=60;
        this.largeur=100;
        this.e= new EnsembleChaines();
        this.v=new Vaisseau(0);
    }
    /**
     * permet de renvoyer la hauteur 
     * @return un entier qui represente la hauteur
     */
    public int getHauteur() {
        return hauteur;
    }
    /**
     * permet de renvoyer la largeur
     * @return un entier representant la largeur 
     */
    public int getLargeur() {
        return largeur;
    }
    /**
     * va renvoyer un message lorsque nous allons appuyer sur la touche gauche
     */
    public void toucheGauche(){
        if (positionX-4>0){
            this.positionX-=4;
            v.deplace(positionX);
        }
        System.out.println("Appui sur la touche gauche");
    }
    /**
     * va renvoyer un message lorsque nous allons appuyer sur la touche droite
     */
    public void toucheDroite(){
        if (positionX+4<this.largeur){
            this.positionX+=4;
            v.deplace(positionX);
        }
        System.out.println("Appui sur la touche droite");
    }
    /**
     * va renvoyer un message lorsque nous allons appuyer sur la touche espace
     */
    public void toucheEspace(){
        System.out.println("Appui sur la touche espace");
    } 

    public EnsembleChaines getChaines(){
        this.e=new EnsembleChaines();
        System.out.println("hahahaha");
        this.e.union(v.getEnsembleChaines());
        return this.e;
    }
    public void jouerUnTour(){

    }


}