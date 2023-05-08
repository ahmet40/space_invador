
/**
 * permet d'initialiser la class vaisseau
 */
public class Vaisseau {
    private double posX;
    private Vie laVie;
    /**
     * Constructeur de la class vaisseau
     * @param posX la position X à laquelle on va positionner le vaisseau
     */
    public Vaisseau(double posX){               // permet de crée l'instance du vaisseau du jeu
        this.posX=posX;      
        this.laVie=new Vie(2);                  
    }   
    /**
     * permet de renvoyer la position en X du vaisseau
     * @return un double la position du vaisseau
     */
    public double getPosX() {           
        return posX;                            // on retourne la position X du vaisseau
    }

    /**
     * Permet de retourner l'attribut Vie du Vaisseau
     * @return Vie la vie du vaisseau
     */
    public Vie getLaVie() {
        return laVie;
    }

    /**
     * Permet de baiser de 1 le nombre de vie du vaisseau
     */
    public void setLaVie() {
        this.laVie.setVie(this.getLaVie().getVie()-1);
    }


    /**
     * permet de deplacer le vaisseau de x position
     * @param dx le nombre de x qui va se deplacer
     */ 
    public void deplace(double dx){             
        this.posX+=dx;                           // permet de modifier la position X du vaisseau
    }
    /**
     * methode qui va nous permettre de postionner le canon d'ou le projectile va etre tirer
     * @return la postion en X du canon
     */
    public int positionCanon(){
        return (int)posX+4;                     // permet de positionner la position X du canon
    }
    /**
     * methode qui va representer notre vaisseau sous forme d'un ensemble de chaine
     * @return l'ensemble de chaine 
     */
    public EnsembleChaines getEnsembleChaines(){
        EnsembleChaines ch=new EnsembleChaines();                                   // crée un ensemble de chaine vide
        ch.ajouteChaine((int)posX,3,"    ▄    ");
        ch.ajouteChaine((int)posX,2,"   ███   ");                        
        ch.ajouteChaine((int)posX,1,"▄███████▄" );  
        ch.ajouteChaine((int)posX,0,"█████████");                              // on va placer dans cette ensemble l'image de notre vaisseau
        for (ChainePositionnee c:ch.getChaines()){c.setIsWhite(true);}      
        // on parcours notre ensemble et pour chaque chaine on change la valeur de setIsWhite. Cela va nous permettre de mettre notre vaisseau en blanc.
        return ch;  
    }

    /**
     * permet de voir si la position X,Y est contenu dans notre vaisseau. Pour cela on va faire appel à notre methode getEnsembleChaine 
     * et pour cette ensemble on regarde si il contient la position X,Y.
     * @param x la position x representez par un entier
     * @param y la position y representez par un entier
     * @return  un boolean indiquant si la position X,Y et contenu dans le vaisseau
     */
    public boolean contient(int x,int y){
        return this.getEnsembleChaines().contient(x, y);                // on regarde si la position x,y et contenu dans le vaisseau.
    }
}