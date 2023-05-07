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
    private List<Murs> lesMurs;
    /**
     * ceci est la methode qui va crée notre gestionnaire de jeu
     */
    public GestionJeu(){
        this.hauteur=60;                                                            // on va crée un jeu de hauteur 60
        this.largeur=120;                                                           // de largeur 120
        this.projectileVaisseau=null;                                               // on initialise le projectile du vaisseau à null
        this.v=new Vaisseau((this.largeur/2)-4);                                    // on place notre vaisseau au centre du jeu
        this.vieMaxVaisseau=v.getLaVie().getVie();                                  // on crée le maximum de la vie de notre vaisseau
        this.score=new Score();                                                     // on crée le score
        this.lesAliens=new ArrayList<>();                       //création de la liste d'alien
        for (int i=0;i<4;++i){                                  // on va crée 4 lignes d'alien
            int positionXdesAliens=5;
            while (positionXdesAliens<this.largeur-20){
                if (i==0){this.lesAliens.add(new AlienTypeUn(positionXdesAliens, this.hauteur/2));
                    positionXdesAliens+=15;}
                else if (i==2 || i==1){
                    this.lesAliens.add(new AlienTypeDeux(positionXdesAliens, (this.hauteur/2)+7*i));
                    positionXdesAliens+=15;
                }
                else{
                    this.lesAliens.add(new AlienTypeTrois(positionXdesAliens, (this.hauteur/2)+7*i));
                    positionXdesAliens+=15;
                }
            }
        }
        this.compteTours=0;
        this.lesAliensTouche=new ArrayList<>();
        this.nombreToursSansImage=50;
        this.lesMurs=new ArrayList<>();
        this.lesMurs.add(new Murs((double)this.largeur-25,(double)8));
        this.lesMurs.add(new Murs((double)15,(double)8));
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
     * cette methode va nous permettre d'avoir tous les objet( vaisseau, alienne, projectile) dans un ensemble de chaines
     * @return un ensemble de chaine
     */
    public EnsembleChaines getChaines(){
        EnsembleChaines e=new EnsembleChaines();                        // on commence par crée une chaine vide
        if (!perdu()){                                      // si on n'a pas perdu
            EnsembleChaines c=new EnsembleChaines();            
            c.ajouteChaine(2, getHauteur()-1, score.toString());                // on ajoute le score à l'ensemble chaine c
            c.ajouteChaine(15, getHauteur()-1, this.v.getLaVie().toString());     //on ajoute a c le vie du vaisseau
            
            for (ChainePositionnee chaine:c.getChaines()){              //pour chaque chaine dans c
               chaine.setIsWhite(true);                         // on met la couleur a blanc
            }
            e.union(c);                                                 //on ajoute dans e l'ensemble chaine c
            
            
            if (lesMurs.size()!=0){                                                         //si on a des murs
                for (Murs m:this.lesMurs){                                                  //pour chaque murs
                    if (m.getLaVie().getVie()>0){e.union(m.getEnsembleChaines());}}
                    //on verifie si sa vie est superieur à 0 et on l'ajoute
            }
    
            if (this.projectileVaisseau!=null){                                         // si on a tiré
                e.union(this.projectileVaisseau.getEnsembleChaines());                    // on affiche le projectile du vaisseau.
            }
            if (lesAliens.size()!=0){                                       // si on a des aliens
                if (lesAliens.get(0).getDep()==false){                      
                // si le deplacement du premiere alien est a false (il n'a pas ete anime). On regarde que le premier car pour chaque alien c'est le meme resultat.
                    
                    for (Alien a:this.lesAliens){                             //pour chaque alien
                        e.union(a.anime());                                  // on anime l'alien
                        if (a.getProjectileAlien()!=null){e.union(a.getProjectileAlien().getEnsembleChaines());}    //si il a tire on l'affiche
                    }
                }
                else{   
                    for (Alien a:this.lesAliens){               // si le premiere alien a ete anime avant
                        e.union(a.getEnsembleChaines());        // on affiche sa façon non anime
                        if (a.getProjectileAlien()!=null){e.union(a.getProjectileAlien().getEnsembleChaines());}
                    }
                    
                }
            } 
        
            if (vieMaxVaisseau-1==this.v.getLaVie().getVie()){              //si le vaisseau vient de prendre un projectile d'un alien
                if (nombreToursSansImage==0){                               // le nombre de tours sans vaisseau vaut 0
                    vieMaxVaisseau=this.v.getLaVie().getVie();              // on decroit de 1 la vie max du vaisseau
                    nombreToursSansImage=51;                                // on remet à 51 le nombre d'image sans tours
                }
                if (nombreToursSansImage%11==0){e.union(this.v.getEnsembleChaines());}      // si le nombre d'image sans vaisseau est modulo 11 on va afficher le vaisseau
                nombreToursSansImage-=1;            // on decroit le nombre de tours sans vaisseau
            }
            else{e.union(this.v.getEnsembleChaines());}         // sinon on va afficher le vaisseau
        }
        return e;
    }


    /**
     * Permet de faire evoluer le projectile du vaisseau ou de le suppirmer
     */
    private void evolutionProjectileVaisseau(){
        if (projectileVaisseau!=null){
            if (this.projectileVaisseau.getPosY()<this.hauteur){        // on regarde si la position y du projectil est inferieur à la longueur de la fenetre
                this.projectileVaisseau.evolue();                       // on va faire evoluer le projectile sur la meme position x
            }
            else{
                this.projectileVaisseau=null;                           // on remet le projectile à null si il à atteint le haut de la fenetre
            }
        } 
    }

    /**
     * Cette methode permet de changer les vie des murs si ils sont touché par un alien ou par le projectile du vaisseau
     */
    private void GestionDesMurs(){
        if (this.lesMurs.size()!=0){            //si il y a des murs
            for (Murs m:this.lesMurs){
                if (this.projectileVaisseau!=null){             // si le projectile du vaisseau est different de null (on  a tiré)
                    if(m.contient((int) Math.round(projectileVaisseau.getPosX()),(int) Math.round(projectileVaisseau.getPosY()))){  
                        //si le murs contient le projectile du vaisseau
                        
                        projectileVaisseau=null;            //on supprime le projectil
                        m.setLaVie();                       // on décroit la vie du murs
                    }
                }
                if (lesAliens.size()>0){                              // si il y à des aliens
                    for (Alien a:this.lesAliens){                    // pour chaque alien
                        if (a.getProjectileAlien()!=null){
                            if(m.contient((int) Math.round(a.getProjectileAlien().getPosX()),(int) Math.round(a.getProjectileAlien().getPosY()))){
                                // si le tire de l'alien touche le murs
                                a.setProjectileAlien();         // on change l'etat du projectile de l'alien
                                m.setLaVie();                   
                            }
                        }
                    }
                }    

                if (m.getLaVie().getVie()==0){              // si la vie du murs vaut 0
                    this.lesMurs.remove(m);                 // on supprime le murs
                    break;                                  // on sort de la boucle.
                }
            }
        }
    }

    /**
     * Cette methode permet de supprimer les alien si ils sont touché par les projectile du vaisseau
     */
    private void suppresionAlien(){
        if (lesAliens.size()>0){                        //si il ya des aliens
            if (lesAliensTouche.size()!=0){                                     // si il y a des aliens qui se sont fait touché
                for (Alien a:this.lesAliensTouche){     // pour chaque alien touché
                    if (a instanceof AlienTypeUn ){score.ajoute(10);}               //on modifie le score par rapport au type de l'alien
                    if (a instanceof AlienTypeDeux ){score.ajoute(20);}
                    if (a instanceof AlienTypeTrois ){score.ajoute(50);}
                    this.lesAliens.remove(a);           // on enleve l'alien  de la liste
                }
            }
            
        }
        lesAliensTouche=new ArrayList<>(); // on remet à vide la liste des aliens touché 
    }

    /**
     * Cette methode va faire tirer les aliens ennemis tous les x tours, changer leur position Y tous les x tours,changer leur deplacement, les animer.
     */
    private void GererLesTours(){
        if (lesAliens.size()!=0){
            for (Alien a:this.lesAliens){
                if(this.compteTours % this.getLargeur()/100 == 0){             // tout les 10 tour de jeu
                    a.anime();                              //l'alien change d'animation
                }

                if (this.compteTours%20==0){a.changerDep();}    //tout les 20 tours de jeu on fait changer le deplacement(on change l'animation) de l'alien
                
            }
            if(this.compteTours % (this.largeur/2)-10 == 0){                     //tout les 40 tours de jeu
                Random r=new Random();                              
                int monNum = r.nextInt(lesAliens.size());  //crée un random entre 0 et la taille de la liste d'alien
                lesAliens.get(monNum).tire();              // grâce à ce random on choisit l'alien qui va tirer
            }

            if (this.compteTours==this.largeur){         // si on à fait toutes la largeur du jeu
                for (Alien a:this.lesAliens){     // pour chaque alien
                    a.changerDeplacement();         // on change le deplacement
                    compteTours=0;                  // on remet le compte tours à 0
                    a.setPosY(1);                 // on change leur position Y
                }
            }
        }
    }   

    /**
     * Methode qui permet de gerer la collision entre projectile, de faire evoluer les aliens, de voir si le tire de l'alien touche le projectile ou le vaissea
     * La parti de supprsion des alliens sera gerer par une autre methode. 
     */
    private void GererLesAliens(){

        if (lesAliens.size()>0){                              // si il y à des aliens
            for (Alien a:this.lesAliens){                    // pour chaque alien
                //on gere la partie entre les  aliens et le projectile du vaisseau
                if (projectileVaisseau !=null){   
                    // on verife que le projectile du vaisseau est different de null 
                    if(a.contient((int) Math.round(projectileVaisseau.getPosX()), (int) Math.round(projectileVaisseau.getPosY()))){
                        
                        // si l'alien se fait touche par le projectil
                        this.lesAliensTouche.add(a);        // on ajoute l'alien à la liste pour le supprimer plus tard dans la methode
                        
                        //this.lesProjectilesQuiTouche.add(projectileVaisseau);   //on ajoute
                        projectileVaisseau=null;
                    }
                }

                if (a.getProjectileAlien()!=null){          //si le projectille de l'alien est different de null (si il a tirer)
                    
                    if (v.contient((int) Math.round(a.getProjectileAlien().getPosX()),(int) Math.round(a.getProjectileAlien().getPosY()))){this.v.setLaVie();a.setProjectileAlien();}
                    // si le tire de l'alien touche le vaisseau, on change la valeur de la vie du vaisseau et du projectile de l'alien
                }   
                if (a.getProjectileAlien()!=null){// à la ligne d'avant le projectile peut être mis a null donc une reverification est necessaire
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
                        //si le projectile de l'alien est touché par celui du vaisseau

                        a.setProjectileAlien();       // on met le projectile de l'alien à null
                        projectileVaisseau=null;      // on met le projectile du vaisseau à null.
                    }
                }
                
                a.evolue();                   // les projectile ont été gere maintenant on fait evoluer l'alien (il se deplace)
            }
        }
    }



    
    /**
     * methode qui represente tous ce qui va se passer lors d'un tour dans le jeu
     */
    public void jouerUnTour(){
        evolutionProjectileVaisseau();                  // appel de la methode privé evolution Projectile.
        GestionDesMurs();                               // appel de la methode pour gerer les murs.
        GererLesAliens();                               // appel de la methode gererLesAliens qui gerer faire tous ce qui peut se passer avec les aliens

        GererLesTours();         // appel de la methode qui permet de gerer ce qui se passe à x tours
        this.compteTours++;
        suppresionAlien();      // permet de supprimer les aliens touché. 
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