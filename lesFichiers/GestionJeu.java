import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

/**
 * permet de cree le gestionneur du jeu
 */
public class GestionJeu{
    private int hauteur;
    private int largeur;
    private int positionX=0;
    private Vaisseau v;
    private Projectile projectile;
    private List<Projectile> lesProjectiles;
    private Score score;
    private List<Alien> lesAliens;
    private int compteTours;
    private List<Projectile> lesProjectilesQuiTouche;
    private List<Alien> lesAliensTouche;
    //private boolean changercouleur;

    
    /**
     * ceci est la methode qui va crée notre gestionnaire de jeu
     */
    public GestionJeu(){
        this.lesProjectiles=new ArrayList<>();
        this.hauteur=60;
        this.largeur=100;
        this.projectile=null;
        this.v=new Vaisseau(30);
        this.score=new Score();
        this.lesAliens=new ArrayList<>();
        for (int i=0;i<2;++i){
            int c=10;
            for (int j=0;j<5;++j){
                if (i==0){this.lesAliens.add(new Alien(c, 30));
                        c+=15;}
                else{
                    this.lesAliens.add(new Alien(c, 40));
                    c+=15;
                }
                
            }
        }
        this.compteTours=0;
        this.lesAliensTouche=new ArrayList<>();
        this.lesProjectilesQuiTouche=new ArrayList<>();
        //this.changercouleur=false;
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
     * permet de renvoyer la liste (Arraylist) d'aliens
     * @return la liste
     */
    public List<Alien> getLesAliens() {
        return lesAliens;
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


    /**
     * cette methode va nous permettre d'avoid tous les objet( vaisseau, alienne, projectile) dans un ensemble de chaines
     * @return un ensemble de chaine
     */
    public EnsembleChaines getChaines(){
        EnsembleChaines e=new EnsembleChaines();
        if (!this.perdu()){
            e.ajouteChaine(10, getHauteur()-10, score.toString());
            e.union(this.v.getEnsembleChaines());
            if (this.projectile!=null){
                e.union(this.projectile.getEnsembleChaines());
            }
            if (lesAliens.size()!=0){  
                if (lesAliens.get(0).getDep()==false){
                    for (Alien a:this.lesAliens){
                        e.union(a.anime());
                        
                    }
                }
                else{
                    for (Alien a:this.lesAliens){
                        e.union(a.getEnsembleChaines());
                        
                    }
                    
                }
            }          
        }
        return e;
    }

    
    /**
     * methode qui represente tous ce qui va se passer lors d'un tour dans le jeu
     */
    public void jouerUnTour(){
        if (projectile!=null){
            for (Alien a:this.lesAliens){
                if(a.contient((int) Math.round(projectile.getPosX()), (int) Math.round(projectile.getPosY()))){
                   // System.out.println("TOUCHE");
                    this.lesAliensTouche.add(a);
                    //projectile=null;
                    
                    this.lesProjectilesQuiTouche.add(projectile);
                }
            }
            if (projectile!=null){
                if (this.projectile.getPosY()<this.hauteur){
                    this.projectile.evolue();
                }
                else{
                    this.projectile=null;
                }
            }
        }
        for (Alien a:this.lesAliens){
            a.evolue();
            if(this.compteTours % 10 == 0)
            a.anime();
            if (this.compteTours%20==0){a.changerDep();}
        }

        if (this.compteTours==100){
            for (Alien a:this.lesAliens){
                a.changerDeplacement();
                compteTours=0;
                a.setPosY(10);
            }
        }

        this.compteTours++;
        score.ajoute(1);
        for (Alien a:this.lesAliensTouche){this.lesAliens.remove(a);}
        for (Projectile p:this.lesProjectilesQuiTouche){
            if (p.equals(this.projectile)){projectile=null;}
        }
        if(this.lesProjectilesQuiTouche.size()!=0){
            this.lesProjectilesQuiTouche=new ArrayList<>();
        }
        System.out.println((int)Math.round(lesAliens.get(0).getPosY()));
    }   


    /**
     * verifie que nous avons gagner le jeu
     * @return un boolean qui va indiquer la condition
     */
    public boolean gagner(){
        if (lesAliens.size()==0){return true;}
        return false;
    }

    
    /**
     * methode qui permet de dire si nous avons perdu
     * @return un boolean
     */
    public boolean perdu(){
        if (lesAliens.size()!=0){
            for (Alien a:this.lesAliens){
                if ((int) Math.round(a.getPosY())<=5){return true;}
            }
        }
        return false;
    }
}