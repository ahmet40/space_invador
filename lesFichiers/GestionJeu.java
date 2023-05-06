import java.util.List;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Random;

/**
 * permet de cree le gestionneur du jeu
 */
public class GestionJeu{
    private int hauteur;
    private int largeur;
    private Vaisseau v;
    private ProjectileVaisseau projectileVaisseau;
    private Score score;
    private List<Alien> lesAliens;
    private int compteTours;
    private List<Alien> lesAliensTouche;
    private int vieMaxVaisseau;

    private int nombreToursSansImage;
    /**
     * ceci est la methode qui va crée notre gestionnaire de jeu
     */
    public GestionJeu(){
        this.hauteur=60;
        this.largeur=100;
        this.projectileVaisseau=null;
        this.v=new Vaisseau(30);
        this.vieMaxVaisseau=v.getLaVie().getVie();
        this.score=new Score();
        this.lesAliens=new ArrayList<>();
        for (int i=0;i<2;++i){
            int c=10;
            int j=0;
            while (j<this.largeur/20){
                if (i==0){this.lesAliens.add(new Alien(c, 30));
                        c+=15;}
                else{
                    this.lesAliens.add(new Alien(c, 40));
                    c+=15;
                }
                ++j;
                
            }
        }
        this.compteTours=0;
        this.lesAliensTouche=new ArrayList<>();
        this.nombreToursSansImage=50;
    }


    /**
     * permet de renvoyer la hauteur 
     * @return un entier qui represente la hauteur
     */
    public int getHauteur() {
        return hauteur;
    }

