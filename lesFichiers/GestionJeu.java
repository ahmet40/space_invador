import java.util.List;
import java.util.ArrayList;


/**
 * permet de cree le gestionneur du jeu
 */
public class GestionJeu{
    private int hauteur;
    private int largeur;
    private int positionX=0;
    private Vaisseau v;
    private Projectile projectile;
    private Score score;
    private List<Alien> lesAliens;
    /**
     * ceci est la methode qui va crée notre gestionnaire de jeu
     */
    public GestionJeu(){
        this.hauteur=60;
        this.largeur=100;
        this.projectile=null;
        this.v=new Vaisseau(30);
        this.score=new Score();
        this.lesAliens=new ArrayList<>();
        for (int i=0;i<4;i++){
            for (int j=0;j<2;j++){
                this.lesAliens.add(new Alien(i*15, j*15+20));
            }
        }
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
        if (this.v.getPosX()-4>0){
            v.deplace(-4);
        }
        System.out.println("Appui sur la touche gauche");
    }
    /**
     * va renvoyer un message lorsque nous allons appuyer sur la touche droite
     */
    public void toucheDroite(){
        if (this.v.getPosX()+4<this.largeur-16){
            v.deplace(4);
        }
        System.out.println("Appui sur la touche droite");
    }
    /**
     * va renvoyer un message lorsque nous allons appuyer sur la touche espace
     */
    public void toucheEspace(){
        if (projectile==null){
            this.projectile=new Projectile(this.v.positionCanon(), 4);
            System.out.println(this.projectile);
        }
        System.out.println("Appui sur la touche espace");
    } 

    public EnsembleChaines getChaines(){
        EnsembleChaines e=new EnsembleChaines();
        e.union(this.v.getEnsembleChaines());
        if (this.projectile!=null){
            e.union(this.projectile.getEnsembleChaines());
        }
        for (Alien a:this.lesAliens){e.union(a.getEnsembleChaines());}
        return e;
    }
    public void jouerUnTour(){
        if (projectile!=null){
            if (this.projectile.getPosY()<this.hauteur){
                this.projectile.evolue();
            }
            else{
                this.projectile=null;
            }
        }
        score.ajoute(1);
    }


}