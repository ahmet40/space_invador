import java.util.List;
import java.util.ArrayList;


/**
 * permet de cree le gestionneur du jeu
 */
public class GestionJeu{
    private int hauteur;
    private int largeur;
    /**
     * ceci est la methode qui va crée notre gestionnaire de jeu
     */
    public GestionJeu(){
        this.hauteur=60;
        this.largeur=100;
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
        System.out.println("touche gauche");
    }
    /**
     * va renvoyer un message lorsque nous allons appuyer sur la touche droite
     */
    public void toucheDroite(){
        System.out.println("touche droite");
    }
    /**
     * va renvoyer un message lorsque nous allons appuyer sur la touche espace
     */
    public void toucheEspace(){
        System.out.println("touche espace");
    } 

    public EnsembleChaines getChaines(){
        EnsembleChaines e = new EnsembleChaines();
        e.ajouteChaine(0, 30, "@@");
        return e;
    }
    public void jouerUnTour(){

    }


}