    /**
     * permet de retourner le score 
     * @return Score le score
     */
    public Score getScore() {
        return score;
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
     * va renvoyer un message lorsque nous allons appuyer sur la touche gauche et va deplacer notre vaisseau si cela est possible
     */
    public void toucheGauche(){
        if (this.v.getPosX()-4>0){
            v.deplace(-4);
        }
        System.out.println("Appui sur la touche gauche");
    }



    /**
     * va renvoyer un message lorsque nous allons appuyer sur la touche droite et va deplacer notre vaisseau si cela est possible
     */
    public void toucheDroite(){
        if (this.v.getPosX()+4<this.largeur-9){
            v.deplace(4);
        }
        System.out.println("Appui sur la touche droite");
    }


    /**
     * va renvoyer un message lorsque nous allons appuyer sur la touche espace il va lancer un projectile si le vaisseau n'en a pas deja lancer un 
     */
    public void toucheEspace(){
        if (projectileVaisseau==null){
            this.projectileVaisseau=new ProjectileVaisseau(this.v.positionCanon(), 4);
            System.out.println(this.projectileVaisseau);
        }
        System.out.println("Appui sur la touche espace");
    } 


    /**
     * cette methode va nous permettre d'avoid tous les objet( vaisseau, alienne, projectile) dans un ensemble de chaines
     * @return un ensemble de chaine
     */
    public EnsembleChaines getChaines(){
        EnsembleChaines e=new EnsembleChaines();
        if (!perdu()){
            EnsembleChaines c=new EnsembleChaines();
            c.ajouteChaine(10, getHauteur()-10, score.toString());
            c.ajouteChaine(25, getHauteur()-10, this.v.getLaVie().toString());
            for (ChainePositionnee chaine:c.getChaines()){
               chaine.setIsWhite(true);
            }
            e.union(c);
            //e.ajouteChaine(10, getHauteur()-10, score.toString());
    
    
            if (this.projectileVaisseau!=null){
                e.union(this.projectileVaisseau.getEnsembleChaines());
            }
            if (lesAliens.size()!=0){  
                if (lesAliens.get(0).getDep()==false){
                    for (Alien a:this.lesAliens){
                        e.union(a.anime());
                        if (a.getProjectileAlien()!=null){e.union(a.getProjectileAlien().getEnsembleChaines());}
                    }
                }
                else{
                    for (Alien a:this.lesAliens){
                        e.union(a.getEnsembleChaines());
                        if (a.getProjectileAlien()!=null){e.union(a.getProjectileAlien().getEnsembleChaines());}
                    }
                    
                }
            } 
        
            if (vieMaxVaisseau-1==this.v.getLaVie().getVie()){
                if (nombreToursSansImage==0){
                    vieMaxVaisseau=this.v.getLaVie().getVie();
                    nombreToursSansImage=51;
                }
                if (nombreToursSansImage%11==0){e.union(this.v.getEnsembleChaines());}
                nombreToursSansImage-=1;
            }
            else{e.union(this.v.getEnsembleChaines());}
        }
        else{
            EnsembleChaines c=new EnsembleChaines();
            c.ajouteChaine(10, getHauteur()-10, score.toString());
            c.ajouteChaine(25, getHauteur()-10, this.v.getLaVie().toString());
            for (ChainePositionnee chaine:c.getChaines()){
               chaine.setIsWhite(true);
            }
            e.union(c);
            e.union(this.v.getEnsembleChaines());
            if (lesAliens.size()!=0){  
                if (lesAliens.get(0).getDep()==false){for (Alien a:this.lesAliens){e.union(a.anime());}}
                else{for (Alien a:this.lesAliens){e.union(a.getEnsembleChaines());}}
            } 
        }
        return e;
    }

    
    /**
     * methode qui represente tous ce qui va se passer lors d'un tour dans le jeu
     */
    public void jouerUnTour(){
        if (projectileVaisseau!=null){
            if (this.projectileVaisseau.getPosY()<this.hauteur){        // on regarde si la position y du projectil est inferieur à la longueur de la fenetre
                this.projectileVaisseau.evolue();                       // on va faire evoluer le projectile sur la meme position x
            }
            else{
                this.projectileVaisseau=null;                           // on remet le projectile à null si il à atteint le haut de la fenetre
            }
        }        


        if (lesAliens.size()>0){                              // si il y à des aliens
            for (Alien a:this.lesAliens){                    // pour chaque alien

                //on gere la partie entre les aliens et les projectiles
                if (projectileVaisseau !=null){   
                    // on verife que le projectile du vaisseau est different de null 
                    if(a.contient((int) Math.round(projectileVaisseau.getPosX()), (int) Math.round(projectileVaisseau.getPosY()))){
                        // si l'allien se fait touche par le projectil
                        this.lesAliensTouche.add(a);        // on ajoute l'alien à la liste pour le supprimer plus tard dans la methode
                        //this.lesProjectilesQuiTouche.add(projectileVaisseau);   //on ajoute
                        projectileVaisseau=null;
                    }
                }



                if (a.getProjectileAlien()!=null){          //si le projectille de l'alien est different de null (si il a tirer)
                    
                    if (v.contient((int) Math.round(a.getProjectileAlien().getPosX()),(int) Math.round(a.getProjectileAlien().getPosY()))){this.v.setLaVie();a.setProjectileAlien();}
                    // si le tire de l'alienne touche le vaisseau, on change la valeur de la vie du vaisseau et du projectile de l'alien
                    
                    if (a.getProjectileAlien()!=null){   // à la ligne d'avant le projectile peut etre mis a null donc une reverification est necessaire
                        if (a.getProjectileAlien().getPosY()>1){       //si la position y du projectile de l'alien ne depasse pas 1
                            a.evolueProjectile();                       //on le fait evoluer
                        }
                        else{                                            //sinon
                            a.setProjectileAlien();                     //on le remet à null
                        }
                    }
                    if (projectileVaisseau!=null && a.getProjectileAlien()!=null){      
                        // on verifie que les projectiles sont differents de null
                        if (projectileVaisseau.contient((int) Math.round(a.getProjectileAlien().getPosX()),(int) Math.round(a.getProjectileAlien().getPosY()))){
                            //si le projectile de l'alienne est touché par celui du vaisseau
                            a.setProjectileAlien();       // on met le projectile de l'alien à null

                            projectileVaisseau=null;      // on met le projectile du vaisseau à null.
                        }
                    }
                }
                a.evolue();                                 // les projectile ont été gere maintenant on fait evoluer l'alienne (il se deplace)
                if(this.compteTours % 10 == 0){             // tout les 10 tour de jeu
                    a.anime();                              //l'alien change d'animation
                }

                if (this.compteTours%20==0){a.changerDep();}    //tout les 20 tours de jeu on fait changer le deplacement(on change l'animation) de l'alien
            }
            if(this.compteTours % 40 == 0){                     //tout les 40 tours de jeu
                Random r=new Random();                              
                int monNum = r.nextInt(lesAliens.size());  //crée un random entre 0 et la taille de la liste d'alien
                lesAliens.get(monNum).tire();              // grâce à ce random on choisit l'alien qui va tirer
            }

            if (this.compteTours==100){         // si on à fait 100 tours de jeu

                for (Alien a:this.lesAliens){     // pour chaque alien
                    a.changerDeplacement();         // on change le deplacement
                    compteTours=0;                  // on remet le compte tours à 0
                    a.setPosY(1);                 // on change leur position Y
                }
            }
        }
        this.compteTours++;
        score.ajoute(1);

        // à partir de la on gère les suppressions d'aliens
        if (lesAliens.size()>0){                    
            for (Alien a:this.lesAliensTouche){     // pour chaque alien touche
                this.lesAliens.remove(a);           // on l'enleve de la liste
            }
        }
        lesAliensTouche=new ArrayList<>(); // on remet à vide la liste des aliens touché 
    }   


    /**
     * verifie que nous avons gagner le jeu. Pour gagner il faut supprimer tous les aliens.
     * @return un boolean qui va indiquer si nous avons gagner
     */
    public boolean gagner(){
        if (lesAliens.size()==0){return true;}
        return false;
    }

    
    /**
     * methode qui permet de dire si nous avons perdu. Pour perdre il faut soit que le tire d'un alien touche le vaisseau 2 fois, soit que les positionY de l'un des 
     * alien touche la position Y du vaisseau
     * @return un boolean indiquant si nous avons perdu
     */
    public boolean perdu(){
        if (this.v.getLaVie().getVie()==0){return true;}
        if (lesAliens.size()!=0){
            for (Alien a:this.lesAliens){
                if ((int) Math.round(a.getPosY())<=4){return true;}
            }
        }
        return false;
    }
}